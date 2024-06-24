/**
 * 22.07.2008 16:58:29
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 600 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-08-07 12:20:01 +0300 (Чт, 07 авг 2008) $
 */
package com.eclipsesp.tplanner.web;

import java.io.*;
import java.sql.*;
import java.util.regex.*;

import javax.servlet.*;

import org.apache.commons.logging.*;
import org.apache.tools.ant.*;
import org.springframework.beans.*;
import org.springframework.web.servlet.*;

import com.eclipsesp.tplanner.core.facade.*;

/**
 * We should override basic implementation as we need to apply DB patches if any
 * TODO implement
 * 
 * @author Pavel Marinchev
 * @author vpostrigan
 */
public class TplannerDispatcherServlet
        extends DispatcherServlet {

	private static final long serialVersionUID = -5825588642993247311L;

	private final Log logger = LogFactory.getLog(this.getClass());

	/** Path name to ant file (build.xml) that update database */
	private final String PATH_NAME = "/WEB-INF/patches";

	private String foundPatchName;

	/**
	 * Debug mode flag. Should always be <code>false</code> except when you
	 * need to use debug mode. To source code repository should be committed
	 * only with <code>false</code> value. This values initializes in
	 * {@link #init()} servlet's method, referencing appropriate servlet context
	 * parameter
	 */
	public static boolean DEBUG = false;

	/** Parameter specifying the DEBUG mode state of application */
	public static final String DEBUG_PARAM = "debugMode";

	@Override
	protected void initFrameworkServlet() throws ServletException,
	    BeansException {
		super.initFrameworkServlet();

		// Initializing DEBUG state
		ServletContext sc = this.getServletContext();
		if (null != sc) {
			String debugParam =
			        sc.getInitParameter(TplannerDispatcherServlet.DEBUG_PARAM);
			if (null != debugParam) {
				// TODO apply DEBUG state if any
			}
		}

		// Update database if needed
		this.dataBaseAntPatcher(sc);
	}

	/**
	 * Update database
	 * 
	 * @param sc
	 *            ServletContext
	 */
	public void dataBaseAntPatcher(ServletContext sc) {

		File patchDirectory = new File(sc.getRealPath(this.PATH_NAME));

		try {
			int currentDataBaseVersion = this.currentDataBaseVersion();
			int newDataBaseVersion[] =
			        this.findNewDataBaseVersion(patchDirectory,
			                currentDataBaseVersion);

			if (currentDataBaseVersion <= 0 != newDataBaseVersion[1] <= 0) {
				this.logger.error("\nDefined database version isn't correct. Cause: maybe this patch was installed or he has no correct old and new version.\n");
			} else {
				if (newDataBaseVersion[0] == currentDataBaseVersion
				        && newDataBaseVersion[1] > currentDataBaseVersion) {
					this.logger.info("\nFound new database version. Deploying new patch..."
					        + "\nCurrent database version: "
					        + currentDataBaseVersion
					        + "\nNew database version: "
					        + newDataBaseVersion[1] + "\n");

					// Execute ant file (patch)
					try {
						this.usingAnt(sc, this.foundPatchName);
					} catch (SQLException sqlE) {
						this.logger.error("\nError while finding connection params to database (URL, user name ...)"
						        + sqlE + "\n");
					}

				} else {
					this.logger.error("\nFound new database version don't bigger then current database version\n");
				}
			}
		} catch (Exception e) {
			this.logger.error("\nError while finding and deploying patch file"
			        + e + "\n");
		}
	}

	/**
	 * Executing ant file
	 * 
	 * @param sc
	 *            ServletContext
	 * @param antFile
	 *            found ant file (patch) name
	 * @throws SQLException
	 */
	public void usingAnt(ServletContext sc, String antFile) throws SQLException {

		Project p = new Project();
		File buildFile =
		        new File(sc.getRealPath(this.PATH_NAME + "/" + antFile));

		/**
		 * Set a user property, which cannot be overwritten by set/unset
		 * property calls. Any previous value is overwritten.
		 */
		p.setUserProperty("ant.file", buildFile.getAbsolutePath());

		// Creation consoleLogger that will include output stream
		DefaultLogger consoleLogger = new DefaultLogger();
		ByteArrayOutputStream sw = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(sw);

		consoleLogger.setErrorPrintStream(ps);
		consoleLogger.setOutputPrintStream(ps);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);

		/**
		 * Add a build listener to the list. This listener will be notified of
		 * build events for this project.
		 */
		p.addBuildListener(consoleLogger);

		try {
			/**
			 * Send a &quot;build started&quot; event to the build listeners for
			 * this project.
			 */
			p.fireBuildStarted();

			/**
			 * Initialise the project. This involves setting the default task
			 * definitions and loading the system properties.
			 */
			p.init();

			/**
			 * Set the base directory for the project, checking that the given
			 * filename exists and is a directory.
			 */
			p.setBasedir(sc.getRealPath("/WEB-INF/patches/"));

			DatabasePatcher dd =
			        DatabasePatcher.class.cast(this.getWebApplicationContext().getBean(
			                "databasePatcher"));

			p.setProperty("com.", sc.getRealPath("/WEB-INF/patches/"));

			p.setProperty("db.driver", dd.getDriverClassName());
			p.setProperty("db.url", dd.getUrl());
			p.setProperty("db.user", dd.getUsername());
			p.setProperty("db.pw", dd.getPassword());

			// Directory with sql scripts. For example, patch_v1_v2
			p.setProperty("db.sql.script.directory",
			        this.foundPatchName.substring(0,
			                this.foundPatchName.lastIndexOf('.')));

			ProjectHelper helper = ProjectHelper.getProjectHelper();
			p.addReference("ant.projectHelper", helper);
			helper.parse(p, buildFile);

			/**
			 * Execute the specified target and any targets it depends on.
			 * 
			 * @exception BuildException
			 *                if the build failed.
			 */
			p.executeTarget(p.getDefaultTarget());

			/**
			 * Send a &quot;build finished&quot; event to the build listeners
			 * for this project.
			 * 
			 * @param exception
			 *            an exception indicating a reason for a build failure.
			 *            May be <code>null</code>, indicating a successful
			 *            build.
			 */
			p.fireBuildFinished(null);

			this.logger.info("= Ant file started =");
			this.logger.info(sw.toString());
			this.logger.info("= Ant file finished =");

		} catch (BuildException e) {
			this.logger.error(sw.toString() + "\n" + e);
			p.fireBuildFinished(e);
		}
	}

	/**
	 * @return current database version
	 */
	public int currentDataBaseVersion() {
		int currentDataBaseVersion = 0;

		try {
			IAccountManager accountManager =
			        IAccountManager.class.cast(this.getWebApplicationContext().getBean(
			                "accountManager"));

			currentDataBaseVersion = accountManager.getMetaData();
		} catch (Exception e) {
			this.logger.error("Cannot define current database version from MataData\n"
			        + e);
		}
		return currentDataBaseVersion;
	}

	/**
	 * @param patchDirectory
	 *            the place where will be search new patches
	 * @return OldDataBaseVersion and newDataBaseVersion of the found patch name
	 */
	public int[] findNewDataBaseVersion(File patchDirectory,
	    int currentDataBaseVersion) {

		int newDataBaseVersion[] = { 0, 0 };

		Pattern patternBuildFile =
		        Pattern.compile("^([a-z]+)[\u005F][v]([0-9]+)[\u005F][v]([0-9]+)[\u002E]([a-z]+)$");

		if (patchDirectory.isDirectory()) {

			String patchList[] = patchDirectory.list();

			for (String element : patchList) {
				File f = new File(patchDirectory + "/" + element);

				if (f.isFile()) {
					String s = f.getName();
					Matcher mat = patternBuildFile.matcher(s);

					if (mat.matches()) {
						String numSOld =
						        s.substring(s.indexOf('v') + 1,
						                s.lastIndexOf('_'));

						String numSNew =
						        s.substring(s.lastIndexOf('v') + 1,
						                s.lastIndexOf('.'));

						try {
							int currentOldDataBaseVersion =
							        Integer.parseInt(numSOld);

							int currentNewDataBaseVersion =
							        Integer.parseInt(numSNew);

							if (currentOldDataBaseVersion == currentDataBaseVersion
							        && currentNewDataBaseVersion > newDataBaseVersion[1]) {
								newDataBaseVersion[0] =
								        currentOldDataBaseVersion;
								newDataBaseVersion[1] =
								        currentNewDataBaseVersion;

								this.foundPatchName = s;
							}
						} catch (NumberFormatException e) {
							// This message, at correct work, will be never seen
							this.logger.error("Cannot convert database version in number "
							        + e);
						}
					} else {
						this.logger.info("\nFound file isn't a needed ant file: `"
						        + f.getName()
						        + "` .For example, patch_v1_v2.xml\n");
					}
				} else {
					// In directory will be not only ant files. Info canceled.
					/*
					 * this.logger.info("Found object isn't a file: " +
					 * f.getName());
					 */
				}
			}
		} else {
			this.logger.error("\nDon't found patch directory: "
			        + this.PATH_NAME + "\n");
		}

		return newDataBaseVersion;
	}
}

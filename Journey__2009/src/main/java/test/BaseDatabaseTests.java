package test;

import javax.annotation.*;
import javax.sql.*;

import org.springframework.jdbc.core.support.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.transaction.*;


/**
 * Superclass for tests which use the database. It has the following features:
 * 
 * It provides a DataSource.
 * 
 * The database patcher runs before each test method, so the database is always
 * up to date when each test method runs.
 * 
 * The transaction is automatically rolled back after each test method. (The
 * Spring superclass does this.) This relieves tests of having to clean up their
 * test data, and prevents tests from leaving test data behind which might break
 * other tests or fill the database.
 */
@ContextConfiguration(locations = { "/test/BaseDatabaseTests-context.xml" })
public abstract class BaseDatabaseTests
        extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	protected DataSource dataSource;

	/**
	 * It would be nice to patch BeforeClass rather than BeforeDatabase, but the
	 * Spring superclass doesn't seem to initialize the datasource early enough.
	 */
	@BeforeTransaction
	public void patchDatabase() {
		// Do a patch or anything else
	}

	protected <T extends JdbcDaoSupport> T setUp(Class<T> daoSupportClass) {
		try {
			T daoSupport = daoSupportClass.newInstance();
			daoSupport.setDataSource(this.dataSource);
			daoSupport.afterPropertiesSet();
			return daoSupport;
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}

}
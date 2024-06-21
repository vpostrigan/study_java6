package journey.gwt.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import journey.gwt.client.SesameGWTService;
import journey.semantic.JourneySesame;
import journey.semantic.jena.JenaTest;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class SesameGWTServiceImpl extends RemoteServiceServlet implements SesameGWTService {

	private static final long serialVersionUID = 3094847897126294824L;
	
	@Override
	public String getRDFSchemaRdfXml(String inputFileName){
		try{
			return JenaTest.getInstance().getRDFSchemaRdfXml(inputFileName);
		}catch(Exception e){
			return "ERROR:" + e.getMessage();
		}
	}
	
	@Override
	public Boolean saveRDFSchemaRdfXml(String pathPart, String RDFSToSave) {
		try{
			String path = this.getServletContext().getRealPath(pathPart);
			return JenaTest.getInstance().saveRDFSchemaRdfXml(path, RDFSToSave);
		}catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return false;
		}
	}

	@Override
	public String[][] getStatements() {
		try{
			return JenaTest.getInstance().getStatements();
		}catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return null;
		}
	}
	
	@Override
	public Boolean deleteStatement(String[][] values){
		try{
			String path = this.getServletContext().getRealPath("/destination");
			return JenaTest.getInstance().deleteStatement(path, values);
		}catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return false;
		}
	}
	
	@Override
	public Boolean uploadFile(String fileName) {
		try{
			String dir = this.getServletContext().getRealPath("/rdf");			
			System.out.println(dir);
			
			String RDFToSave = JenaTest.getInstance().getRDFSchemaRdfXml(fileName);
			System.out.println(RDFToSave);
			
			return JenaTest.getInstance().uploadToDirectory(dir, fileName, RDFToSave);
		}catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return false;
		}
	}
	
	@Override
	public Boolean uploadFileToRepository (String urlValue) {		
		try{
			JourneySesame.getInstance().addRDF(urlValue);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	@Override
	public Collection<String> uploadedFiles() {
		Collection<String> temp = new ArrayList<String>();
		File path = new File(this.getServletContext().getRealPath("/rdf"));
		
		if (path.isDirectory()) {
			String patchList[] = path.list();
			
			for (String element : patchList) {
				File f = new File(path + "/" + element); 
				
				if (f.isFile()) {
					String s = f.getName();
					temp.add(s);
				}
			}
		}
		return temp;
	}
	
}
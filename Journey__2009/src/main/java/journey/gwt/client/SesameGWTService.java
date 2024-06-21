package journey.gwt.client;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("sesameGWT")
public interface SesameGWTService extends RemoteService{ 
	
	String getRDFSchemaRdfXml(String inputFileName);
	
	Boolean saveRDFSchemaRdfXml(String pathPart, String RDFSToSave);
	
	String[][] getStatements();	

	Boolean deleteStatement(String[][] values);
	
	Boolean uploadFile(String pathRDFToSave);
	
	Collection<String> uploadedFiles();
	
	Boolean uploadFileToRepository (String urlValue);
}
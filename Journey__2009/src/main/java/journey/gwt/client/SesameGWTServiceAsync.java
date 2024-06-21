package journey.gwt.client;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SesameGWTServiceAsync {
	
	void getRDFSchemaRdfXml(String inputFileName, AsyncCallback<String> callback);
		
	void saveRDFSchemaRdfXml(String pathPart, String RDFSToSave, AsyncCallback<Boolean> callback);
	
	void getStatements(AsyncCallback<String[][]> callback);
	
	void deleteStatement(String[][] values, AsyncCallback<Boolean> callback);
	
	void uploadFile(String pathRDFToSave, AsyncCallback<Boolean> callback);
	
	void uploadedFiles(AsyncCallback<Collection<String> >callback);
	
	void uploadFileToRepository (String urlValue, AsyncCallback<Boolean> callback);
}

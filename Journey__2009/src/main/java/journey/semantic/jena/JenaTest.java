package journey.semantic.jena;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

public class JenaTest {
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	private static JenaTest _instance = null;
	
	private Model model = null;
	
	private URL url = null;
	
	public synchronized static JenaTest getInstance() throws IOException {
		if(_instance == null){
			_instance = new JenaTest();
		}		
		return _instance;
	}
	
	private JenaTest() throws IOException {
		// create an empty model
		model = ModelFactory.createDefaultModel();
		
		// rdfs
		url = new URL("http://localhost:8080/Journey/destination");
		
		readModelFromFile(url);
	}
	
	public void readModelFromFile(URL u) throws IOException {
		String inputFileName = u.toString();
		
		// use the FileManager to find the input file
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
			logger.error("JONI: File: " + inputFileName + " not found");
		    throw new IllegalArgumentException("File: " + inputFileName + " not found");
		}
		
		// read the RDF/XML file
		model.read(in, "");
		in.close();
	}
	
	public String getRDFSchemaRdfXml(String inputFileName) throws IllegalArgumentException, IOException {
		
		// use the FileManager to find the input file
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		    throw new IllegalArgumentException("File: " + inputFileName + " not found");
		}
		
		int i;
		String rdfs = "";
		
		do {
			i = in.read();
			if (i != -1) {
				rdfs = rdfs + (char)i;
			}
		} while (i != -1);
		
		in.close();
		return rdfs;
	}
	
	public Boolean saveRDFSchemaRdfXml(String path, String RDFSToSave) throws IOException {
		
		FileOutputStream file = null;
		try{
			file = new FileOutputStream(path);
		}catch(FileNotFoundException e){
			e.printStackTrace();
			return false;
		}
		
		file.write(RDFSToSave.getBytes());
		file.close();
		//model.write(file, "RDF/XML-ABBREV");
		readModelFromFile(url);
		return true;
	}
	
	public Boolean uploadToDirectory(String dir, String filename, String RDFToSave) throws IOException {
		
		File file = new File(dir, new File(filename).getName());
		
		if (!file.createNewFile()) {
			logger.error("JONI: File not created, but must.");
		}
		
		FileOutputStream out = null;
		try{
			out = new FileOutputStream(file);
		}catch(FileNotFoundException e){
			e.printStackTrace();
			return false;
		}
		out.write(RDFToSave.getBytes());
		out.close();
		
		return true;
	}
	
	public String[][] getStatements(){
		// list the statements in the Model
		StmtIterator iter = model.listStatements();

		Collection<String> subjects = new ArrayList<String>();
		Collection<String> predicaties = new ArrayList<String>();
		Collection<String> objects = new ArrayList<String>();
		
		// print out the predicate, subject and object of each statement
		while (iter.hasNext()) {
		    Statement stmt      = iter.nextStatement();  // get next statement
		    
		    Resource  subject   = stmt.getSubject();     // get the subject
		    Property  predicate = stmt.getPredicate();   // get the predicate
		    RDFNode   object    = stmt.getObject();      // get the object

		    subjects.add(subject.toString());
		    predicaties.add(predicate.toString());
		    
		    if (object instanceof Resource) {
		    	objects.add(object.toString());
		    } else {
		        // object is a literal
		    	objects.add(" \"" + object.toString() + "\"");
		    }
		}
		
		String[][] temp = new String[subjects.size()][3];
		
		Object[] tempR = subjects.toArray();
		for(int i=0; i < subjects.size(); i++) {
			temp[i][0] = tempR[i].toString();
		}
		
		Object[] tempP = predicaties.toArray();
		for(int i=0; i < predicaties.size(); i++) {
			temp[i][1] = tempP[i].toString();
		}
		
		Object[] tempO = objects.toArray();
		for(int i=0; i < objects.size(); i++) {
			temp[i][2] = tempO[i].toString();
		}
		
		return temp;
	}
	
	/**
	 * @param statements
	 * @throws IOException 
	 */
	public Boolean deleteStatement(String path, String[][] values) throws IOException {
		
		StmtIterator iter = model.listStatements();
		
		Statement[] statementsToDelete = new Statement[values.length];
		int pos = 0;
		while (iter.hasNext()) {
			Statement stmt      = iter.nextStatement();  // get next statement
			
			for(int i=0; i < values.length; i++){
				if(stmt.getSubject().toString().equals(values[i][0]) && 
						stmt.getPredicate().toString().equals(values[i][1]) && 
						stmt.getObject().toString().equals(values[i][2])){
					logger.info("JONI: Statement: " + stmt.getSubject().toString() + " | " +
							stmt.getPredicate().toString() + " | " +
							stmt.getObject().toString()+ " removing");					
					statementsToDelete[pos] = stmt;
					pos++;
					break;
				}				
			}
		}
		model.remove(statementsToDelete);
		
		FileOutputStream file = null;
		try{
			file = new FileOutputStream(path);
		}catch(FileNotFoundException e){
			e.printStackTrace();
			return false;
		}
		
		model.write(file);
		file.close();
		return true;
	}
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Vector;


class XML_Reader {
	public Vector<Vector<String>> XML_Read(String path) throws Exception{
		
		Vector<Vector<String>> v = new Vector<Vector<String>>();
		
		FileReader fr = new FileReader(path); 
		BufferedReader br = new BufferedReader(fr);
				
		String file_line;
		while( (file_line = br.readLine()) != null ){
			
			Vector<String> v_ = new Vector<String>();	// Table(...)
			
			if( file_line.startsWith("<Table name=\"") ){
				StringTokenizer st = new StringTokenizer(file_line, "\"");
				st.nextToken();
				v_.addElement(st.nextToken());
				
				while( (file_line = br.readLine()).startsWith("<Field>") ){	
					StringTokenizer st2 = new StringTokenizer(file_line, "><");
					st2.nextToken();
					v_.addElement(st2.nextToken());
				}
				
				v.addElement(v_);
			}			
		}
		
		fr.close();
		
		return v;
	}
}

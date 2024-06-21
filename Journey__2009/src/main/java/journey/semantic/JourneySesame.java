package journey.semantic;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.openrdf.sail.nativerdf.NativeStore;

public class JourneySesame {
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	private static JourneySesame _instance = null;
	
	private final static String SESAME_REPOSITORY = "/sesame_repository/";
	
	private Repository myRepository = null;
	
	private JourneySesame() throws RepositoryException {
		File dataDir = new File(SESAME_REPOSITORY);
		String indexes = "spoc";
		this.myRepository = new SailRepository(new NativeStore(dataDir, indexes));
		this.myRepository.initialize();
		logger.info("JONE: SESAME_REPOSITORY: " + this.myRepository.getDataDir());
	}
	
	/**
	 * @return Instance for working with Sesame Repository
	 * @throws RepositoryException
	 */
	public synchronized static JourneySesame getInstance() throws RepositoryException {
		if(_instance == null){
			_instance = new JourneySesame();
		}		
		return _instance;
	}
	
	public void addRDF(String urlValue) throws RepositoryException, RDFParseException, 
											IOException, MalformedQueryException, QueryEvaluationException {		
		RepositoryConnection con = myRepository.getConnection();			
		try{
			URL url = new URL(urlValue);
			con.add(url, url.toString(), RDFFormat.RDFXML);
		} finally {
			con.close();
		}
	}
	
	public JourneySesameBean getData(String subject, String predicate, String object) throws
			RepositoryException, MalformedQueryException, QueryEvaluationException {
		
		RepositoryConnection con = myRepository.getConnection();
		JourneySesameBean temp = null;
		
		try{
			String queryString = "SELECT " + subject + ", " + predicate + ", " + object + 
					" FROM {" + subject + "} " + predicate + " {" + object + "}" +
					" USING NAMESPACE journey = <http://localhost:8080/Journey/destination#>";
			
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
			
			TupleQueryResult result = tupleQuery.evaluate();
			
			try{
				Collection<Value> subjects = new ArrayList<Value>();
				Collection<Value> predicaties = new ArrayList<Value>();
				Collection<Value> objects = new ArrayList<Value>();
				
				while(result.hasNext()){
					BindingSet bindingSet = result.next();
					Value s = bindingSet.getValue(subject);
					Value p = bindingSet.getValue(predicate);
					Value o = bindingSet.getValue(object);
					
					subjects.add(s);
					predicaties.add(p);
					objects.add(o);
				}
				temp = new JourneySesameBean(subjects, predicaties, objects);				
			} finally {
				result.close();
			}			
		} finally {
			con.close();
		}
		
		return temp;
	}
	
	public JourneySesameBeanColumns getData(String[] selectColumn, String from) throws
			RepositoryException, MalformedQueryException, QueryEvaluationException {

		RepositoryConnection con = myRepository.getConnection();
		JourneySesameBeanColumns temp = null;
		
		String select = "";
		for(int i=0; i < selectColumn.length; i++){
			if(i == selectColumn.length-1){
				select = select + selectColumn[i]; 
			} else {
				select = select + selectColumn[i] + ", ";
			}
		}

		try{
			String queryString = "SELECT " + select + from +
				" USING NAMESPACE journey = <http://localhost:8080/Journey/destination#>";
			
			logger.info("JONI: queryString: " + queryString);
	
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
	
			TupleQueryResult result = tupleQuery.evaluate();
			try{
				Collection<Collection<Value>> columnTemp = new ArrayList<Collection<Value>>();
		
				while(result.hasNext()){
					BindingSet bindingSet = result.next();
					Collection<Value> localTemp = new ArrayList<Value>();
					
					for(String s: selectColumn){
						Value v = bindingSet.getValue(s);
						localTemp.add(v);
					}
					columnTemp.add(localTemp);
				}
				temp = new JourneySesameBeanColumns(columnTemp);				
			} finally {
				result.close();
			}
		} finally {
			con.close();
		}

		return temp;
	}	
}

package journey.gwt.client;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.TreeImages;

public interface Images extends TreeImages {
	
	@Resource("journey/gwt/bundle/rdf_metadata_button.gif")
	AbstractImagePrototype rdf_metadata_button();
	
	@Resource("journey/gwt/bundle/rdfIn.gif")
	AbstractImagePrototype rdfIn();
	
	@Resource("journey/gwt/bundle/addFile.gif")
	AbstractImagePrototype addFile();
	
	@Resource("journey/gwt/bundle/file.gif")
	AbstractImagePrototype file();
	
	@Resource("journey/gwt/bundle/rdfInStore.gif")
	AbstractImagePrototype rdfInStore();
	
	@Resource("journey/gwt/bundle/remove.gif")
	AbstractImagePrototype remove();
	
	@Resource("journey/gwt/bundle/save.gif")
	AbstractImagePrototype save();
}

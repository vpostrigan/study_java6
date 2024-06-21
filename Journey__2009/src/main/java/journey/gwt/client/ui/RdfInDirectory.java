package journey.gwt.client.ui;

import java.util.Collection;

import journey.gwt.client.Images;
import journey.gwt.client.SesameGWTConstants;
import journey.gwt.client.SesameGWTServiceAsync;
import journey.gwt.client.Util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
import com.google.gwt.user.client.ui.Widget;

public class RdfInDirectory {
	
	private SesameGWTConstants messages = (SesameGWTConstants) GWT.create(SesameGWTConstants.class);
	
	// Get the images
	private Images images = (Images) GWT.create(Images.class);
	
	private SesameGWTServiceAsync sesameGWTService;
		
	private FlowPanel rdfInDirectoryTPanel;
	
	private FlowPanel rightPanel;
	
	private Tree tree;
	
	private TreeItem directoryRDF;
	
	private Button rdfInDirectory;
	
	private Button saveRdf;
	
	private Label labelMark;
	
	private TextArea rdfInDirectoryTextArea;
	
	public RdfInDirectory(SesameGWTServiceAsync sesameGWTService,
			/*FlowPanel sesameGWTPanel,*/ FlowPanel rightPanel,
			/*TreeItem directoryRDF,*/ Button rdfInDirectory) {
		super();
		rdfInDirectoryTPanel = new FlowPanel();	
		
		this.sesameGWTService = sesameGWTService;
		/*this.sesameGWTPanel = sesameGWTPanel;*/
		this.rightPanel = rightPanel;
		/*this.directoryRDF = directoryRDF;*/
		this.rdfInDirectory = rdfInDirectory;
		this.rdfInDirectory.addClickListener(new rdfInDirectoryClick());
	}


	private class rdfInDirectoryClick implements ClickListener {
		public void onClick (Widget sender) {
			
			rightPanel.clear();
			rightPanel.setStyleName("rightPanel");
			
			tree = new Tree();
			tree.addTreeListener(new rdfInDirectoryTreeListener());
			
			directoryRDF = new TreeItem();
			directoryRDF.setText("rdf");
			directoryRDF.setState(true);
			
			tree.addItem(directoryRDF);
			rdfInDirectoryTPanel.add(tree);
			
			
			rdfInDirectoryTextArea = new TextArea();
			
			saveRdf = new Button( Util.getHeaderString(messages.save(), images.save()) );
			saveRdf.setEnabled(true);
			saveRdf.addClickListener(new SaveRdfClick());
			
			labelMark = new Label("");
			labelMark.setStyleName("labelSuccess");
			
			rdfInDirectoryTPanel.add(rdfInDirectoryTextArea);
			rdfInDirectoryTPanel.add(new HTML("<br>"));
			rdfInDirectoryTPanel.add(saveRdf);
			rdfInDirectoryTPanel.add(labelMark);
						
			rightPanel.add(rdfInDirectoryTPanel);
			
			
			AsyncCallback<Collection<String>> callback = new AsyncCallback<Collection<String>>(){
				public void onSuccess(Collection<String> result) {
					for(String f : result){
						TreeItem treeItem = new TreeItem();
						treeItem.setText(Util.getHeaderString(f, images.file()));
						directoryRDF.addItem(treeItem);
					}
				}
				
				public void onFailure(Throwable caught) {
					Window.alert("ERROR: " + caught.getMessage());
				}
			};			
			sesameGWTService.uploadedFiles(callback);
		}
	}
	
	private class rdfInDirectoryTreeListener implements TreeListener {
		public void onTreeItemSelected(TreeItem item){
			
			AsyncCallback<String> callback = new AsyncCallback<String>(){
				public void onSuccess(String result) {
					rdfInDirectoryTextArea.setText(result);
					labelMark.setText("");
				}
				
				public void onFailure(Throwable caught) {
					Window.alert("ERROR: " + caught.getMessage());
				}
			};
			
			sesameGWTService.getRDFSchemaRdfXml("http://localhost:8080/Journey/rdf/"+item.getText(), callback);
		}
		
		public void onTreeItemStateChanged(TreeItem item){
			// nothing
		}
	}
	
	
	private class SaveRdfClick implements ClickListener {
		public void onClick (Widget sender) {
			AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>(){
				public void onSuccess(Boolean result) {
					if(result){
						labelMark.setText(messages.saved());
					} else {
						labelMark.setText(messages.error_watchLog());
					}
				}
				
				public void onFailure(Throwable caught) {
					labelMark.setText("ERROR: " + caught.getMessage());
				}
			};
			sesameGWTService.saveRDFSchemaRdfXml( "/rdf/" + tree.getSelectedItem().getText(),
					rdfInDirectoryTextArea.getText(), callback);
		}
	}

	
	public Button getRdfInDirectory() {
		return this.rdfInDirectory;
	}
}

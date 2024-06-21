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
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
import com.google.gwt.user.client.ui.Widget;

public class RdfInSesame {
	
	private SesameGWTConstants messages = (SesameGWTConstants) GWT.create(SesameGWTConstants.class);
	
	// Get the images
	private Images images = (Images) GWT.create(Images.class);
	
	private SesameGWTServiceAsync sesameGWTService;
	
	private FlowPanel rdfInRepositoryTPanel;

	private FlowPanel rightPanel;
	
	private Button rdfInRepository;
	
	private Tree tree;
	
	private TreeItem directoryRDF;
		
	private Label labelMark;
	
	
	public RdfInSesame(SesameGWTServiceAsync sesameGWTService, FlowPanel rightPanel, Button rdfInDirectory) {
		super();
		rdfInRepositoryTPanel = new FlowPanel();	
		
		this.sesameGWTService = sesameGWTService;
		this.rightPanel = rightPanel;
		this.rdfInRepository = rdfInDirectory;
		this.rdfInRepository.addClickListener(new rdfInRepositoryClick());
	}
	
	private class rdfInRepositoryClick implements ClickListener {
		public void onClick (Widget sender) {
			rightPanel.clear();
			rightPanel.setStyleName("rightPanel");
			
			tree = new Tree();
			tree.addTreeListener(new rdfInRepositoryTreeListener());
			
			directoryRDF = new TreeItem();
			directoryRDF.setState(true);
			directoryRDF.setText("rdf");
			
			tree.addItem(directoryRDF);
			rdfInRepositoryTPanel.add(tree);
			
			
			labelMark = new Label("");
			labelMark.setStyleName("labelSuccess");
			
			rdfInRepositoryTPanel.add(new HTML("<br>"));
			rdfInRepositoryTPanel.add(labelMark);
						
			rightPanel.add(rdfInRepositoryTPanel);
			
			
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
	
	private class rdfInRepositoryTreeListener implements TreeListener {
		public void onTreeItemSelected(TreeItem item){
			
			AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>(){
				public void onSuccess(Boolean result) {
					Window.alert(messages.saved());
				}
				
				public void onFailure(Throwable caught) {
					Window.alert("ERROR: " + caught.getMessage());
				}
			};
			
			sesameGWTService.uploadFileToRepository("http://localhost:8080/Journey/rdf/"+item.getText(), callback);
		}
		
		public void onTreeItemStateChanged(TreeItem item){
			// nothing
		}
	}

	
	public Button getRdfInRepository() {
		return rdfInRepository;
	}

	public void setRdfInRepository(Button rdfInRepository) {
		this.rdfInRepository = rdfInRepository;
	}
}

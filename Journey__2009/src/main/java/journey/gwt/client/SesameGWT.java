package journey.gwt.client;

import java.util.ArrayList;
import java.util.Collection;

import journey.gwt.client.ui.RdfInDirectory;
import journey.gwt.client.ui.RdfInSesame;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SesameGWT implements EntryPoint {
	
	private SesameGWTConstants messages = (SesameGWTConstants) GWT.create(SesameGWTConstants.class);
	
	// Get the images
	private Images images = (Images) GWT.create(Images.class);
	
	private final SesameGWTServiceAsync sesameGWTService = GWT.create(SesameGWTService.class);

	private TextArea rdfsSchemaTextArea;
	private Label labelMark;
	private Label labelMark_DeleteRdfsStatement;
	
	private Button SaveRdfsGraph;
	
	private FlowPanel rightPanel = new FlowPanel();
	
	private FlexTable flexTable;
	private Button DeleteRdfsStatement;
	
	private FlowPanel sesameGWTPanel;
	
	private FileUpload fileUpload;
		
	/**
	* This is the entry point method.
	*/
	public void onModuleLoad() {
		
		sesameGWTPanel = new FlowPanel();
		
		// By default, we assume we'll make RPCs to a servlet, but see
		// updateRowData(). There is special support for canned RPC responses.
		// (Which is a totally demo hack, by the way :-)
		// 
		ServiceDefTarget target = (ServiceDefTarget) sesameGWTService;
			
		// Use a module-relative URLs to ensure that this client code can find
		// its way home, even when the URL changes (as might happen when you
		// deploy this as a webapp under an external servlet container).
		String moduleRelativeURL = GWT.getModuleBaseURL() + "sesameGWT";
		target.setServiceEntryPoint(moduleRelativeURL);
		
	    // Create the dialog box
	    //final DialogBox dialogBox = new DialogBox();
	    //dialogBox.setText("Welcome to GWT!");
	    //dialogBox.setAnimationEnabled(true);
	    //Button closeButton = new Button("close");
	    //VerticalPanel dialogVPanel = new VerticalPanel();
	    //dialogVPanel.setWidth("100%");
	    //dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
	    //dialogVPanel.add(closeButton);
	
	    /*closeButton.addClickListener(new ClickListener() {
	      public void onClick(Widget sender) {
	        dialogBox.hide();
	      }
	    });*/
	
	    // Set the contents of the Widget
		/* dialogBox.setWidget(dialogVPanel);
	    
	    button.addClickListener(new ClickListener() {
	      public void onClick(Widget sender) {
	        dialogBox.center();
	        dialogBox.show();
	      }
	    });*/
		
		// Right panel
		rdfsGraph();
		SaveRdfsGraph.setEnabled(false);
		
		
		// Left panel
	StackPanel panel = new StackPanel();
		
		String rdfsHeader = Util.getHeaderString(messages.rdf_Schema(), images.rdf_metadata_button(), 
				"cw-StackPanelHeader");
		
		VerticalPanel rdfsPanel = new VerticalPanel();
		rdfsPanel.setSpacing(4);
		
			Button rdfsGraphB = new Button(messages.rdf_Graph());
			rdfsGraphB.addClickListener(new rdfsGraphB_Click());
		
			Button rdfsStatements = new Button(messages.rdf_Schema_Statements());
			rdfsStatements.addClickListener(new rdfsStatements());
		
			rdfsPanel.add(rdfsGraphB);
			rdfsPanel.add(rdfsStatements);
		
	panel.add(rdfsPanel, rdfsHeader, true);
		
		String rdfInHeader = Util.getHeaderString(messages.rdf_In(), images.rdfIn(), "cw-StackPanelHeader");
		
		VerticalPanel rdfInPanel = new VerticalPanel();
		rdfInPanel.setSpacing(4);
		
			Button rdfInUploadButton = new Button( Util.getHeaderString(messages.rdf_In_upload(), images.addFile()) );
			rdfInUploadButton.addClickListener(new rdfInUploadClick());
			
			Button rdfInDirectory = new Button(messages.rdf_In_DirectoryWithIncomingRDF());
						
			rdfInPanel.add(rdfInUploadButton);
			rdfInPanel.add(new RdfInDirectory(sesameGWTService, rightPanel,	rdfInDirectory).getRdfInDirectory());
			
	panel.add(rdfInPanel, rdfInHeader, true);
	
		String rdfInRepositoryHeader = Util.getHeaderString(messages.rdf_in_repository(), images.rdfInStore(), 
				"cw-StackPanelHeader");
		
		VerticalPanel rdfInRepositoryPanel = new VerticalPanel();
		rdfInRepositoryPanel.setSpacing(4);
			
			Button rdfInRepository = new Button(messages.rdf_In_DirectoryWithIncomingRDF());
			
			rdfInRepositoryPanel.add(new RdfInSesame(sesameGWTService, rightPanel, rdfInRepository).getRdfInRepository());
		
	panel.add(rdfInRepositoryPanel, rdfInRepositoryHeader, true);
	
	
		VerticalPanel langPanel = new VerticalPanel();
		langPanel.add(new Hyperlink(messages.lang_russian(), "?locale=ru"));
		langPanel.add(new Hyperlink(messages.lang_ukraine(), "?locale=ua"));
		langPanel.add(new Hyperlink(messages.lang_english(), "?locale=en"));
		
		FlowPanel leftPanel = new FlowPanel();
		leftPanel.add(panel);
		leftPanel.setStyleName("leftPanel");
		leftPanel.add(new HTML("<br>"));		
		leftPanel.add(langPanel);
				
		sesameGWTPanel.add(leftPanel);
		sesameGWTPanel.add(rightPanel);
		sesameGWTPanel.setWidth("100%");
	
		// Add it to the root panel.
		RootPanel.get().add(sesameGWTPanel);
		RootPanel.get().setStyleName("sesameGWT");
	}
	
	private void rdfsGraph(){
		rightPanel.clear();
		rightPanel.setStyleName("rightPanel");
		
		rdfsSchemaTextArea = new TextArea();
		
		SaveRdfsGraph = new Button( Util.getHeaderString(messages.save(), images.save()) );
		SaveRdfsGraph.setEnabled(true);
		SaveRdfsGraph.addClickListener(new SaveRdfsGraph());
		
		labelMark = new Label("");
		labelMark.setStyleName("labelSuccess");
		
		rightPanel.add(rdfsSchemaTextArea);
		rightPanel.add(new HTML("<br>"));
		rightPanel.add(SaveRdfsGraph);
		rightPanel.add(labelMark);
	}
	
	private class rdfsGraphB_Click implements ClickListener {
		public void onClick (Widget sender) {
			
			rdfsGraph();
			
			AsyncCallback<String> callback = new AsyncCallback<String>(){
				public void onSuccess(String result) {
					rdfsSchemaTextArea.setText(result);					
				}
				
				public void onFailure(Throwable caught) {
					rdfsSchemaTextArea.setText("ERROR: " + caught.getMessage());
				}
			};			
			sesameGWTService.getRDFSchemaRdfXml("http://localhost:8080/Journey/destination", callback);
		}
	}
	
	private class SaveRdfsGraph implements ClickListener {
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
					rdfsSchemaTextArea.setText("ERROR: " + caught.getMessage());
				}
			};
			sesameGWTService.saveRDFSchemaRdfXml("/destination", rdfsSchemaTextArea.getText(), callback);
		}
	}
	
	private class rdfsStatements implements ClickListener {
		public void onClick (Widget sender) {
			
			rightPanel.clear();
			rightPanel.setStyleName("rightPanel");
			
			flexTable = new FlexTable();
			flexTable.setStyleName("flexTableHeader");
			flexTable.setBorderWidth(1);
			flexTable.setCellPadding(4);
			flexTable.setCellSpacing(1);
			
			flexTable.setHTML(0, 0, "#");
			flexTable.setHTML(0, 1, "<label>" + messages.subject_resource() + "</label>");
			flexTable.setHTML(0, 2, "<label>" + messages.predicate_property() + "</label>");
			flexTable.setHTML(0, 3, "<label>" + messages.object_RDFNode() + "</label>");
			
			DeleteRdfsStatement = new Button( Util.getHeaderString(messages.delete(), images.remove()) );
			DeleteRdfsStatement.addClickListener(new DeleteRdfsStatement());
			
			labelMark_DeleteRdfsStatement = new Label("");
			labelMark_DeleteRdfsStatement.setStyleName("labelSuccess");
						
			rightPanel.add(flexTable);
			rightPanel.add(DeleteRdfsStatement);
			rightPanel.add(labelMark_DeleteRdfsStatement);
			
			AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>(){
				public void onSuccess(String[][] result) {
					for(int i=0; i < result.length; i++){						
						flexTable.setWidget(i+1, 0, new CheckBox(""));
						flexTable.setText(i+1, 1, result[i][0]);
						flexTable.setText(i+1, 2, result[i][1]);
						flexTable.setText(i+1, 3, result[i][2]);
					}
				}
				
				public void onFailure(Throwable caught) {
					labelMark_DeleteRdfsStatement.setText("ERROR: " + caught.getMessage());
				}
			};
			sesameGWTService.getStatements(callback);
		}
	}
	
	private class DeleteRdfsStatement implements ClickListener {
		public void onClick (Widget sender) {
			
			Collection<Integer> selectedCB = new ArrayList<Integer>(); 
			for(int i=1; i < flexTable.getRowCount(); i++){
				CheckBox tempCB = (CheckBox) flexTable.getWidget(i, 0);
				if(tempCB.isChecked()){
					selectedCB.add(i);
				}
			}
			
			String[][] values = new String[selectedCB.size()][3];
			int pos = 0;
			for(Integer i : selectedCB){
				values[pos][0] = flexTable.getText(i, 1);
				values[pos][1] = flexTable.getText(i, 2);
				values[pos][2] = flexTable.getText(i, 3);
				pos++;
			}
			
			AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>(){
				public void onSuccess(Boolean result) {
					if(result){
						labelMark_DeleteRdfsStatement.setText(messages.deleted());
					} else {
						labelMark_DeleteRdfsStatement.setText(messages.error_watchLog());
					}
				}
				
				public void onFailure(Throwable caught) {
					labelMark_DeleteRdfsStatement.setText("ERROR: " + caught.getMessage());
				}
			};
			
			sesameGWTService.deleteStatement(values, callback);
		}
	}
	
	private class rdfInUploadClick implements ClickListener {
		public void onClick (Widget sender) {
			rightPanel.clear();
			rightPanel.setStyleName("rightPanel");
			
			fileUpload = new FileUpload();
			rightPanel.add(fileUpload);
			
			// Add a button to upload the file
		    Button uploadButton = new Button(messages.upload());
		    uploadButton.addClickListener(new ClickListener() {
		    	public void onClick(Widget sender) {
		    		String filename = fileUpload.getFilename();		    		
		    		if (filename.length() == 0) {
		    			Window.alert(messages.file_incorrect());
		    		} else {
		    			Window.alert(messages.file() + " " + filename + messages.uploaded_successful());
		    			
		    			AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>(){
		    				public void onSuccess(Boolean result) {
		    					if(result){
		    						Window.alert(messages.saved());
		    					} else {
		    						Window.alert(messages.error_watchLog());
		    					}
		    				}		    				
		    				public void onFailure(Throwable caught) {
		    					Window.alert("ERROR: " + caught.getMessage());
		    				}
		    			};
		    			sesameGWTService.uploadFile(filename, callback);
		    		}
		    	}
		    });

		    rightPanel.add(new HTML("<br>"));
		    rightPanel.add(uploadButton);
		}
	}
	
}

package journey.gwt.client;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class Util {
	
	/**
	* Get a string representation of the header that includes an image and some
	* text.
	* 
	* @param text the header text
	* @param image the {@link AbstractImagePrototype} to add next to the header
	* @return the header as a string
	*/
	public static String getHeaderString(String text, AbstractImagePrototype image) {
		
		// Add the image and text to a horizontal panel
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setSpacing(0);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel.add(image.createImage());
		hPanel.add(new HTML(text));

		// Return the HTML string for the panel
		return hPanel.toString();
	}
	
	public static String getHeaderString(String text, AbstractImagePrototype image, String styleName) {
		
		// Add the image and text to a horizontal panel
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setSpacing(0);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel.add(image.createImage());
		HTML headerText = new HTML(text);
		headerText.setStyleName(styleName);
		hPanel.add(headerText);

		// Return the HTML string for the panel
		return hPanel.toString();
	}
}

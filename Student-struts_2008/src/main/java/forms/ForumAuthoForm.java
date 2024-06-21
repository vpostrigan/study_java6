package forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ForumAuthoForm extends ActionForm {
	private static final long serialVersionUID = 14L;
	
	private int chapId;
	private String chapPass;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    	
		return null;
    }
	
	
	public int getChapId() {
		return chapId;
	}
	public void setChapId(int chapId) {
		this.chapId = chapId;
	}
	public String getChapPass() {
		return chapPass;
	}
	public void setChapPass(String chapPass) {
		this.chapPass = chapPass;
	}
}

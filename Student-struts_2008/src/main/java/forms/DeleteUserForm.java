package forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class DeleteUserForm extends ActionForm {
	private static final long serialVersionUID = 5L;	
	
	private int code;
	private boolean stringDeleted;

	
	public void reset(ActionMapping mapping, HttpServletRequest request) {	
		this.code = 0;	
		this.stringDeleted = false;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
        
        // Проверка поля 'code'
        if (code < 1){
        	// В БД нет продуктов с ID < 0
        	errors.add(finals.Final.ERROR_CODE_DELETE, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__CODE, code));
        }
        
        if (errors.isEmpty()) return null;        
        return errors;
	}

	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public boolean isStringDeleted() {
		return stringDeleted;
	}
	public void setStringDeleted(boolean stringDeleted) {
		this.stringDeleted = stringDeleted;
	}
}

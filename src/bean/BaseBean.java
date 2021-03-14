package bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class BaseBean {

	public void addMessage(String summary, String detail, String severity) {
    	if(severity.equals("error")) {
            FacesMessage messages = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
            FacesContext.getCurrentInstance().addMessage("growl", messages);
    	}else {
            FacesMessage messages = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
            FacesContext.getCurrentInstance().addMessage("growl", messages);	
    	}
    }
	
}

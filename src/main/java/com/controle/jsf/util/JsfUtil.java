package com.controle.jsf.util;

import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

public class JsfUtil {
 
    public static Throwable getRootCause(Throwable cause) {
        if (cause != null) {
            Throwable source = cause.getCause();
            if (source != null) {
                return getRootCause(source);
            } else {
                return cause;
            }
        }
        return null;
    }
    
    public static void openDialog(String dialogo) {
        RequestContext.getCurrentInstance().execute("PF('" + dialogo +"').show();");
    }
    
    public static void closeDialog(String dialogo) {
        RequestContext.getCurrentInstance().execute("PF('" + dialogo +"').hide();");
    }    
    
    public static void cleanSubmittedValues(UIComponent component) {
	if (component instanceof EditableValueHolder) {
		EditableValueHolder evh = (EditableValueHolder) component;
		evh.setSubmittedValue(null);
		evh.setValue(null);
		evh.setLocalValueSet(false);
		evh.setValid(true);
	}
	if (component.getChildCount() > 0) {
		for (UIComponent child : component.getChildren()) {
			cleanSubmittedValues(child);
		}
	}
    }        
    
    public static UIComponent findComponent(String componentName) {
	FacesContext ctx = FacesContext.getCurrentInstance();
	return ctx.getViewRoot().findComponent(componentName);
    }
        
    public static void cleanSubmittedValues(String componentName) {
	cleanSubmittedValues(findComponent(componentName));
	
    }        

    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String i18n) {
        String msg = getMessageI18n(i18n);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    public static void addErrorMessageNoI18N(String message) {        
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);        
    }

    public static void addSuccessMessage(String i18n) {
        String mensagem = getMessageI18n(i18n);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }
    
    public static void addSuccessMessageNoI18N(String message) {        
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);        
    }    
    
    public static String getMessageI18n(String key) {
	ResourceBundle bundle = ResourceBundle.getBundle("/Bundle");
	return bundle.getString(key);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

}

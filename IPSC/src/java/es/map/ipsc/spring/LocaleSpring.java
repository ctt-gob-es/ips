package es.map.ipsc.spring;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.spring.Globals;
import org.apache.spring.action.Action;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;

/**
 * El Class LocaleSpring.
 */
public class LocaleSpring extends Action { 

    /**
     * Execute.
     *
     * @param mapping el mapping
     * @param form el form
     * @param request el request
     * @param response el response
     * @return el spring forward
     * @throws IllegalAccessException el illegal access exception
     * @throws InvocationTargetException el invocation target exception
     * @throws NoSuchMethodException el no such method exception
     */
    public SpringForward execute(SpringMapping mapping, SpringForm form, 
            HttpServletRequest request, HttpServletResponse response) 
            throws IllegalAccessException, InvocationTargetException, 
            NoSuchMethodException { 

        String language = (String) request.getParameter("lang"); 
        if (language != null && language.length() > 0) { 
            Locale locale = new java.util.Locale(language, ""); 
            HttpSession session = request.getSession(); 
            session.setAttribute(Globals.LOCALE_KEY, locale); 
        } 

        return mapping.findForward("success"); 
    } 
}  

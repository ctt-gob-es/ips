package es.map.ipsg.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class FormSessionMapper.
 */
@Component
@Scope("session")
public class FormSessionMapper {

	/** el forms map. */
	private Map<String, SpringForm> formsMap = new HashMap<>();
	
	/**
	 * Clear.
	 */
	public void clear() {
		formsMap.clear();
	}
	
	/**
	 * Obtiene el.
	 *
	 * @param key el key
	 * @return el spring form
	 */
	public SpringForm get(String key) {
		return formsMap.get(key);
	}

	/**
	 * Put.
	 *
	 * @param key el key
	 * @param springForm el spring form
	 * @return el spring form
	 */
	public SpringForm put(String key, SpringForm springForm) {
		return formsMap.put(key, springForm);
	}

	/**
	 * Resolve form.
	 *
	 * @param <F> the generic type
	 * @param springForm el spring form
	 * @param request el request
	 * @return el f
	 */
	@SuppressWarnings("unchecked")
	public <F extends SpringForm> F resolveForm(F springForm, HttpServletRequest request) {
		String requestPath = request.getServletPath();
		
		if ("S".equalsIgnoreCase(request.getParameter("menu"))) {
			clear();
		}
		
		SpringForm auxForm = null;
		
		if (request.getMethod().equals("GET")) {
			auxForm = get(requestPath);
		}

		if (auxForm != null) {
			springForm = (F) auxForm;
		} else {
			put(requestPath, springForm);
		}
		
		return springForm;
	}
	
}

package br.com.company.gwt.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public interface InputServlet {
	
	public ServletConfig getServletConfig();
	void setServletConfig(ServletConfig servletConfig);
	
	public ServletContext getServletContext();
	void setServletContext (ServletContext servletContext);
	
	public HttpServletRequest getServletRequest();
	void setServletRequest(HttpServletRequest servletRequest);

}
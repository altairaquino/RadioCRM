package br.com.company.gwt.server.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoggedFilter implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {  
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		/*
		Usuario user = (Usuario) session.getAttribute("user");
		
		if(user == null && !req.getPathInfo().equals("/userService")){
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		*/
		filter.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}  

}

package br.com.company.gwt.server;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GwtDispatcherServlet extends RemoteServiceServlet{

	private static final long serialVersionUID = 4264228600144634225L;
	
	private static final Log LOGER = LogFactory.getLog(GwtDispatcherServlet.class);
	
	private Object getBean(HttpServletRequest request) {
		String serviceName = getServiceNameByRequest(request);
		Object beanService = getBeanService(serviceName);

		if (!(beanService instanceof RemoteService)) {
			throw new IllegalArgumentException("Spring bean is not a GWT RemoteService: " + serviceName + " (" + beanService + ")");
		}
		if(beanService instanceof InputServlet){
			((InputServlet)beanService).setServletRequest(getThreadLocalRequest());
			((InputServlet)beanService).setServletConfig(getServletConfig());
			((InputServlet)beanService).setServletContext(getServletContext());
		}
		
		LOGER.debug("Bean for service " + serviceName + " is " + beanService);
		return beanService;
	}

	/**
	 * Parse the service name from the request URL.
	 *
	 * @param request
	 * @return bean name
	 **/
	protected String getServiceNameByRequest(HttpServletRequest request) {
		String url = request.getRequestURI();
		String service = url.substring(url.lastIndexOf("/") + 1);

		LOGER.debug("Service for URL " + url + " is " + service);
		return service;
	}


	/**
	 * Look up a spring bean with the specified name in the current web
	 * application context.
	 *
	 * @param name
	 * bean name
	 * @return the bean
	 **/
	protected Object getBeanService(String name) {
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		if (applicationContext == null) {
			throw new IllegalStateException("No Spring web application context found");
		}
		if (!applicationContext.containsBean(name)) {
			throw new IllegalArgumentException("Spring bean not found: " + name);
		}
		return applicationContext.getBean(name);
	}
	
	@Override
	public String processCall(String payload) throws SerializationException {
		checkPermutationStrongName();
		try {
			Object handler = getBean(getThreadLocalRequest());
			RPCRequest rpcRequest = RPC.decodeRequest(payload, handler.getClass(), this);
			onAfterRequestDeserialized(rpcRequest);
			
			LOGER.debug("Invoking " + handler.getClass().getName() + "." + rpcRequest.getMethod().getName());
			return RPC.invokeAndEncodeResponse(handler, rpcRequest.getMethod(), rpcRequest.getParameters(), rpcRequest.getSerializationPolicy());
		} catch (IncompatibleRemoteServiceException ex) {
			LOGER.error("An IncompatibleRemoteServiceException was thrown while processing this call.", ex);
			return RPC.encodeResponseForFailure(null, ex);
		} catch (Exception tokenException) {
			LOGER.error("An RpcTokenException was thrown while processing this call.",	tokenException);
			return RPC.encodeResponseForFailure(null, tokenException);
		}	
	}

}
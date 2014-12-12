package org.fao.fi.refpub.webservice.cache;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;


@CacheService
@Interceptor
public class CacheServiceInterceptor {
	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		System.out.println("EDDAJEEEEEEEEEEEEEEE");
		System.out.println("SimpleInterceptor - Logging BEFORE calling method :"+context.getMethod().getName() );

		Object result = context.proceed();

		System.out.println("SimpleInterceptor - Logging AFTER calling method :"+context.getMethod().getName() );

		return result;
	}

}

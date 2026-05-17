package lab1.interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@LoggedInvocation
public class LoggedInvocationInterceptor implements Serializable {
    @AroundInvoke
    public Object logMethodEntry(InvocationContext context) throws Exception {
        System.out.println("Intercepted: " + context.getMethod().getName());
        return context.proceed();
    }
}
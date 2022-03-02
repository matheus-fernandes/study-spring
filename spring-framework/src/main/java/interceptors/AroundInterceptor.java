package interceptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AroundInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("I will invoke: " + invocation.getMethod().getName());
        System.out.println("With arguments: " + invocation.getArguments());
        return proceed(invocation);
    }

    private Object proceed(MethodInvocation invocation){
        try {
            return invocation.proceed();
        } catch (Throwable e){
            System.out.println("Error intercepted");
            return null;
        }
    }
}

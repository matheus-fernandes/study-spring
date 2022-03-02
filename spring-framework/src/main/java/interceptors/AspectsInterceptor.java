package interceptors;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import static java.lang.System.out;

@Aspect
public class AspectsInterceptor {
    @Pointcut("execution(* sayHello*(..))")
    public void hello(){}

    @Before("hello()")
    public void beforeHello(){
        out.println("I'm gonna say hello...");
    }

    @After("hello()")
    public void afterHello(){
        out.println("I have said hello!");
    }

    @Pointcut("execution(* sayBye*(..))")
    public void bye(){}

    @Before("bye()")
    public void beforeBye(){
        out.println("I'm gonna say bye...");
    }

    @After("bye()")
    public void afterBye(){
        out.println("I have said bye!");
    }

}

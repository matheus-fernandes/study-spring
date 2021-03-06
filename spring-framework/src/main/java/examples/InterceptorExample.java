package examples;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.PersonService;

public class InterceptorExample {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "config/interceptor.xml");

        PersonService proxy = context.getBean(
                "proxy", PersonService.class);

        proxy.printName();
        proxy.printURL();
        proxy.throwException();
    }
}

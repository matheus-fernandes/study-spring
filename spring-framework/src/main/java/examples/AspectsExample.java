package examples;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.SimpleService;

public class AspectsExample {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "config/aop.xml");

        SimpleService service = (SimpleService) context.getBean("service");

        service.sayHello();
        service.sayBye();
    }
}

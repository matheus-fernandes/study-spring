package examples;

import beans.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static java.lang.System.out;

public class DependencyInjectionExample {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("parentbean.xml");
        Parent parent = context.getBean("parent", Parent.class);

        out.println(parent);
    }
}

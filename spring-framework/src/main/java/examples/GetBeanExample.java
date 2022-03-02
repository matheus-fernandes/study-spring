package examples;
import beans.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static java.lang.System.out;


public class GetBeanExample {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "employeebean.xml");

        Employee employee1 = context.getBean(
                "employee1", Employee.class);

        Employee employee2 = context.getBean(
                "employee2", Employee.class);

        out.println(employee1);
        out.println(employee2);
    }
}

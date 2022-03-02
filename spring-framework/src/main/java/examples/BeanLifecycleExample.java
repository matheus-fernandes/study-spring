package examples;

import beans.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static java.lang.System.out;

public class BeanLifecycleExample {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = getContext();
        Person person = getPerson(context);

        out.println(person);
        context.close();
    }

    static ClassPathXmlApplicationContext getContext(){
        return new ClassPathXmlApplicationContext("personbean.xml");
    }

    static Person getPerson(ApplicationContext context){
        return context.getBean("person", Person.class);
    }
}

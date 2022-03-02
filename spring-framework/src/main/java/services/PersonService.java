package services;

import beans.Person;

import java.util.Locale;

import static java.lang.System.out;

public class PersonService {
    private final Person person;

    public PersonService(Person person){
        this.person = person;
    }

    public void printName(){
        out.println("Person name: " + person.getName());
    }

    public void printURL(){
        out.println("Person URL www." +
                person.getName().toLowerCase() + ".com"  );
    }

    public void throwException() throws Exception {
        throw new Exception("Person service exception!");
    }
}

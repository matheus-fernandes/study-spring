package beans;

import static java.lang.System.out;

public class Person {
    protected final String name;

    public Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void onInit(){
        out.println("init...");
    }

    public void onDestroy(){
        out.println("destroy...");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

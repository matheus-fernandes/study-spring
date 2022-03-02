package beans;

public class Parent extends Person{
    private Person child;

    public Parent(String name, Person child) {
        super(name);
        this.child = child;
    }

    public Person getChild() {
        return child;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "child=" + child +
                ", name='" + name + '\'' +
                '}';
    }
}

package config;

import static java.lang.System.out;

public class HelloService implements GreetingService {
    @Override
    public void greet() {
        out.println("Hello!");
    }
}

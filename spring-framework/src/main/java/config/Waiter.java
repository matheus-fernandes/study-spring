package config;

import org.springframework.beans.factory.annotation.Autowired;


public class Waiter {
    private GreetingService service;

    public Waiter(@Autowired GreetingService service){
        this.service = service;
    }

    public void greet(){
        service.greet();
    }
}

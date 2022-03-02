package config;

import org.springframework.beans.factory.annotation.Autowired;

public class Restaurant {

    private Waiter waiter;

    public Restaurant(@Autowired Waiter waiter){
        this.waiter = waiter;
    }

    public void open(){
        waiter.greet();
    }
}

package examples;

import config.BeanConfig;
import config.Restaurant;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;


public class BeanConfigInjectionExample {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                createContext(args);

        openRestaurant(context);
    }

    static ConfigurableApplicationContext createContext(String[] args){
        return SpringApplication.run(BeanConfig.class, args);
    }

    static void openRestaurant(ApplicationContext context){
       context.getBean(Restaurant.class).open();
    }
}

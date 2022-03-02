package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public GreetingService greetingService(){
        return new HelloService();
    }

    @Bean
    public Restaurant getRestaurant(@Autowired Waiter waiter){
        return new Restaurant(waiter);
    }

    @Bean
    public Waiter getWaiter(@Autowired GreetingService service){
        return new Waiter(service);
    }
}

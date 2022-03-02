package examples;

import annotated.AnnotatedConfig;
import annotated.Vendor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotatedConfigExample {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(
                        AnnotatedConfig.class
                );

        Vendor vendor = context.getBean(Vendor.class);
        vendor.showProduct();
    }
}

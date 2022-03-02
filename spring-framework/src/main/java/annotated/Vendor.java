package annotated;

import annotated.core.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.System.*;

@Component
public class Vendor {
    private final Store store;

    public Vendor(@Autowired Store store){
        this.store = store;
    }

    public void showProduct(){
        out.println(store.getProduct());
    }
}

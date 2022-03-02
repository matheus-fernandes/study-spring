package annotated;

import annotated.core.Store;
import org.springframework.stereotype.Component;

@Component
public class TShirtStore implements Store {
    @Override
    public String getProduct() {
        return "a t-shirt";
    }
}

package annotated;

import annotated.core.Store;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class ShoesStore implements Store {
    @Override
    public String getProduct() {
        return "A shoe...";
    }
}

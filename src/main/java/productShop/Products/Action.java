package productShop.Products;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Action {
    private int ruleForMinCount;
    private float price;
}

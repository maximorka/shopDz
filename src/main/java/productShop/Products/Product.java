package productShop.Products;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private String name;
    private float price;
    private Action action;
}

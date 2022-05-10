package productShop;

import productShop.Products.Action;
import productShop.Products.Product;

import java.util.ArrayList;
import java.util.List;

public class App {
    List<Product> productsInShop = new ArrayList();

    public App() {
        productsInShop.add(Product.builder().name("A").price(1.25f).action(Action.builder().price(1.0f).ruleForMinCount(3).build()).build());
        productsInShop.add(Product.builder().name("B").price(4.25f).build());
        productsInShop.add(Product.builder().name("C").price(1.0f).action(Action.builder().price(0.8f).ruleForMinCount(6).build()).build());
        productsInShop.add(Product.builder().name("D").price(0.75f).build());
    }


    public float calculateCostBasket(String basket) {
        String checkBasket = basket.replace(" ", "");
        if (!checkCorrectBasket(checkBasket))
            throw new IllegalArgumentException("Invalid input data:"+basket);
        return calculate(productsInShop, basket);
    }

    float calculate(List<Product> products, String basket) {
        float summ = 0;
        for (int j = 0; j < products.size(); j++) {
            int count = getCountSameProduct(basket, products.get(j).getName());
            float price = getActualPriceWithAction(products, j, count);
            summ += price * count;
        }
        return summ;
    }


    float getActualPriceWithAction(List<Product> allProductsInShop, int productIndex, int count) {
        float price = allProductsInShop.get(productIndex).getPrice();
        if (allProductsInShop.get(productIndex).getAction() != null)
            if (allProductsInShop.get(productIndex).getAction().getRuleForMinCount() <= count)
                price = allProductsInShop.get(productIndex).getAction().getPrice();
        return price;
    }

    int getCountSameProduct(String basket, String product) {
        int count = 0;
        for (int i = 0; i < basket.length(); i++) {
            String prod = String.valueOf(basket.charAt(i));
            if (product.equals(prod))
                count++;
        }
        return count;
    }

    boolean checkCorrectBasket(String basket) {
        return basket.matches("[A-Z]+");
    }

    public static void main(String[] args) {
        App app = new App();
        System.out.println("Cost:" + app.calculateCostBasket(" ABC  DABA "));
    }

}

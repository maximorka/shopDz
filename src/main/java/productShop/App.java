package productShop;

import productShop.dao.ProductDao;
import productShop.entity.Action;
import productShop.entity.Product;
import productShop.storage.Init.DataBaseInit;

import java.util.ArrayList;
import java.util.List;

import static productShop.storage.Storage.connectionURL;

public class App {

    ProductDao productDao = new ProductDao();

    public float calculateCostBasket(String basket) {
        String checkBasket = basket.replace(" ", "");
        if (!checkCorrectBasket(checkBasket))
            throw new IllegalArgumentException("Invalid input data:" + basket);
        return calculate(productDao.getAllProduct(), basket);

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
        if (allProductsInShop.get(productIndex).getAction() != null) {
            Action action = allProductsInShop.get(productIndex).getAction();
            if (action.getRule() <= count)
                price = action.getPrice();
        }
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
        new DataBaseInit().initDB(connectionURL);
        App app = new App();
        System.out.println("Cost:" + app.calculateCostBasket(" ABC  DABA "));
    }
}

package productShop;


import org.junit.jupiter.api.*;
import productShop.Products.Action;
import productShop.Products.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {
    private App app;
    List<Product> productsInShop;


    @Test
    @BeforeEach
    public void createIns() {
        app = new App();
        productsInShop = new ArrayList();
        productsInShop.add(Product.builder().name("A").price(1.25f).action(Action.builder().price(1.0f).ruleForMinCount(3).build()).build());
        productsInShop.add(Product.builder().name("B").price(4.25f).build());
        productsInShop.add(Product.builder().name("C").price(1.0f).action(Action.builder().price(0.8f).ruleForMinCount(6).build()).build());
        productsInShop.add(Product.builder().name("D").price(0.75f).build());
    }

    @Test
    @Order(1)
    public void nameProductOnlyOneChar() {
        String actualName = app.productsInShop.get(0).getName();
        int expectedCountChar = 1;
        int actualCountName = actualName.length();
        assertEquals(expectedCountChar, actualCountName, Integer.toString(actualCountName));
    }

    @Test
    @Order(2)
    public void nameProductNotNumber() {
        String actualName = app.productsInShop.get(0).getName().toString();
        assertTrue(actualName.matches("[A-Z]"), actualName);
    }


    @Test
    void getCountSameProduct() {

        List<String> testB = new ArrayList();
        testB.add("A");
        testB.add("BB");
        testB.add("CCC");
        testB.add("DDDD");

        char[] testProduct = {'A', 'B', 'C', 'D'};
        for (int i = 0; i < testB.size(); i++) {
            int actualRes = app.getCountSameProduct(testB.get(i), String.valueOf(testProduct[i]));
            int expectedCount = i + 1;
            assertEquals(expectedCount, actualRes, Integer.toString(expectedCount));
        }
    }

    @Test
    void getActualPriceWithAction() {

        float actualPrice = app.getActualPriceWithAction(productsInShop, 0, 7);
        float expectedPrice = 1f;
        assertEquals(expectedPrice, actualPrice, Float.toString(expectedPrice));

        actualPrice = app.getActualPriceWithAction(productsInShop, 1, 7);
        expectedPrice = 4.25f;
        assertEquals(expectedPrice, actualPrice, Float.toString(expectedPrice));

        actualPrice = app.getActualPriceWithAction(productsInShop, 2, 77);
        expectedPrice = 0.8f;
        assertEquals(expectedPrice, actualPrice, Float.toString(expectedPrice));
    }

    @Test
    void calculate() {
        float actualRes = app.calculate(productsInShop," ABC  DABA ");
        float expectedCount = 13.25f;
        assertEquals(expectedCount, actualRes, Float.toString(expectedCount));

        actualRes = app.calculate(productsInShop," AAAA ");
        expectedCount = 1f*4f;
        assertEquals(expectedCount, actualRes, Float.toString(expectedCount));
    }


    @Test
    void checkCorrectBasket() {
        assertTrue(app.checkCorrectBasket("ABDCS"));
        assertFalse(app.checkCorrectBasket("ABaCS"));
        assertFalse(app.checkCorrectBasket("A4FJH"));
        assertFalse(app.checkCorrectBasket("A,FJH"));
    }

    @Test
    void calculateCostBasket() {
        float actualRes = app.calculateCostBasket(" ABC  DABA ");
        float expectedCount = 13.25f;
        assertEquals(expectedCount, actualRes, Float.toString(expectedCount));

        assertThrows(IllegalArgumentException.class,()->app.calculateCostBasket("A S ,"));
        assertThrows(IllegalArgumentException.class,()->app.calculateCostBasket("ADFGBVG%5"));

    }
}
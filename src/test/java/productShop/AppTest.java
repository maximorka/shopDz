package productShop;


import org.junit.jupiter.api.*;
import productShop.entity.Action;
import productShop.entity.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {
    private App app;


    @Test
    @BeforeEach
    public void createIns() {
        app = new App();

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
    void checkCorrectBasket() {
        assertTrue(app.checkCorrectBasket("ABDCS"));
        assertFalse(app.checkCorrectBasket("ABaCS"));
        assertFalse(app.checkCorrectBasket("A4FJH"));
        assertFalse(app.checkCorrectBasket("A,FJH"));
    }


}
package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BasketTest extends TestRunner {
    private BasketPage openBasketPage;

    @BeforeMethod
    public void addProductToBasket() {
        openBasketPage = new HomePage()
                .openHomePage()
                .openProductByNumber(1)
                .addProductToBasket();
    }

    //In progress
    @Test
    public void testRestoreProduct() {
        var basket = openBasketPage.deleteProduct(0);
        var deletedProductLink = basket.getRecentlyViewedProductLink(1);
        var newB = basket.restoreProduct(1);
        var restoredProductLink = newB.getProductLink(1);
        assertEquals(restoredProductLink, deletedProductLink);
    }
}

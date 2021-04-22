package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        openBasketPage.deleteProduct(0);
        var deletedProductLink = openBasketPage.getRecentlyViewedProductLink(0);
        openBasketPage.restoreProduct(0);
        var restoredProductLink = openBasketPage.getProductLink(0);
        assertTrue(deletedProductLink == restoredProductLink);
    }
}

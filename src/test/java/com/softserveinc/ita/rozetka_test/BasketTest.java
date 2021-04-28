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

    @Test
    public void testAddProductFromRecentlyViewedSection() {
        openBasketPage.deleteProduct(1);
        openBasketPage.closeBasket();
        var recentlyViewedProductPage = openBasketPage.getRecentlyViewedSectionPage();
        var recentlyViewedProductName = recentlyViewedProductPage.getRecentlyViewedProductTitle(1);
        var productTitle = recentlyViewedProductPage.addProductFromRecentlyViewedProducts(1)
                .getProductTitle(1);
        assertTrue(productTitle.contains(recentlyViewedProductName), "Added product shouldn't equals with addition");
    }
}
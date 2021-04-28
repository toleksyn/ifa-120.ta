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
        var productPage = openBasketPage.deleteProduct(1)
                .closeBasket();
        var recentlyViewedProductPage = openBasketPage.getRecentlyViewedSectionPage();
        var recentlyViewedProductName = recentlyViewedProductPage.getProductTitle(1);
        productPage.openBasketPage()
                .getRecentlyViewedSectionPage();
        var productTitle = recentlyViewedProductPage.addProduct(1)
                .getProductTitle(1);
        assertTrue(productTitle.contains(recentlyViewedProductName), "Added product shouldn't equals with addition");
    }
}
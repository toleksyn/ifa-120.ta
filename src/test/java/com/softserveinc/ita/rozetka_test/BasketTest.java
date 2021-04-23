package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        var emptyBasketPage = openBasketPage.deleteProduct(1);
        var recentlyViewedProductName = emptyBasketPage.getRecentlyViewedProductTitle(1);
        var productTitle = emptyBasketPage.addProductFromRecentlyViewedProducts(1).getProductTitle(1);
        Assert.assertTrue(productTitle.contains(recentlyViewedProductName), "Added product shouldn't equals with addition");
    }
}
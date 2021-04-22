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

    @Test
    public void testRestoreProduct() {
        var basketAfterDeletedProduct = openBasketPage.deleteProduct(0);
        var deletedProductId = basketAfterDeletedProduct.getRecentlyViewedProductId(1);
        var basketAfterRestoreProduct = basketAfterDeletedProduct.restoreProduct(1);
        var restoredProductId = basketAfterRestoreProduct.getProductId(1);
        assertEquals(restoredProductId, deletedProductId);
    }
}

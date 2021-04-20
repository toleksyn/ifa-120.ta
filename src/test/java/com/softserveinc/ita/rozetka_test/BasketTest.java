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
    public void testRestoreDeletedOrder() {
        openBasketPage.deleteGoods(0);
//        var deletedGoodsLink = openBasketPage.getDeletedFromBasketGoodsLink(0);
//        var restoredGoodsLink = openBasketPage.restoreDeletedGoods(0).getGoodsInBasketLink(0);

        assertTrue(openBasketPage.getDeletedFromBasketGoodsLink(0).equals(
                openBasketPage.restoreDeletedGoods(0).getGoodsInBasketLink(0)
        ));
    }
}

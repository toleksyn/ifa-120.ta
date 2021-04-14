package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.OrderPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderTest extends TestRunner {

    private OrderPage openOrderPage;
    private BasketPage openBasketPage;
    private String searchString;

    @BeforeMethod
    public void addProductToBasketOpenOrder() {
        openOrderPage = new HomePage()
                .openHomePage()
                .openProductByNumber(1)
                .addProductToBasket()
                .openOrderPage();
    }

    @Test
    public void testEditOrder() {
        int orderProductCount;
        int orderSum;
        int changedOrderProductCount;
        int changedOrderSum;

        openOrderPage.startEditingProductInBasket();
        openBasketPage = new BasketPage();
        orderProductCount = openBasketPage.getProductCount();
        orderSum = openBasketPage.getOrderProductPrice();
        openBasketPage.increaseProductCount();
        changedOrderProductCount = openBasketPage.getProductCount();
        changedOrderSum = openBasketPage.getOrderProductPrice();

        Assert.assertEquals(changedOrderProductCount, 2 * orderProductCount);
        Assert.assertEquals(changedOrderSum, 2 * orderSum);

        openBasketPage.openOrderPage();
        openOrderPage.startEditingProductInBasket();
        openBasketPage.decreaseProductCount();
        changedOrderProductCount = openBasketPage.getProductCount();
        changedOrderSum = openBasketPage.getOrderProductPrice();

        Assert.assertEquals(changedOrderProductCount, orderProductCount);
        Assert.assertEquals(changedOrderSum, orderSum);

        openBasketPage.openOrderPage();
    }
}

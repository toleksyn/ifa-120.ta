package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.OrderPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderTest extends TestRunner {

    //This added for future tests
    private OrderPage openOrderPage;
    private BasketPage openBasketPage;

    //This added for future tests
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

        openBasketPage = openOrderPage.startEditingProductsInBasket();

        int orderProductCount = 0;
        orderProductCount = openBasketPage.getProductCount(1);
        int orderProductSum = 0;
        orderProductSum = openBasketPage.getOrderProductSum(1);

        int changedOrderProductCount = 0;
        int changedOrderProductSum = 0;
        openBasketPage.increaseProductCount(1);
        changedOrderProductCount = openBasketPage.getProductCount(1);
        changedOrderProductSum = openBasketPage.getOrderProductSum(1);

        Assert.assertEquals(changedOrderProductCount, 2 * orderProductCount, "the new count for the product " +
                        "must be twice as much as the previous one");
        Assert.assertEquals(changedOrderProductSum, 2 * orderProductSum, "new amount for the product" +
                        " must be twice as much as the previous one");

        openBasketPage.openOrderPage();
        openOrderPage.startEditingProductsInBasket();

        openBasketPage.decreaseProductCount(1);
        changedOrderProductCount = openBasketPage.getProductCount(1);
        changedOrderProductSum = openBasketPage.getOrderProductSum(1);

        openBasketPage.openOrderPage();
        Assert.assertEquals(changedOrderProductCount, orderProductCount, "the new count for the product " +
                "must be twice less than the previous one");
        Assert.assertEquals(changedOrderProductSum, orderProductSum, "the new amount for the product " +
                "must be twice less than the previous one");
    }
}

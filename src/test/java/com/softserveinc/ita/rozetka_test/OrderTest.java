package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.OrderPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class OrderTest extends TestRunner {

    private OrderPage orderPage;
    private BasketPage basketPage;

    @BeforeMethod
    public void addProductToBasketOpenOrder() {
        orderPage = new HomePage()
                .openHomePage()
                .openProductByNumber(1)
                .addProductToBasket()
                .openOrderPage();

        assertEquals(orderPage.getHeaderText(), "Оформлення замовлення", "Page header should be" +
                " 'Оформлення замовлення'");
    }

    @Test
    public void testEditOrder() {

        basketPage = orderPage.startEditingProductsInBasket();

        var orderProductCount = basketPage.getProductCount(1);
        var orderProductSum = basketPage.getOrderProductSum(1);

        basketPage.increaseProductCount(1);
        var changedOrderProductCount = basketPage.getProductCount(1);
        var changedOrderProductSum = basketPage.getOrderProductSum(1);

        assertEquals(changedOrderProductCount, 2 * orderProductCount, "the new count for the product " +
                "should be twice as much as the previous one");
        assertEquals(changedOrderProductSum, 2 * orderProductSum, "new amount for the product" +
                " should be twice as much as the previous one");

        basketPage.openOrderPage().startEditingProductsInBasket();

        basketPage.decreaseProductCount(1);

        changedOrderProductCount = basketPage.getProductCount(1);
        changedOrderProductSum = basketPage.getOrderProductSum(1);

        basketPage.openOrderPage();
        assertEquals(changedOrderProductCount, orderProductCount, "the new count for the product " +
                "should be twice less than the previous one");
        assertEquals(changedOrderProductSum, orderProductSum, "the new amount for the product " +
                "should be twice less than the previous one");
    }
}

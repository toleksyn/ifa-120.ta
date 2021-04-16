package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.OrderPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class OrderTest extends TestRunner {

    private OrderPage OrderPage;
    private BasketPage BasketPage;

    @BeforeMethod
    public void addProductToBasketOpenOrder() {
        OrderPage = new HomePage()
                .openHomePage()
                .openProductByNumber(1)
                .addProductToBasket()
                .openOrderPage();

        assertEquals(OrderPage.getHeaderText(), "Оформлення замовлення", "Page header should be" +
                " 'Оформлення замовлення'");
    }

    @Test
    public void testEditOrder() {

        BasketPage = OrderPage.startEditingProductsInBasket();

        var orderProductCount = BasketPage.getProductCount(1);
        var orderProductSum = BasketPage.getOrderProductSum(1);

        BasketPage.increaseProductCount(1);
        var changedOrderProductCount = BasketPage.getProductCount(1);
        var changedOrderProductSum = BasketPage.getOrderProductSum(1);

        assertEquals(changedOrderProductCount, 2 * orderProductCount, "the new count for the product " +
                "should be twice as much as the previous one");
        assertEquals(changedOrderProductSum, 2 * orderProductSum, "new amount for the product" +
                " should be twice as much as the previous one");

        BasketPage.openOrderPage()
                .startEditingProductsInBasket();

        BasketPage.decreaseProductCount(1);

        changedOrderProductCount = BasketPage.getProductCount(1);
        changedOrderProductSum = BasketPage.getOrderProductSum(1);

        BasketPage.openOrderPage();
        assertEquals(changedOrderProductCount, orderProductCount, "the new count for the product " +
                "should be twice less than the previous one");
        assertEquals(changedOrderProductSum, orderProductSum, "the new amount for the product " +
                "should be twice less than the previous one");
    }
}

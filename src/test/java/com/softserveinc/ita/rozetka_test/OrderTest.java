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
    public void putProductToBasketOpenOrder() {

        openOrderPage = new HomePage()
                .openHomePage()
                .navigateToFirstProduct()
                .putProductOpenBasket()
                .openOrderPage();

    }

    @Test
    public void testEditOrder() {
        int orderQuantity;
        int orderSum;
        openOrderPage.editItemInBasket();
        openBasketPage = new BasketPage();
        orderSum = openBasketPage.getOrderItemPrice();
        openBasketPage.pushPlusItem();
        openBasketPage.openOrderPage();
        openOrderPage.editItemInBasket();
        openBasketPage.pushMinusItem();
        openBasketPage.openOrderPage();
    }

    @Test
    public void testOpenOrderPage() {
        Assert.assertTrue(openOrderPage.getPageTitle().equals("Оформлення замовлення"), "The page title shouldn't match the condition");
    }
}

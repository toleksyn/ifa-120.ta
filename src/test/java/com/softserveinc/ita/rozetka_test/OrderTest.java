package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.OrderPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class OrderTest extends TestRunner {

    //This added for future tests
    private OrderPage orderPage;
    private BasketPage basketPage;

    //This added for future tests
    @BeforeMethod
    public void addProductToBasketOpenOrder() {
        orderPage = new HomePage()
                .openHomePage()
                .openProductByNumber(1)
                .addProductToBasket()
                .openOrderPage();
    }

    @Test
    public void testOpenOrderPage() {
        var pageTitle = openOrderPage.getPageTitle();
        assertTrue(!pageTitle.isEmpty(), "The title shouldn't be empty");
        assertTrue(pageTitle.equals("Оформлення замовлення"), "The page title should match the condition");
    }
}

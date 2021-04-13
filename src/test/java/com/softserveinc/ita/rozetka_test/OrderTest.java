package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.OrderPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderTest extends TestRunner {
    private BasketPage basketPage;
    private OrderPage orderPage;

    @BeforeMethod
    public void openHomepage() {
        basketPage = new HomePage()
                .openHomePage()
                .openFirstProductPage()
                .addProductToBasket();
    }

    @AfterMethod
    public void clearBasket() {

    }

    @Test
    public void testOrderProductTest() {
        orderPage = basketPage.openOrderPage().fillAllInputFields();
    }
}

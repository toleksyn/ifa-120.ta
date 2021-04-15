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

    public OrderTest() {
    }

    //This added for future tests
    @BeforeMethod
    public void addProductToBasketOpenOrder() {
        openOrderPage = new HomePage()
                .openHomePage()
                .openProductByNumber(1)
                .addProductToBasket()
                .openOrderPage();
    }
}

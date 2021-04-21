package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.OrderPage;
import com.softserveinc.ita.rozetka.page_objects.ShippingAddress;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
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
    public void testAddingShippingAddress() {
        var shippingAddress = ShippingAddress
                .builder()
                .city("Львів")
                .name("Вася")
                .surname("Петренко")
                .phone("0997003330")
                .build();
        var isConfirmOrderButtonDisplayed = orderPage
                .setShippingAddress(shippingAddress)
                .isConfirmOrderButtonDisplayed();

        assertTrue(isConfirmOrderButtonDisplayed, "The Confirm Button should be displayed");
        assertEquals(OrderPage.getSurname(), shippingAddress.getSurname(), "Incorrect surname");
        assertEquals(OrderPage.getName(), shippingAddress.getName(), "Incorrect name");
        assertTrue(OrderPage.getCity().contains(shippingAddress.getCity().toLowerCase()), "Incorrect city");
        assertEquals(OrderPage.getPhone(), "+38" + shippingAddress.getPhone(), "Incorrect phone");
    }
}

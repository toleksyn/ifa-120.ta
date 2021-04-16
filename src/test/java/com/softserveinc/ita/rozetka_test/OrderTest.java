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
    public void testShippingAddressInsertion() {
        var shippingAddress = new ShippingAddress("Петренко", "Вася", "Львів", "0997003330");

        var isDisplayedConfirmOrderButton = orderPage
                .setShippingAddress(shippingAddress)
                .isConfirmOrderButtonDisplayed();

        assertTrue(isDisplayedConfirmOrderButton, "The Confirm Button should be displayed");
        assertEquals(OrderPage.getSurname(), shippingAddress.getSurname(), "The surname should be "
                + shippingAddress.getSurname());
        assertEquals(OrderPage.getName(), shippingAddress.getName(), "The name should be "
                + shippingAddress.getName());
        assertTrue(OrderPage.getCity().contains(shippingAddress.getCity().toLowerCase()), "The city should be "
                + shippingAddress.getCity());
        assertEquals(OrderPage.getPhone(), "+38" + shippingAddress.getPhone(), "The phone should be +38"
                + shippingAddress.getPhone());
    }
}

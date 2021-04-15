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
    private String searchString;

    //This added for future tests
    @BeforeMethod
    public void putProductToBasketOpenOrder() {
        openOrderPage = new HomePage()
                .openHomePage()
                .openProductByNumber(1)
                .addProductToBasket()
                .openOrderPage();
    }

    @Test
    public void insertBuyersCredentialsTest() {
        String surname = "Петренко";
        String name = "Вася";
        String city = "Львів";
        String phone = "0997003330";
        boolean isDisplayedConfirmOrderButton = openOrderPage
                .setName(name)
                .setSurname(surname)
                .setCity(city)
                .setPhone(phone)
                .isDisplayedConfirmOrderButton();
        Assert.assertTrue(isDisplayedConfirmOrderButton, "The Comfirm Button should be displayed");
        Assert.assertEquals(openOrderPage.getSurname(), surname, "The surname should be " + surname);
        Assert.assertEquals(openOrderPage.getName(), name, "The name should be " + name);
        Assert.assertTrue(openOrderPage.getCity().contains(city.toLowerCase()), "The city should be " + city);
        Assert.assertEquals(openOrderPage.getPhone(), "+38" + phone, "The phone should be +38" + phone);
    }
}

package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.OrderPage;
import com.softserveinc.ita.rozetka.utility_class.ShippingAddress;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
        assertEquals(orderPage.getSurname(), shippingAddress.getSurname(), "Incorrect surname");
        assertEquals(orderPage.getName(), shippingAddress.getName(), "Incorrect name");
        assertTrue(orderPage.getCity().contains(shippingAddress.getCity().toLowerCase()), "Incorrect city");
        assertEquals(orderPage.getPhone(), "+38" + shippingAddress.getPhone(), "Incorrect phone");
    }

    @Test
    public void testDeletingProductFromBasket() {   // Popup basket
        basketPage = orderPage
                .openProductPage(1)
                .getHeader()
                .openHomePageByLogo()
                .getHeader()
                .openBasketPage()
                .deleteAllProducts();
        assertTrue(basketPage.isBasketEmpty(), "Basket should be empty");
    }

    @Test
    public void testIncreasingAndDecreasingProductsCountInBasket() {
        basketPage = orderPage.startEditingProductsInBasket();

        var orderProductCount = basketPage.getProductCount(1);
        var orderProductSum = basketPage.getOrderProductSum(1);

        basketPage.increaseProductCount(1);
        var increasedOrderProductCount = basketPage.getProductCount(1);
        var increasedOrderProductSum = basketPage.getOrderProductSum(1);

        basketPage.openOrderPage();
        assertEquals(increasedOrderProductCount, orderProductCount * 2,
                "The new count for the product should be twice as much as the previous one");
        assertEquals(increasedOrderProductSum, orderProductSum * 2,
                "New sum for the product should be twice as much as the previous one");

        orderPage.startEditingProductsInBasket();

        basketPage.decreaseProductCount(1);
        var decreasedOrderProductCount = basketPage.getProductCount(1);
        var decreaseOrderProductSum = basketPage.getOrderProductSum(1);

        basketPage.openOrderPage();
        assertEquals(decreasedOrderProductCount, increasedOrderProductCount / 2,
                "The new count for the product should be twice less than the previous one");
        assertEquals(decreaseOrderProductSum, increasedOrderProductSum / 2,
                "The new sum for the product should be twice less than the previous one");
    }

    @Test
    public void testOpenOrderPage() {
        var pageTitle = orderPage.getPageTitle();
        assertTrue(!pageTitle.isEmpty(), "The title shouldn't be empty");
        assertTrue(pageTitle.equals("Оформлення замовлення"), "The page title should match the condition");
        var name = "Андрій";
        var surname = "Гура";
        orderPage.setName(name).setSurName(surname);
        assertTrue(orderPage.getName().equals(name), "Name shouldn't equals with entered");
        assertTrue(orderPage.getSurname().equals(surname), "Surname shouldn't equals with entered");
    }
}

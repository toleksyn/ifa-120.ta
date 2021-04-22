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
    public void testDeletingProductFromBasket() {   // Popup basket
        basketPage = orderPage
                .openProductPage(1)
                .openHomePageByLogo()
                .openBasketPage()
                .deleteAllProducts();
        assertTrue(basketPage.isBasketEmpty(), "Basket should be empty");
    }

    @Test
    public void testEditOrder() {
        basketPage = orderPage.startEditingProductsInBasket();

        var orderProductCount = basketPage.getProductCount(1);
        var orderProductSum = basketPage.getOrderProductSum(1);

        basketPage.increaseProductCount(1);
        var changedOrderProductCount = basketPage.getProductCount(1);
        var changedOrderProductSum = basketPage.getOrderProductSum(1);

        basketPage.openOrderPage();
        assertEquals(changedOrderProductCount, orderProductCount * 2,
                "The new count for the product should be twice as much as the previous one");
        assertEquals(changedOrderProductSum, orderProductSum * 2,
                "New amount for the product should be twice as much as the previous one");

        orderProductCount = changedOrderProductCount;
        orderProductSum = changedOrderProductSum;
        orderPage.startEditingProductsInBasket();

        basketPage.decreaseProductCount(1);
        changedOrderProductCount = basketPage.getProductCount(1);
        changedOrderProductSum = basketPage.getOrderProductSum(1);

        basketPage.openOrderPage();
        assertEquals(changedOrderProductCount, orderProductCount / 2,
                "The new count for the product should be twice less than the previous one");
        assertEquals(changedOrderProductSum, orderProductSum / 2,
                "The new amount for the product should be twice less than the previous one");
    }
}

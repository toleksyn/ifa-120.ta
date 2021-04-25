package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.Header;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.ProductPageTab;
import com.softserveinc.ita.rozetka.page_objects.SortingOption;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static java.lang.String.format;

import static java.lang.String.format;
import static org.testng.Assert.*;

public class RozetkaProductNavigationTest extends TestRunner {

    private Header rozetkaHeader;

    @BeforeMethod
    public void openHomepage() {
        rozetkaHeader = new HomePage().openHomePage().getHeader();
    }

    @Test
    public void testSelectProductBySearch() {
        var productName = "гаманець";
        var productTitle = rozetkaHeader
                .searchFor(productName)
                .openProductByNumber(1)
                .getProductTitle();
        assertTrue(productTitle.contains(productName), "Incorrect product title");
    }

    @Test
    public void testFilterProductsList() {
        var productName = "бензопила";
        var filterType = "Ланцюгова пила";
        var characteristicType = "Вид";
        var characteristicTypeText = rozetkaHeader
                .searchFor(productName)
                .filterProductsList(filterType)
                .openProductByNumber(1)
                .openProductTab(ProductPageTab.CHARACTERISTICS)
                .getCharacteristicText(characteristicType);
        assertEquals(characteristicTypeText, filterType, "Incorrect product characteristic");
    }

    @Test
    public void testSortByPrice() {
        var productListPage = rozetkaHeader
                .searchFor("сокира")
                .setSortingType(SortingOption.CHEAP);
        var lastProductNumber = productListPage.getProductsAmount();
        var firstProductPrice = productListPage.getPriceFromProduct(1);
        var lastProductPrice = productListPage.getPriceFromProduct(lastProductNumber);
        var middleProductPrice = productListPage.getPriceFromProduct(lastProductNumber / 2);
        assertTrue(middleProductPrice < lastProductPrice,
                "Incorrect products sorting by price at the end of list");
        assertTrue(firstProductPrice < middleProductPrice,
                "Incorrect product sorting by price at the beginning of list");
    }

    @Test
    public void testFirstProductInViewedProductsList() {
        var productPage = rozetkaHeader
                .searchFor("галстук");
        var productName = productPage.getProductName(1);
        var firstViewedProductName = productPage
                .openProductByNumber(1)
                .getViewedProductName(1);
        assertTrue(productName.contains(firstViewedProductName), "First viewed product name is incorrect");
    }

    @Test
    public void testNextPreviousPagePagination() {
        var productListPage = rozetkaHeader
                .searchFor("мисливський ніж");
        var lastProductNumber = productListPage.getProductsAmount();
        var firstProductName = productListPage.getProductName(1);
        var lastProductName = productListPage.getProductName(lastProductNumber);
        var middleProductName = productListPage.getProductName(lastProductNumber / 2);
        var currentPageNumber = productListPage.getCurrentPageNumber();
        productListPage.openNextPage();
        assertNotEquals(firstProductName, productListPage.getProductName(1),
                "First product name on the next page is incorrect");
        assertNotEquals(lastProductName, productListPage.getProductName(lastProductNumber),
                "Last product name on the next page is incorrect");
        assertNotEquals(middleProductName, productListPage.getProductName(lastProductNumber / 2),
                "Middle product name on the next page is incorrect");
        assertNotEquals(productListPage.getCurrentPageNumber(), currentPageNumber,
                "Next page number is incorrect");
        productListPage.openPreviousPage();
        assertEquals(firstProductName, productListPage.getProductName(1),
                "First product name on the previous page is incorrect");
        assertEquals(lastProductName, productListPage.getProductName(lastProductNumber),
                "Last product name on the previous page is incorrect");
        assertEquals(middleProductName, productListPage.getProductName(lastProductNumber / 2),
                "Middle product name on the previous page is incorrect");
        assertEquals(productListPage.getCurrentPageNumber(), currentPageNumber,
                "Previous page number is incorrect");
    }
}


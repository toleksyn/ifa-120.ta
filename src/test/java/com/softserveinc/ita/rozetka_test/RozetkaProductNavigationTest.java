package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.ProductPageTab;
import com.softserveinc.ita.rozetka.page_objects.SortingOption;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import static java.lang.String.format;

public class RozetkaProductNavigationTest extends TestRunner {

    private HomePage rozetkaHomePage;

    @BeforeMethod
    public void openHomepage() {
        rozetkaHomePage = new HomePage().openHomePage();
    }

    @Test
    public void testAddingProductToBasket() {
        var productPage = rozetkaHomePage
                .getLeftSidebar()
                .openCategory("Сантехніка")
                .openProductsListPage("Ванни")
                .openProductByNumber(1);
        var productTitle = productPage.getProductTitle();
        var basketPage = productPage.addProductToBasket();
        assertEquals(basketPage.getProductTitleByName("Ванна"), productTitle,
                "Product should be added to basket");
    }

    @Test
    public void testSelectProductBySearch() {
        var productName = "гаманець";
        var productTitle = rozetkaHomePage
                .getHeader()
                .searchFor(productName)
                .getHeader()
                .searchFor(productName)
                .openProductByNumber(1)
                .getProductTitle();
        assertTrue(productTitle.contains(productName), "Incorrect product title");
    }

    @Test
    public void testFilterProductsList() {
        var productName = "віскі";
        var filterType = "Віскі односолодовий";
        var characteristicType = "Вид";
        var characteristicTypeText = rozetkaHomePage
                .getHeader()
                .searchFor(productName)
                .getAgeConfirmationPopup()
                .acceptAdultAge()
                .filterProductsList(filterType)
                .openProductByNumber(1)
                .openProductTab(ProductPageTab.CHARACTERISTICS)
                .getCharacteristicText(characteristicType);
        assertEquals(characteristicTypeText, filterType, "Incorrect product characteristic");
    }

    @Test
    public void testResultsOnProductsListPage() {
        var productCategoryName = "Ноутбуки";
        var productListPage = rozetkaHomePage
                .getLeftSidebar()
                .openCategory("Ноутбуки та комп’ютери")
                .openProductsListPage(productCategoryName);
        assertEquals(productListPage.getPageTitle(), productCategoryName, format("Title should be '%s'", productCategoryName));
        var expectedProductType = "Ноутбук";
        assertTrue(productListPage.getProductName(1).contains(expectedProductType),
                format("First product should be '%s'", expectedProductType));
        var productCount = productListPage.getProductListSize();
        assertTrue(productListPage.getProductName(productCount / 2).contains(expectedProductType),
                format("Middle product should be '%s'", expectedProductType));
        assertTrue(productListPage.getProductName(productCount).contains(expectedProductType),
                format("Last product should be '%s'", expectedProductType));
    }

    @Test
    public void testSortByPrice() {
        var productListPage = rozetkaHomePage
                .getHeader()
                .searchFor("сокира")
                .setSortingType(SortingOption.CHEAP);
        var productsAmount = productListPage.getProductListSize();
        var firstProductPrice = productListPage.getProductPrice(1);
        var lastProductPrice = productListPage.getProductPrice(productsAmount);
        var middleProductPrice = productListPage.getProductPrice(productsAmount / 2);
        assertTrue(middleProductPrice < lastProductPrice && firstProductPrice < middleProductPrice,
                "Incorrect products sorting by price");
    }

    @Test
    public void testViewedProducts() {
        var productPage = rozetkaHomePage
                .getHeader()
                .searchFor("галстук");
        var productName = productPage.getProductName(1);
        var firstViewedProductName = productPage
                .openProductByNumber(1)
                .getViewedProductName(1);
        assertTrue(productName.contains(firstViewedProductName), "First viewed product name is incorrect");
    }

    @Test
    public void testPageChanging() {
        var productListPage = rozetkaHomePage
                .getHeader()
                .searchFor("мисливський ніж");
        var productsAmount = productListPage.getProductListSize();
        var firstProductName = productListPage.getProductName(1);
        var lastProductName = productListPage.getProductName(productsAmount);
        var middleProductName = productListPage.getProductName(productsAmount/2);
        var currentPageNumber = productListPage.getCurrentPageNumber();
        productListPage.openNextPage();
        assertFalse(productListPage.getResultNameByNumber(1).equals(firstResultName) &&
                productListPage.getResultNameByNumber(lastNumber).equals(lastResultName) &&
                productListPage.getResultNameByNumber(lastNumber / 2).equals(middleResultName) &&
                currentPageNumber == productListPage.getCurrentPageNumber());
        productListPage.openPreviousResultPage();
        assertTrue(productListPage.getResultNameByNumber(1).equals(firstResultName) &&
                productListPage.getResultNameByNumber(lastNumber).equals(lastResultName) &&
                productListPage.getResultNameByNumber(lastNumber / 2).equals(middleResultName) &&
                currentPageNumber == productListPage.getCurrentPageNumber());
    }
}


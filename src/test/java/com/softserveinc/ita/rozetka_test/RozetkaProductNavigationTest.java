package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.ProductPageTab;
import com.softserveinc.ita.rozetka.page_objects.SortingOption;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
                .getHeaderPage()
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
                .getHeaderPage()
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
        assertTrue(productListPage.getProductNameForProduct(1).contains(expectedProductType),
                format("First product should be '%s'", expectedProductType));
        var productCount = productListPage.getProductListSize();
        assertTrue(productListPage.getProductNameForProduct(productCount / 2).contains(expectedProductType),
                format("Middle product should be '%s'", expectedProductType));
        assertTrue(productListPage.getProductNameForProduct(productCount).contains(expectedProductType),
                format("Last product should be '%s'", expectedProductType));
    }

    @Test
    public void testSortByPrice() {
        var productListPage = rozetkaHomePage
                .getHeaderPage()
                .searchFor("сокира")
                .setSortingType(SortingOption.CHEAP);
        var productsAmount = productListPage.getProductListSize();
        var firstProductPrice = productListPage.getProductPriceForProduct(1);
        var lastProductPrice = productListPage.getProductPriceForProduct(productsAmount);
        var middleProductPrice = productListPage.getProductPriceForProduct(productsAmount / 2);
        assertTrue(middleProductPrice < lastProductPrice && firstProductPrice < middleProductPrice,
                "Incorrect products sorting by price");
    }

    @Test
    public void testViewedProducts() {
        var productPage = rozetkaHomePage
                .getHeaderPage()
                .searchFor("галстук");
        var productName = productPage.getProductNameForProduct(1);
        var firstViewedProductName = productPage
                .openProductByNumber(1)
                .getViewedProductName(1);
        Assert.assertTrue(productName.contains(firstViewedProductName), "First viewed product name is incorrect");
    }

//    @Test
//    public void testPageSwitching() {
//        var productListPage = rozetkaHomePage
//                .getHeaderPage()
//                .searchFor("мисливський ніж");
//        var productsAmount = productListPage.getProductListSize();
//        var firstProductName = productListPage.getProductNameForProduct(1);
//        var lastProductName = productListPage.getProductNameForProduct(productsAmount);
//        var middleProductName = productListPage.getProductNameForProduct(productsAmount/2);
//        var currentPageNumber = productListPage.getCurrentPageNumber();
//        productListPage.openNextResultPage();
//        Assert.assertFalse(resultsPage.getResultNameByNumber(1).equals(firstResultName) &&
//                resultsPage.getResultNameByNumber(lastNumber).equals(lastResultName) &&
//                resultsPage.getResultNameByNumber(lastNumber / 2).equals(middleResultName) &&
//                currentPageNumber == resultsPage.getCurrentPageNumber());
//        resultsPage.openPreviousResultPage();
//        Assert.assertTrue(resultsPage.getResultNameByNumber(1).equals(firstResultName) &&
//                resultsPage.getResultNameByNumber(lastNumber).equals(lastResultName) &&
//                resultsPage.getResultNameByNumber(lastNumber / 2).equals(middleResultName) &&
//                currentPageNumber == resultsPage.getCurrentPageNumber());
//    }
}


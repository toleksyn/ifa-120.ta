package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.components.CatalogMenu;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CategoryNavigationTests extends TestRunner {

    private CatalogMenu catalogMenu;

    @BeforeMethod
    public void openHomepage() {
        catalogMenu = new HomePage()
                .openHomePage()
                .getCatalogMenu();
    }

    @Test
    public void testAddingProductToBasket() {
        var productPage = catalogMenu
                .openCategory("Сантехніка")
                .openProductsListPage("Ванни")
                .openProductByNumber(1);
        var productTitle = productPage.getProductTitle();
        var basketPage = productPage.addProductToBasket();
        assertEquals(basketPage.getProductTitleByName("Ванна"), productTitle,
                "Product should be added to basket");
    }

    @Test
    public void testResultsOnProductsListPage() {
        var productCategoryName = "Ноутбуки";
        var productListPage = catalogMenu
                .openCategory("Ноутбуки та комп’ютери")
                .openProductsListPage(productCategoryName);
        var softAssert = new SoftAssert();
        softAssert.assertEquals(productListPage.getPageTitle(), productCategoryName, format("Title should be '%s'", productCategoryName));
        var expectedProductType = "Ноутбук";
        softAssert.assertTrue(productListPage.getProductName(1).contains(expectedProductType),
                format("First product should be '%s'", expectedProductType));
        var productCount = productListPage.getProductsAmount();
        softAssert.assertTrue(productListPage.getProductName(productCount / 2).contains(expectedProductType),
                format("Middle product should be '%s'", expectedProductType));
        softAssert.assertTrue(productListPage.getProductName(productCount).contains(expectedProductType),
                format("Last product should be '%s'", expectedProductType));
        softAssert.assertAll();
    }

    @Test
    public void testPossibilityToFilterProductsByPriceRange() {
        var lowestPrice = 8000;
        var highestPrice = 12000;
        var productsListPage = catalogMenu
                .openCategory("Спорт і захоплення")
                .openProductsListPage("Електротранспорт")
                .setLowestPrice(lowestPrice)
                .setHighestPrice(highestPrice)
                .confirmFilterPriceRange();
        var productsAmount = productsListPage.getProductsAmount();
        var firstProductPrice = productsListPage.getPriceFromProduct(1);
        var isFirstProductPriceInRange = lowestPrice <= firstProductPrice && firstProductPrice <= highestPrice;
        var softAssert = new SoftAssert();
        softAssert.assertTrue(isFirstProductPriceInRange, "First product price should be in range 8000-12000");
        var middleProductPrice = productsListPage.getPriceFromProduct(productsAmount / 2);
        var isMiddleProductPriceInRange = lowestPrice <= middleProductPrice && middleProductPrice <= highestPrice;
        softAssert.assertTrue(isMiddleProductPriceInRange, "Middle product price should be in range 8000-12000");
        var lastProductPrice = productsListPage.getPriceFromProduct(productsAmount);
        var isLastProductPriceInRange = lowestPrice <= lastProductPrice && lastProductPrice <= highestPrice;
        softAssert.assertTrue(isLastProductPriceInRange, "Last product price should be in range 8000-12000");
        softAssert.assertAll();
    }
}

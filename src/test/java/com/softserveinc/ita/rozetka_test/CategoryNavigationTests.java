package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.components.CatalogMenu;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        assertEquals(productListPage.getPageTitle(), productCategoryName, format("Title should be '%s'", productCategoryName));
        var expectedProductType = "Ноутбук";
        assertTrue(productListPage.getProductName(1).contains(expectedProductType),
                format("First product should be '%s'", expectedProductType));
        var productCount = productListPage.getProductsAmount();
        assertTrue(productListPage.getProductName(productCount / 2).contains(expectedProductType),
                format("Middle product should be '%s'", expectedProductType));
        assertTrue(productListPage.getProductName(productCount).contains(expectedProductType),
                format("Last product should be '%s'", expectedProductType));
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
        assertTrue(isFirstProductPriceInRange, "First product price should be in range 8000-12000");
        var middleProductPrice = productsListPage.getPriceFromProduct(productsAmount / 2);
        var isMiddleProductPriceInRange = lowestPrice <= middleProductPrice && middleProductPrice <= highestPrice;
        assertTrue(isMiddleProductPriceInRange, "Middle product price should be in range 8000-12000");
        var lastProductPrice = productsListPage.getPriceFromProduct(productsAmount);
        var isLastProductPriceInRange = lowestPrice <= lastProductPrice && lastProductPrice <= highestPrice;
        assertTrue(isLastProductPriceInRange, "Last product price should be in range 8000-12000");
    }
}

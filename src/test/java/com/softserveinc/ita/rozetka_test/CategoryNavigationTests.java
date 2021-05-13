package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.components.CatalogMenu;
import com.softserveinc.ita.rozetka.enums.ProductPageTab;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.back;
import static java.lang.String.format;
import static org.testng.Assert.*;

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

    @Test
    public void testProductsListCheckboxFilters() {

        var catalogMenuCategoryName = "Побутова техніка";
        var subCategorySectionName = "Велика побутова техніка";
        var subCategoryName = "Посудомийні машини";
        var productsListPage = catalogMenu.openProductsListByCategoryInCatalogMenu(catalogMenuCategoryName, subCategorySectionName, subCategoryName);

        var firstFilterType = "Спосіб встановлення";
        var firstFilterValue = "Вбудована";
        productsListPage.setCheckBoxFilter(firstFilterType, firstFilterValue);

        var secondFilterType = "Колір корпусу";
        var secondFilterValue = "Нержавіюча сталь";
        productsListPage.setCheckBoxFilter(secondFilterType, secondFilterValue);

        var productsCount = productsListPage.getProductsAmount();
        assertNotEquals(productsCount, 0, "Incorrect products filters parameters");

        SoftAssert softassert = new SoftAssert();
        //next cycle runs 1, 2 times for 1-2 products, and 3 times for products count > 3 (checks first, middle and last product in filtered list)
        for (var productNumber = 1; productNumber <= productsCount; ) {//productNumber = productNumber + productCount / 2) {
            var characteristicTab = productsListPage
                    .openProductByNumber(productNumber)
                    .openProductTab(ProductPageTab.CHARACTERISTICS);
            softassert.assertEquals(characteristicTab.getCharacteristicText(firstFilterType), firstFilterValue, "Incorrect product characteristic");
            softassert.assertEquals(characteristicTab.getCharacteristicText(secondFilterType), secondFilterValue, "Incorrect product characteristic");

            if (productNumber == 1 + productsCount / 2)
                productNumber = productsCount;
            else
                productNumber = productNumber + productsCount / 2;
            if (productsCount == 1) break;
            back();
            back();
        }
        softassert.assertAll();
    }
}

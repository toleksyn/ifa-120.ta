package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.rozetka.enums.ProductPageTab;
import com.softserveinc.ita.rozetka.modules.CatalogMenu;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CategoryNavigationTests {

    private CatalogMenu catalogMenu;

    @BeforeMethod
    public void openHomepage() {
        catalogMenu = new HomePage().openHomePage().getCatalogMenu();
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
    public void testPossibilityViewingProductDescriptions() {
        var productCharacteristicPage = catalogMenu
                .openCategory("Ноутбуки")
                .openProductsListPage("Планшети")
                .openProductByNumber(1)
                .openProductTab(ProductPageTab.CHARACTERISTICS);
        var characteristicsAmount = productCharacteristicPage
                .getCharacteristicsTexts()
                .size();
        assertTrue(characteristicsAmount > 0,
                "product characteristic description should contains at least 1 item");
        var productCommentPageTitle = productCharacteristicPage
                .openProductTab(ProductPageTab.COMMENTS)
                .getProductTabTitle();
        assertTrue(productCommentPageTitle.contains("Відгуки"), "incorrect tab's title");
        var questionsAmount = productCharacteristicPage
                .openProductTab(ProductPageTab.QUESTIONS)
                .getQuestionsAmount();
        assertTrue(questionsAmount > 0, "list size  should contain at least 1 item");
    }

    @Test
    public void testFilterProductsUsingFilterSearch() {
        var filterCategory = "producer";
        var filterName = "buro";
        var tradeEquipmentPage = catalogMenu
                .openCategory("Товари для бізнесу")
                .openProductsListPageWithSubcategories(1);
        var filteredTradeEquipmentPage = tradeEquipmentPage
                .searchInFilterMenu(filterCategory, filterName)
                .filterProductsList(filterName);
        var productsAmount = filteredTradeEquipmentPage.getProductsAmount();
        var firstProductTitle = filteredTradeEquipmentPage
                .getProductName(1)
                .toLowerCase();
        var middleProductTitle = filteredTradeEquipmentPage
                .getProductName(productsAmount / 2)
                .toLowerCase();
        var lastProductTitle = filteredTradeEquipmentPage
                .getProductName(productsAmount)
                .toLowerCase();
        assertTrue(firstProductTitle.contains(filterName), "incorrect filter result");
        assertTrue(middleProductTitle.contains(filterName), "incorrect filter result");
        assertTrue(lastProductTitle.contains(filterName), "incorrect filter result");
    }

    @Test
    public void testNavigatingFromCategoryToSubcategory() {
        var sportAndHobbiesCategoryPage = catalogMenu
                .openCategory("Спорт і захоплення");
        var categoriesAmount = sportAndHobbiesCategoryPage.getCategoriesAmount();
        assertTrue(categoriesAmount > 0, "page should contains at least 1 category");
        var bicyclesPageProductsAmount = sportAndHobbiesCategoryPage
                .openProductsListPageWithSubcategories(1)
                .getProductsAmount();
        assertTrue(bicyclesPageProductsAmount > 0,
                "bicycles page should contains at least 1 product");
    }
}

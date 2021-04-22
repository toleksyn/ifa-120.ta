package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.ProductPageTab;
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
        var searchRequest = "гаманець";
        var productTitle = rozetkaHomePage
                .getHeader()
                .searchFor(searchRequest)
                .openProductByNumber(1)
                .getProductTitle();
        assertTrue(productTitle.contains(searchRequest),
                "Product title should contain search request");
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
    public void testPossibilityViewingProductDescriptions() {
        var productCharacteristicPage = rozetkaHomePage
                .getLeftSidebar()
                .openCategory("Ноутбуки")
                .openProductsListPage("Планшети")
                .openProductByNumber(1)
                .openProductTab(ProductPageTab.CHARACTERISTICS);
        var characteristicsListSize = productCharacteristicPage
                .getCharacteristicsTexts()
                .size();
        assertTrue(characteristicsListSize > 0,
                "product characteristic description should contains at least 1 item");
        var productCommentPageTitle = productCharacteristicPage
                .openProductTab(ProductPageTab.COMMENTS)
                .getProductTabTitle();
        assertTrue(productCommentPageTitle.contains("Відгуки"), "incorrect tab's title");
        var questionsCount = productCharacteristicPage
                .openProductTab(ProductPageTab.QUESTIONS)
                .getQuestionListSize();
        assertTrue(questionsCount > 0, "list size  should contain at least 1 item");
    }

    @Test
    public void testFilterProductsUsingFilterSearch() {
        var filterCategory = "producer";
        var filterName = "buro";
        var productListPage = rozetkaHomePage
                .getLeftSidebar()
                .openCategory("Товари для бізнесу")
                .openProductsListPage(1);
        var filteredProductListPage = productListPage
                .searchInFilterMenu(filterCategory, filterName)
                .filterProductsList(filterName);
        var productsAmount = filteredProductListPage.getProductListSize();
        var firstProductTitle = filteredProductListPage
                .getProductName(1)
                .toLowerCase();
        var middleProductTitle = filteredProductListPage
                .getProductName(productsAmount / 2)
                .toLowerCase();
        var lastProductTitle = filteredProductListPage
                .getProductName(productsAmount)
                .toLowerCase();
        assertTrue(firstProductTitle.contains(filterName), "incorrect filter result");
        assertTrue(middleProductTitle.contains(filterName), "incorrect filter result");
        assertTrue(lastProductTitle.contains(filterName), "incorrect filter result");
    }

    @Test
    public void testNavigatingFromCategoryToSubcategory() {
        var sportAndHobbiesCategoryPage = rozetkaHomePage
                .getLeftSidebar()
                .openCategory("Спорт і захоплення");
        var categoriesCountOnPage = sportAndHobbiesCategoryPage.getCategoriesCount();
        assertTrue(categoriesCountOnPage > 0, "page should contains at least 1 category");
        var bicyclesPageProductsNumber = sportAndHobbiesCategoryPage
                .openSubcategoriesAndProductsListPage(1)
                .getProductListSize();
        assertTrue(bicyclesPageProductsNumber > 0,
                "bicycles page should contains at least 1 product");
    }
}
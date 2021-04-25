package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.Header;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.ProductPageTab;
import com.softserveinc.ita.rozetka.page_objects.SortingOption;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.*;

public class ProductNavigationTest extends TestRunner {

    private Header header;

    @BeforeMethod
    public void openHomepage() {
        header = new HomePage().openHomePage().getHeader();
    }

    @Test
    public void testSelectProductBySearch() {
        var productName = "гаманець";
        var productTitle = header
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
        var characteristicTypeText = header
                .searchFor(productName)
                .filterProductsList(filterType)
                .openProductByNumber(1)
                .openProductTab(ProductPageTab.CHARACTERISTICS)
                .getCharacteristicText(characteristicType);
        assertEquals(characteristicTypeText, filterType, "Incorrect product characteristic");
    }

    @Test
    public void testSortByPrice() {
        var productListPage = header
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
        var productPage = header
                .searchFor("галстук");
        var productName = productPage.getProductName(1);
        var firstViewedProductName = productPage
                .openProductByNumber(1)
                .getViewedProductName(1);
        assertTrue(productName.contains(firstViewedProductName), "First viewed product name is incorrect");
    }

    @Test
    public void testNextPreviousPagePagination() {
        var productListPage = header
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

    @Test
    public void testPossibilityViewingProductDescriptions() {
        var productCharacteristicPage = rozetkaHomePage
                .getLeftSidebar()
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
        var tradeEquipmentPage = rozetkaHomePage
                .getLeftSidebar()
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
        var sportAndHobbiesCategoryPage = rozetkaHomePage
                .getLeftSidebar()
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
package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.enums.ProductPageTab;
import com.softserveinc.ita.rozetka.enums.SortingOption;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.enums.ProductSection.HOT_NEW_PRODUCTS;
import static java.lang.String.format;
import static org.testng.Assert.*;

public class ProductNavigationTest extends TestRunner {

    private Header header;

    @BeforeMethod
    public void openHomepage() {
        header = new HomePage()
                .openHomePage()
                .getHeader();
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
        var productName = "приціл";
        var filterType = "Оптичний";
        var characteristicType = "Тип прицілу";
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
                .getHeader()
                .openHomePageByLogo()
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
    public void testProductMainSectionsPresence() {
        var productsSection = header
                .openHomePageByLogo()
                .openProductsSection(HOT_NEW_PRODUCTS);

        var productsCountInSection = productsSection.getProductsCount();
        var randomProductPosition = Math.max((int) ((Math.random() * 1000 * productsCountInSection) / 1000), 1);

        var productName = productsSection.getProductNameAtPosition(randomProductPosition);
        var productPage = productsSection.openProductAtPosition(randomProductPosition);

        var productTitle = productPage.getProductTitle();
        assertEquals(productTitle, productName, "Incorrect product opened");

        var productSectionsTitles = productPage.getProductSectionsTitleList();
        assertTrue(productSectionsTitles.contains("Також вас можуть зацікавити"),
                "'Також вас можуть зацікавити' section should be present on page");
        assertTrue(productSectionsTitles.contains(format("Опис %s", productTitle)),
                "'Опис' section should be present on page");
        assertTrue(productPage.isReviewSectionPresent(), "Customer review section should be present on page");
        assertTrue(productSectionsTitles.contains(format("Характеристики %s", productTitle)),
                "'Характеристики' section should be present on page");

        var deliveryCityPage = productPage.openDeliveryCityPage();
        var deliveryCityPageHeader = deliveryCityPage.getHeaderText();
        assertEquals(deliveryCityPageHeader, "Виберіть своє місто", "Incorrect page opened");
        var isSubmitButtonDisplayed = deliveryCityPage.isSubmitButtonDisplayed();
        deliveryCityPage.submitDeliveryCity();
        assertTrue(isSubmitButtonDisplayed, "Submit Button should be displayed");
    }
}

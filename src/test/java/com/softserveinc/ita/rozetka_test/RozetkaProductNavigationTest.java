package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
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
    public void testProductMainSectionsPresence() {
        var productsListSize = rozetkaHomePage.getProductsListSize(12);
        var randomProductNumber = (int) ((Math.random() * 1000) * productsListSize / 1000);
        randomProductNumber = randomProductNumber > 1 ? randomProductNumber : 1;
        var randomProductName = rozetkaHomePage.getProductNameByNumber(randomProductNumber);

        var productPage = rozetkaHomePage.openProductByNumber(randomProductNumber);
        var productTitle = productPage.getProductTitle();
        assertEquals(productTitle, randomProductName, "Incorrect product opened");

        List<String> productSectionsTitles = productPage.getProductSectionsTitleList(3);
        assertTrue(productSectionsTitles.contains("Також вас можуть зацікавити"),
                "'Також вас можуть зацікавити' section should be present on page");
        assertTrue(productSectionsTitles.contains("Опис" + " " + productTitle), "'Опис' section should be present on page");
        var ProductReviewCount = productPage.getProductReviewCount();
        assertTrue(productSectionsTitles.contains("Відгуки покупців" + " " + ProductReviewCount) ||
                        productSectionsTitles.contains("Додати відгук до товару"),
                "Customer review section should be present on page");
        assertTrue(productSectionsTitles.contains("Характеристики" + " " + productTitle),
                "'Характеристики' section should be present on page");
        assertTrue(productSectionsTitles.contains("Разом з цим товаром купують"),
                "'Разом з цим товаром купують' section should be present on page");

        var deliveryCityPage = productPage.openDeliveryCityPage();
        var deliveryCityPageHeader = deliveryCityPage.getHeaderText();
        assertEquals(deliveryCityPageHeader, "Виберіть своє місто", "Incorrect page opened");
        var isSubmitButtonDisplayed = deliveryCityPage.isSubmitButtonDisplayed();
        deliveryCityPage.submitDeliveryCity();
        assertTrue(isSubmitButtonDisplayed, "Opened page is not functional");
    }
}


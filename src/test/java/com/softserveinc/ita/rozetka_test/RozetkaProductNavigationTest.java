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
        assertTrue(productListPage.getProductNameForProduct(1).contains(expectedProductType),
                format("First product should be '%s'", expectedProductType));
        var productCount = productListPage.getProductListSize();
        assertTrue(productListPage.getProductNameForProduct(productCount / 2).contains(expectedProductType),
                format("Middle product should be '%s'", expectedProductType));
        assertTrue(productListPage.getProductNameForProduct(productCount).contains(expectedProductType),
                format("Last product should be '%s'", expectedProductType));
    }

    @Test
    public void testSelectProductByCatalog() {
        var pageCategoryName = "Ноутбуки";
        var chosenCategoryPageByCatalog = rozetkaHomePage
                .getLeftSidebar()
                .openCategory(pageCategoryName);
        var categoryTitle = "Комп'ютери";
        assertTrue(chosenCategoryPageByCatalog.getCategoryTitle().contains(categoryTitle),
                "Incorrect page title");
        var chosenProductPage = chosenCategoryPageByCatalog
                .openProductsListPage(pageCategoryName)
                .openProductByName("Ноутбук Apple MacBook Air 13");
        var isProductTitleCorrect = chosenProductPage
                .getProductTitle()
                .contains("Ноутбук Apple MacBook");
        assertTrue(isProductTitleCorrect, "Incorrect product title");
        var chosenCategoryPageByLink = chosenProductPage
                .openCategoryPageByName(pageCategoryName.toLowerCase());
        assertTrue(chosenCategoryPageByLink.getCategoryTitle().contains(categoryTitle),
                "Incorrect page title");
    }

    @Test
    public void testCompareProductPrices() {
        var catalogCategoryPage = rozetkaHomePage
                .getLeftSidebar()
                .openCategory("Знижки");
        var isPageTitleCorrect = catalogCategoryPage
                .getPageTitle()
                .contains("Знижки");
        assertTrue(isPageTitleCorrect, "Incorrect page title");
        var productPage = catalogCategoryPage.openProductByNumber(1);
        var preDiscountPrice = productPage.getPreDiscountPrice();
        var discountPrice = productPage.getDiscountPrice();
        assertTrue(preDiscountPrice > discountPrice,
                "Pre discount price should be bigger than current price with discount");
    }

    @Test
    public void testShowMoreProducts() {
        var chosenProductPage = rozetkaHomePage
                .getLeftSidebar()
                .openCategory("Товари для дому")
                .openProductsListPage("Домашній текстиль");
        var productCount = chosenProductPage.getProductListSize();
        var firstProductName = chosenProductPage.getProductNameForProduct(1);
        var lastProductName = chosenProductPage.getProductNameForProduct(productCount);
        chosenProductPage.showMoreProducts();
        var extendedProductCount = chosenProductPage.getProductListSize();
        var extendedFirstProductName = chosenProductPage.getProductNameForProduct(1);
        var extendedLastProductName = chosenProductPage.getProductNameForProduct(extendedProductCount);
        assertTrue(firstProductName.equals(extendedFirstProductName),
                "First product name should be the same to the first product name after the extended page");
        assertTrue(!lastProductName.equals(extendedLastProductName),
                "Last product name should be different to the last product name after the extended page");
    }
}


package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.components.CatalogMenu;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    public void testSelectProductByCatalogMenu() {
        var categoryName = "Ноутбуки";
        var laptopCategoryPage = catalogMenu.openCategory(categoryName);
        var categoryTitle = "Комп'ютери";
        var isLaptopCategoryPageOpened = laptopCategoryPage
                .getCategoryTitle()
                .contains(categoryTitle);
        assertTrue(isLaptopCategoryPageOpened, "Incorrect page title");
        var laptopProductPage = laptopCategoryPage
                .openProductsListPage(categoryName)
                .openProductByName("Ноутбук Apple MacBook Air 13");
        var isProductTitleCorrect = laptopProductPage
                .getProductTitle()
                .contains("Ноутбук Apple MacBook");
        assertTrue(isProductTitleCorrect, "Incorrect product title");
        var laptopCategoryPageByLink = laptopProductPage.openCategoryPageByLink(categoryName.toLowerCase());
        isLaptopCategoryPageOpened = laptopCategoryPageByLink
                .getCategoryTitle()
                .contains(categoryTitle);
        assertTrue(isLaptopCategoryPageOpened, "Incorrect page title");
    }

    @Test
    public void testCompareDiscountWithPreDiscountPrices() {
        var categoryName = "Ціни";
        var salesCategoryPage = catalogMenu.openCategory(categoryName);
        var isPageTitleCorrect = salesCategoryPage
                .getPageTitle()
                .contains(categoryName);
        assertTrue(isPageTitleCorrect, "Incorrect page title");
        var firstProductPage = salesCategoryPage.openProductByNumber(1);
        var preDiscountPrice = firstProductPage.getPreDiscountPrice();
        var discountPrice = firstProductPage.getDiscountPrice();
        assertTrue(preDiscountPrice > discountPrice,
                "Pre discount price should be bigger than current price with discount");
    }

    @Test
    public void testShowMoreProducts() {
        var productPage = catalogMenu
                .openCategory("Товари для дому")
                .openProductsListPage("Домашній текстиль");
        var productsCount = productPage.getProductsAmount();
        var firstProductName = productPage.getProductName(1);
        var lastProductName = productPage.getProductName(productsCount);
        productPage.showMoreProducts();
        var extendedProductCount = productPage.getProductsAmount();
        var extendedFirstProductName = productPage.getProductName(1);
        var extendedLastProductName = productPage.getProductName(extendedProductCount);
        assertTrue(firstProductName.equals(extendedFirstProductName),
                "First product name should be the same to the first product name after the extended page");
        assertFalse(lastProductName.equals(extendedLastProductName),
                "Last product name should be different to the last product name after the extended page");
    }

    @Test
    public void testCompareCurrentProductPriceWithDiscountInCheaperTogetherMenu() {
        var categoryName = "Ноутбуки";
        var cheaperTogetherMenu = catalogMenu
                .openCategory(categoryName)
                .openProductsListPage(categoryName)
                .openProductByNumber(1)
                .getCheaperTogetherMenu();
        var productName = cheaperTogetherMenu.getProductNameByNumber(1);
        var productPage = cheaperTogetherMenu.openProductByNumber(1);
        var productPageTitle = productPage.getProductTitle();
        assertEquals(productName, productPageTitle,
                "The product name should be the same to the product page title");
        var discountPrice = cheaperTogetherMenu.getDiscountPrice(1);
        var currentPrice = productPage.getDiscountPrice();
        assertTrue(discountPrice < currentPrice,
                "The discount price should be smaller than the current product price");
    }
}

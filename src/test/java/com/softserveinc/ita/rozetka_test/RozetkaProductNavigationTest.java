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
                .getCatalogMenu()
                .openCategoryPage("Сантехніка")
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
                .getCatalogMenu()
                .openCategoryPage("Ноутбуки та комп’ютери")
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
    public void testSelectProductByLeftSidebar() {
        var categoryName = "Ноутбуки";
        var laptopCategoryPage = rozetkaHomePage
                .getCatalogMenu()
                .openCategoryPage(categoryName);
        var categoryTitle = "Комп'ютери";
        var isLaptopCategoryPageCorrect = laptopCategoryPage
                .getCategoryTitle()
                .contains(categoryTitle);
        assertTrue(isLaptopCategoryPageCorrect, "Incorrect page title");
        var laptopProductPage = laptopCategoryPage
                .openProductsListPage(categoryName)
                .openProductByName("Ноутбук Apple MacBook Air 13");
        var isProductTitleCorrect = laptopProductPage
                .getProductTitle()
                .contains("Ноутбук Apple MacBook");
        assertTrue(isProductTitleCorrect, "Incorrect product title");
        var laptopCategoryPageByLink = laptopProductPage
                .openCategoryPageByName(categoryName.toLowerCase());
        isLaptopCategoryPageCorrect = laptopCategoryPageByLink
                .getCategoryTitle()
                .contains(categoryTitle);
        assertTrue(isLaptopCategoryPageCorrect, "Incorrect page title");
    }

    @Test
    public void testCompareDiscountWithPreDiscountPrices() {
        var categoryName = "Знижки";
        var salesCategoryPage = rozetkaHomePage
                .getCatalogMenu()
                .openCategoryPage(categoryName);
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
        var productPage = rozetkaHomePage
                .getCatalogMenu()
                .openCategoryPage("Товари для дому")
                .openProductsListPage("Домашній текстиль");
        var productsCount = productPage.getProductListSize();
        var firstProductName = productPage.getProductName(1);
        var lastProductName = productPage.getProductName(productsCount);
        productPage.showMoreProducts();
        var extendedProductCount = productPage.getProductListSize();
        var extendedFirstProductName = productPage.getProductName(1);
        var extendedLastProductName = productPage.getProductName(extendedProductCount);
        assertTrue(firstProductName.equals(extendedFirstProductName),
                "First product name should be the same to the first product name after the extended page");
        assertTrue(!lastProductName.equals(extendedLastProductName),
                "Last product name should be different to the last product name after the extended page");
    }

    @Test
    public void testSelectProductByCatalog() {
        var catalogMenu = rozetkaHomePage
                .getHeader()
                .openCatalogMenu();
        var makeUpCategory = catalogMenu.scrollToCategory("Краса");
        var subcategoryCount = makeUpCategory.getCategoriesSize();
        assertTrue(subcategoryCount > 0, "Category should contains at least one subcategory");
        var productsListPage = makeUpCategory.openProductsListPage("Догляд за обличчям");
        var isProductListPageCorrect = productsListPage
                .getPageTitle()
                .contains("Косметика для догляду за обличчям");
        assertTrue(isProductListPageCorrect, "Incorrect page title");
    }
}


package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.ProductPageTab;
import com.softserveinc.ita.rozetka.page_objects.SortingOption;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.*;

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
        var productName = "гаманець";
        var productTitle = rozetkaHomePage
                .getHeader()
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
        var characteristicTypeText = rozetkaHomePage
                .getHeader()
                .searchFor(productName)
                .filterProductsList(filterType)
                .openProductByNumber(1)
                .openProductTab(ProductPageTab.CHARACTERISTICS)
                .getCharacteristicText(characteristicType);
        assertEquals(characteristicTypeText, filterType, "Incorrect product characteristic");
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
        var productCount = productListPage.getProductsAmount();
        assertTrue(productListPage.getProductName(productCount / 2).contains(expectedProductType),
                format("Middle product should be '%s'", expectedProductType));
        assertTrue(productListPage.getProductName(productCount).contains(expectedProductType),
                format("Last product should be '%s'", expectedProductType));
    }

    @Test
    public void testSortByPrice() {
        var productListPage = rozetkaHomePage
                .getHeader()
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
        var productPage = rozetkaHomePage
                .getHeader()
                .searchFor("галстук");
        var productName = productPage.getProductName(1);
        var firstViewedProductName = productPage
                .openProductByNumber(1)
                .getViewedProductName(1);
        assertTrue(productName.contains(firstViewedProductName), "First viewed product name is incorrect");
    }

    @Test
    public void testNextPreviousPagePagination() {
        var productListPage = rozetkaHomePage
                .getHeader()
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
        var productsCount = productPage.getProductsAmount();
        var firstProductName = productPage.getProductName(1);
        var lastProductName = productPage.getProductName(productsCount);
        productPage.showMoreProducts();
        var extendedProductCount = productPage.getProductsAmount();
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


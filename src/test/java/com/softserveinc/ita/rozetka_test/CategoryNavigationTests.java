package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.LeftSidebar;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RozetkaCategoryNavigationTests {

    private LeftSidebar rozetkaLeftSidebar;

    @BeforeMethod
    public void openHomepage() {
        rozetkaLeftSidebar = new HomePage().openHomePage().getLeftSidebar();
    }

    @Test
    public void testAddingProductToBasket() {
        var productPage = rozetkaLeftSidebar
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
        var productListPage = rozetkaLeftSidebar
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
    public void testRatingWarrantyPremiumValidation() {
        var productPage = rozetkaLeftSidebar
                .openCategory("Дитячі товари")
                .openProductsListPage("Іграшки")
                .openProductByNumber(1);
        assertTrue(productPage.isRatingDisplayed(), "Rating is not displayed");
        assertTrue(productPage.isWarrantyDisplayed(), "Warranty is not displayed");
        assertTrue(productPage.isPremiumButtonDisplayed(), "Premium Button is not displayed");
    }

    @Test
    public void testSearchProductByCodeValidation() {
        var productPage = rozetkaLeftSidebar
                .openCategory("Дитячі товари")
                .openProductsListPage("Іграшки")
                .openProductByNumber(1);
        var productTitle = productPage.getProductTitle();
        var productCode = productPage.getProductCode();
        var productTitleByCode = productPage
                .openHomePageByLogo()
                .getHeader()
                .searchProductByCode(productCode)
                .getProductTitle();
        assertEquals(productTitle, productTitleByCode, "Product titles should be equal");
    }
}

package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.ProductPageTab;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import static java.lang.String.format;

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
        var productName = "гаманець";
        var productTitle = rozetkaHomePage
                .getHeaderPage()
                .searchFor(productName)
                .openProductByNumber(1)
                .getProductTitle();
        assertTrue(productTitle.contains(productName), "Product title should contain search request");
    }

    @Test
    public void testFilterProductsList() {
        var productName = "віскі";
        var filterType = "Віскі односолодовий";
        var characteristicType = "Вид";
        var characteristicTypeText = rozetkaHomePage
                .getHeaderPage()
                .searchFor(productName)
                .getAgeConfirmationPopup()
                .acceptAdultAge()
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
}


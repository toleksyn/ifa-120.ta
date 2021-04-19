package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.ProductPageTab;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        var searchText = "гаманець";
        var productTitle = rozetkaHomePage
                .getHeaderPage()
                .searchFor(searchText)
                .openProductByNumber(1)
                .getProductTitle();
        assertTrue(productTitle.contains(searchText),
                "Product title should contain search request");
    }

    @Test
    public void testFilterProductsList() {
        var searchText = "віскі";
        var filterType = "Віскі односолодовий";
        var characteristicType = "Вид";
        var characteristicTypeText = rozetkaHomePage
                .getHeaderPage()
                .searchFor(searchText)
                .getAgeConfirmation()
                .acceptAdultAge()
                .filterProductsList(filterType)
                .openProductByNumber(1)
                .openProductTab(ProductPageTab.CHARACTERISTICS)
                .getCharacteristicText(characteristicType);
        assertEquals(characteristicTypeText, filterType, "Product characteristic is incorrect");
    }
}


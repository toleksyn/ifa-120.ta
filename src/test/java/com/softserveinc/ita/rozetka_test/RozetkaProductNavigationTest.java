package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
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
        var searchRequest = "гаманець";
        var productTitle = rozetkaHomePage
                .getHeaderPage()
                .searchFor(searchRequest)
                .openProductByNumber(1)
                .getProductTitle();
        assertTrue(productTitle.contains(searchRequest),
                "Product title should contain search request");
    }

    @Test
    public void testSelectProductByCatalog() {
        var pageCategoryName = "Ноутбуки";
        var chosenCategoryByCatalog = rozetkaHomePage
                .getLeftSidebar()
                .openCategory(pageCategoryName);
        var categoryTitle = "Комп'ютери";
        assertTrue(chosenCategoryByCatalog.getCategoryTitle().contains(categoryTitle),
                "Incorrect page title");
        var chosenProduct = chosenCategoryByCatalog
                .openProductsListPage(pageCategoryName)
                .openProductByName("Ноутбук Apple MacBook Air 13");
        var isProductTitleCorrect = chosenProduct
                .getProductTitle()
                .contains("Ноутбук Apple MacBook");
        assertTrue(isProductTitleCorrect, "Incorrect product title");
        var chosenCategoryByLink = chosenProduct
                .openCategoryPageByName(pageCategoryName.toLowerCase());
        assertTrue(chosenCategoryByLink.getCategoryTitle().contains(categoryTitle),
                "Incorrect page title");
    }
}


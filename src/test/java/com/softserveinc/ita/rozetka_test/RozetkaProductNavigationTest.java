package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.CategoryPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RozetkaProductNavigationTest extends TestRunner {
    private HomePage rozetkaHomePage;

    @BeforeMethod
    public void openHomepage() {
        rozetkaHomePage = new HomePage().openHomePage();
    }

    @Test
    public void testSelectProductByCatalog() {
        CategoryPage catalogCategoryItem = rozetkaHomePage
                .openCategoryPageFromLeftSidebar("Ноутбуки");
        Assert.assertTrue(catalogCategoryItem.getPageTitle().contains("Комп'ютери"),
                "The page title does not match the condition");
        ProductPage chosenProduct = catalogCategoryItem
                .openProductsListPage("Ноутбуки")
                .openProductByName("Ноутбук Apple MacBook Air 13");
        Assert.assertTrue(chosenProduct.getProductTitle().contains("Ноутбук Apple MacBook"),
                "There is no product with this name");
        chosenProduct.returnToCategoryPage(1);
        Assert.assertTrue(catalogCategoryItem.getPageTitle().contains("Комп'ютери"),
                "The page title does not match the condition");
    }

    @Test
    public void testAddingProductToBasket() {
        ProductPage productPage = rozetkaHomePage
                .openCategoryPageFromLeftSidebar("Сантехніка")
                .openProductsListPage("Ванни")
                .openFirstProduct();
        BasketPage basketPage = productPage.addProductToBasket();
        Assert.assertEquals(basketPage.getProductTitleByName("Ванна"), productPage.getProductTitle());
    }

    @Test
    public void testSelectProductBySearch() {
        String productTittle = rozetkaHomePage
                .getHeaderPage()
                .searchFor("гаманець")
                .openProductByNumber(1)
                .getProductTitle();
        Assert.assertTrue(productTittle.toLowerCase().contains("гаманець"), "Search request lead to the wrong product");
    }
}


package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.*;
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
                .navigateToCategoryPage(0);
//        Assert.assertTrue(catalogCategoryItem.getPageTitle().contains("Комп'ютери"));
        ProductPage chosenProduct = catalogCategoryItem
                .navigateToProductCategoryPage(1)
                .navigateToProductByNumber(0);
        Assert.assertTrue(chosenProduct.getProductTitle().contains("Комп'ютер"));
        chosenProduct.returnToCategoryPage(1);
//        Assert.assertTrue(catalogCategoryItem.getPageTitle().contains("Комп'ютери"));
    }

    @Test
    public void testAddingProductToBasket() {
        ProductPage productPage = rozetkaHomePage
                .navigateToCategoryPage(6)
                .navigateToProductsListPage(1)
                .navigateToFirstProduct();
        BasketPage basketPage = productPage.addProductToBasket();
        Assert.assertEquals(basketPage.getFirstProductTitle(), productPage.getProductTitle());
    }

    @Test
    public void testSelectProductBySearch() {
        String productTittle = rozetkaHomePage.getHeaderPage().searchFor("гаманець").navigateToProductByNumber(1).getProductTitle();
        Assert.assertTrue(productTittle.toLowerCase().contains("гаманець"),"Search request lead to the wrong product");
    }
}

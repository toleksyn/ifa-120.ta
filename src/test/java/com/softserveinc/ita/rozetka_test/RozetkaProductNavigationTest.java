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
                .navigateToProductCategoryPage(1)
                .navigateToProductByNumber(0);
        BasketPage basketPage = productPage.buyProduct();
        Assert.assertEquals(basketPage.getFirstProductTitle(), productPage.getProductTitle());
    }
}

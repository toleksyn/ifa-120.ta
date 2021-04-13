package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.RozetkaBasketPage;
import com.softserveinc.ita.rozetka.page_objects.RozetkaCategoryPage;
import com.softserveinc.ita.rozetka.page_objects.RozetkaHomePage;
import com.softserveinc.ita.rozetka.page_objects.RozetkaProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RozetkaProductNavigationTest extends TestRunner {
    private RozetkaHomePage rozetkaHomePage;

    @BeforeMethod
    public void openHomepage() {
        rozetkaHomePage = new RozetkaHomePage().openHomePage();
    }

    @Test
    public void testSelectProductByCatalog() {
        RozetkaCategoryPage catalogCategoryItem = rozetkaHomePage
                .navigateToCategoryPage(0);
        Assert.assertTrue(catalogCategoryItem.getPageTitle().contains("Комп'ютери"));
        RozetkaProductPage chosenProduct = catalogCategoryItem
                .navigateToProductCategoryPage(1)
                .navigateToProductByNumber(0);
        Assert.assertTrue(chosenProduct.getProductTitle().contains("Комп'ютер"));
        chosenProduct.returnToCategoryPage(1);
        Assert.assertTrue(catalogCategoryItem.getPageTitle().contains("Комп'ютери"));
    }

    @Test
    public void testAddingProductToBasket() {
        RozetkaProductPage productPage = rozetkaHomePage
                .navigateToCategoryPage(6)
                .navigateToProductCategoryPage(1)
                .navigateToProductByNumber(0);
        RozetkaBasketPage basketPage = productPage.buyProduct();
        Assert.assertEquals(basketPage.getFirstProductTitle(), productPage.getProductTitle());
    }
}

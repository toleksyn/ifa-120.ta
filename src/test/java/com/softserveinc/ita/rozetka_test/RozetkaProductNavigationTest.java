package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
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
        chosenProduct.returnToCategoryPage(1);
        Assert.assertTrue(catalogCategoryItem.getPageTitle().contains("Комп'ютери"));
    }

    @Test
    public void TestPossibilityViewingProductDescriptions() {
     RozetkaProductPage productPage=rozetkaHomePage.navigateToCategoryPageFromLeftSidebar(1)
             .navigateToConcreteCategotyPage(7)
             .navigateToProductByNumber(1);
    }
}

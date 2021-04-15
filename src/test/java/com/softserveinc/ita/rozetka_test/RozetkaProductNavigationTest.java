package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
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
        rozetkaHomePage = new HomePage()
                .openHomePage();
    }

    @Test
    public void testSelectProductByCatalog() {
        String pageCategoryName = "Ноутбуки";
        CategoryPage catalogCategoryItem = rozetkaHomePage
                .getLeftSidebar()
                .openCategory(pageCategoryName);
        Assert.assertTrue(catalogCategoryItem.getPageTitle().contains("Комп'ютери"),
                "The page title should match the condition");
        ProductPage chosenProduct = catalogCategoryItem
                .openProductsListPage(pageCategoryName)
                .openProductByName("Ноутбук Apple MacBook Air 13");
        Assert.assertTrue(chosenProduct.getProductTitle().contains("Ноутбук Apple MacBook"),
                "The product title should match the condition");
        chosenProduct.openCategoryPageByName("ноутбуки");
        Assert.assertTrue(catalogCategoryItem.getPageTitle().contains("Комп'ютери"),
                "The page title should match the condition");
    }
}


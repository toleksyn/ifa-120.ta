package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class RozetkaProductNavigationTest extends TestRunner {
    private HomePage rozetkaHomePage;

    @BeforeMethod
    public void openHomepage() {
        rozetkaHomePage = new HomePage()
                .openHomePage();
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
        assertTrue(chosenProduct.getProductTitle().contains("Ноутбук Apple MacBook"),
                "Incorrect product title");
        var chosenCategoryByLink = chosenProduct
                .openCategoryPageByName(pageCategoryName.toLowerCase());
        assertTrue(chosenCategoryByLink.getCategoryTitle().contains(categoryTitle),
                "Incorrect page title");
    }
}


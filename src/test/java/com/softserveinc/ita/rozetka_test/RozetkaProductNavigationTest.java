package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RozetkaProductNavigationTest extends TestRunner {

    private HomePage rozetkaHomePage;

    @BeforeMethod
    public void openHomepage() {
        rozetkaHomePage = new HomePage().openHomePage();
    }

    @Test
    public void testSelectProductBySearch() {
        var searchRequest = "гаманець";
        var productTitle = rozetkaHomePage
                .getHeader()
                .searchFor(searchRequest)
                .openProductByNumber(1)
                .getProductTitle();
        assertTrue(productTitle.contains(searchRequest),
                "Product title should contain search request");
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
        assertTrue(productListPage.getProductName(1).contains(expectedProductType),
                format("First product should be '%s'", expectedProductType));
        var productCount = productListPage.getProductListSize();
        assertTrue(productListPage.getProductName(productCount / 2).contains(expectedProductType),
                format("Middle product should be '%s'", expectedProductType));
        assertTrue(productListPage.getProductName(productCount).contains(expectedProductType),
                format("Last product should be '%s'", expectedProductType));
    }
}


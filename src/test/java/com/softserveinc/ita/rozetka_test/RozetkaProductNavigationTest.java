package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RozetkaProductNavigationTest extends TestRunner {

    private HomePage rozetkaHomePage;

    //This added for future tests
    @BeforeMethod
    public void openHomepage() {
        rozetkaHomePage = new HomePage().openHomePage();
    }

    @Test
    public void testSelectProductBySearch() {
        String searchRequest = "гаманець";
        String productTitle = rozetkaHomePage
                .getHeaderPage()
                .searchFor(searchRequest)
                .openProductByNumber(1)
                .getProductTitle();
        Assert.assertTrue(productTitle.toLowerCase().contains(searchRequest), "Product title should contain search request");
    }

}


package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
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
    public void testPossibilityViewingProductDescriptions() {
        ProductPage productPage = rozetkaHomePage.getLeftSidebar
                ("Ноутбуки")
                .openProductsListPage("Планшети")
                .openProductByNumber(1)
                .openProductTabByName("Характеристики");
        int characteristicsListSize = productPage
                .getCharacteristicListSize(1);
        Assert.assertTrue(characteristicsListSize > 0, "product characteristic description" +
                " should contain at least 1 row");
        String productTabsTitle = productPage
                .openProductTabByName("Відгуки")
                .getProductTabsTitle();
        Assert.assertTrue(productTabsTitle.contains("Відгуки"), "product tabs title should contains 'Відгуки'");
        int questionsRowsAmount = productPage
                .openProductTabByName("Питання")
                .getQuestionListSize(1);
        Assert.assertTrue(questionsRowsAmount > 0, "list size  should contain at least 1 text field");

    }

}


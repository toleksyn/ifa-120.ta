package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.ProductPage;
import com.softserveinc.ita.rozetka.page_objects.ProductPageTab;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RozetkaProductNavigationTest extends TestRunner {

    //This added for future tests
    private HomePage rozetkaHomePage;

    //This added for future tests
    @BeforeMethod
    public void openHomepage() {
        rozetkaHomePage = new HomePage()
                .openHomePage();
    }

    @Test
    public void testPossibilityViewingProductDescriptions() {
        ProductPage productPage = rozetkaHomePage
                .getLeftSidebar()
                .openCategory("Ноутбуки")
                .openProductsListPage("Планшети")
                .openProductByNumber(1)
                .openProductTab(ProductPageTab.CHARACTERISTICS);
        int characteristicsListSize = productPage
                .getCharacteristicsTexts()
                .size();
        Assert.assertTrue(characteristicsListSize > 0, "product characteristic description" +
                " should contain at least 1 row");
        String productTabsTitle = productPage
                .openProductTab(ProductPageTab.REVIEWS)
                .getProductTabsTitle();
        Assert.assertTrue(productTabsTitle.contains("Відгуки"), "product tabs title should contains 'Відгуки'");
        int questionsRowsAmount = productPage
                .openProductTab(ProductPageTab.QUESTIONS)
                .getQuestionListSize();
        Assert.assertTrue(questionsRowsAmount > 0, "list size  should contain at least 1 text field");
    }

}


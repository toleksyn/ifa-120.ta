package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RozetkaProductNavigationTest extends TestRunner {

    private HomePage rozetkaHomePage;

    @BeforeMethod
    public void openHomepage() {
        rozetkaHomePage = new HomePage()
                .openHomePage();
    }

    @Test
    public void testAddingProductToBasket() {
        var productPage = rozetkaHomePage
                .getLeftSidebar()
                .openCategory("Сантехніка")
                .openProductsListPage("Ванни")
                .openProductByNumber(1);
        var productTitle = productPage.getProductTitle();
        var basketPage = productPage.addProductToBasket();
        assertEquals(basketPage.getProductTitleByName("Ванна"), productTitle,
                "Product should be added to basket");
    }

    @Test
    public void testSelectProductBySearch() {
        var searchRequest = "гаманець";
        var productTitle = rozetkaHomePage
                .getHeaderPage()
                .searchFor(searchRequest)
                .openProductByNumber(1)
                .getProductTitle();
        assertTrue(productTitle.contains(searchRequest),
                "Product title should contain search request");
    }

    @Test
    public void testPossibilityViewingProductDescriptions() {
        var productPage = rozetkaHomePage
                .getLeftSidebar()
                .openCategory("Ноутбуки")
                .openProductsListPage("Планшети")
                .openProductByNumber(1)
                .openProductTab(ProductPageTab.CHARACTERISTICS);
        var characteristicsListSize = productPage
                .getCharacteristicsTexts()
                .size();
        assertTrue(characteristicsListSize > 0, "product characteristic description" +
                " should contain at least 1 row");
        var productTabsTitle = productPage
                .openProductTab(ProductPageTab.COMMENTS)
                .getProductTabsTitle();
        assertTrue(productTabsTitle.contains("Відгуки"), "product tabs title should contains 'Відгуки'");
        var questionsRowsAmount = productPage
                .openProductTab(ProductPageTab.QUESTIONS)
                .getQuestionListSize();
        assertTrue(questionsRowsAmount > 0, "list size  should contain at least 1 text field");
    }
}


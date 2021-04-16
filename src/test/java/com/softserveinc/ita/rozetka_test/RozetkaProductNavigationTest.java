package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.ProductPage;
import com.softserveinc.ita.rozetka.page_objects.ProductPageTab;

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

    public void testAddingProductToBasket() {
        ProductPage productPage = rozetkaHomePage
                .getLeftSidebar()
                .openCategory("Сантехніка")
                .openProductsListPage("Ванни")
                .openProductByNumber(1);
        String productTitle = productPage.getProductTitle();
        BasketPage basketPage = productPage.addProductToBasket();
        Assert.assertEquals(basketPage.getProductTitleByName("Ванна"), productTitle,
                "Product should be added to basket");
    }

    @Test
    public void testSelectProductBySearch() {
        String searchRequest = "гаманець";
        String productTitle = rozetkaHomePage
                .getHeaderPage()
                .searchFor(searchRequest)
                .openProductByNumber(1)
                .getProductTitle();
        Assert.assertTrue(productTitle.toLowerCase().contains(searchRequest),
                "Product title should contain search request");
    }
}


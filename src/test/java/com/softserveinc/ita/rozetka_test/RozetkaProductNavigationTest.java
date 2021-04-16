package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
<<<<<<< HEAD
import com.softserveinc.ita.rozetka.page_objects.ProductPageTab;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
=======
import com.softserveinc.ita.rozetka.page_objects.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
>>>>>>> origin/development-branch

public class RozetkaProductNavigationTest extends TestRunner {

    private HomePage rozetkaHomePage;

    @BeforeMethod
    public void openHomepage() {
        rozetkaHomePage = new HomePage().openHomePage();
    }

    @Test
<<<<<<< HEAD
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
                .openProductTab(ProductPageTab.REVIEWS)
                .getProductTabsTitle();
        assertTrue(productTabsTitle.contains("Відгуки"), "product tabs title should contains 'Відгуки'");
        var questionsRowsAmount = productPage
                .openProductTab(ProductPageTab.QUESTIONS)
                .getQuestionListSize();
        assertTrue(questionsRowsAmount > 0, "list size  should contain at least 1 text field");
=======
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
>>>>>>> origin/development-branch
    }
}


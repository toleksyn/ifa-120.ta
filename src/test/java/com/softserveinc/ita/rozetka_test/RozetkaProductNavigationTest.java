package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
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
        rozetkaHomePage = new HomePage().openHomePage();
    }

    @Test
    public void testSelectProductByCatalog() {
        String pageCategory = "Ноутбуки";
        CategoryPage catalogCategoryItem = rozetkaHomePage
                .openCategoryPageFromLeftSidebar(pageCategory);
        Assert.assertTrue(catalogCategoryItem.getPageTitle().contains("Комп'ютери"),
                "The page title should match the condition");
        ProductPage chosenProduct = catalogCategoryItem
                .openProductsListPage(pageCategory)
                .openProductByName("Ноутбук Apple MacBook Air 13");
        Assert.assertTrue(chosenProduct.getProductTitle().contains("Ноутбук Apple MacBook"),
                "The product title should match the condition");
        chosenProduct.returnToCategoryPage(1);
        Assert.assertTrue(catalogCategoryItem.getPageTitle().contains("Комп'ютери"),
                "The page title should match the condition");
    }

    @Test
    public void testAddingProductToBasket() {
        ProductPage productPage = rozetkaHomePage
                .openCategoryPageFromLeftSidebar("Сантехніка")
                .openProductsListPage("Ванни")
                .openFirstProduct();
        BasketPage basketPage = productPage.addProductToBasket();
        Assert.assertEquals(basketPage.getProductTitleByName("Ванна"), productPage.getProductTitle(),
                "Added wrong product to basket");
    }

    @Test
    public void testSelectProductBySearch() {
        String productTittle = rozetkaHomePage
                .getHeaderPage()
                .searchFor("гаманець")
                .openProductByNumber(1)
                .getProductTitle();
        Assert.assertTrue(productTittle.toLowerCase().contains("гаманець"), "Search request lead to the wrong product");
    }

    @Test
    public void testPossibilityViewingProductDescriptions() {
        ProductPage productPage = rozetkaHomePage.openCategoryPageFromLeftSidebar("Ноутбуки")
                .openProductsListPage("Планшети")
                .openFirstProduct().openProductTabByName("Характеристики");
        String madeIn = productPage.getCharacteristicDescriptionByIndex(1);
        Assert.assertTrue(madeIn.toLowerCase().contains("Китай"), "field 'Країна виробник' should contains 'Китай'");
        String productTabsTitle = productPage.openProductTabByName("Відгуки").getProductTabsTitle();
        Assert.assertTrue(productTabsTitle.contains("Відгуки"), "Product tabs title should contains 'Відгуки'");
        int commentsList = productPage.openProductTabByName("Питання").getCommentsList().size();
        Assert.assertTrue(commentsList > 0);

    }
}


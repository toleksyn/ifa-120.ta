package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.CategoryPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class RozetkaProductNavigationTest extends TestRunner {
    private HomePage rozetkaHomePage;

    @BeforeMethod
    public void openHomepage() {
        rozetkaHomePage = new HomePage().openHomePage();
    }

    @Test
    public void testSelectProductByCatalog() {
        CategoryPage catalogCategoryItem = rozetkaHomePage
                .openCategoryPageFromLeftSidebar("Ноутбуки");
        Assert.assertTrue(catalogCategoryItem.getPageTitle().contains("Комп'ютери"),
                "'Комп'ютери та ноутбуки' page title contains 'Ноутбуки та комп'ютери'");
        ProductPage chosenProduct = catalogCategoryItem
                .openProductsListPage("Ноутбуки")
                .openProductByName("Ноутбук Apple MacBook Air 13");
        Assert.assertTrue(chosenProduct.getProductTitle().contains("Ноутбук Apple MacBook"),
                "Category 'Ноутбуки' with a list of products displays at least one item");
        chosenProduct.returnToCategoryPage(1);
        Assert.assertTrue(catalogCategoryItem.getPageTitle().contains("Комп'ютери"),
                "'Комп'ютери та ноутбуки' page title contains 'Ноутбуки та комп'ютери'");
    }

    @Test
    public void testAddingProductToBasket() {
        ProductPage productPage = rozetkaHomePage
                .openCategoryPageFromLeftSidebar("Сантехніка")
                .openProductsListPage("Ванни")
                .openFirstProduct();
        BasketPage basketPage = productPage.addProductToBasket();
        Assert.assertEquals(basketPage.getProductTitleByName("Ванна"), productPage.getProductTitle());
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
                .openFirstProduct()
                .openProductTabByName("Характеристики");
        List<String> characteristicsTextList = productPage
                .getCharacteristicsTextList();
        Assert.assertTrue(characteristicsTextList.size() > 0, "product characteristic description" +
                " should contain at least 1 row");
        String productTabsTitle = productPage
                .openProductTabByName("Відгуки")
                .getProductTabsTitle();
        Assert.assertTrue(productTabsTitle.contains("Відгуки"), "product tabs title should contains 'Відгуки'");
        int characteristicsRowsAmount = productPage
                .openProductTabByName("Питання")
                .getCommentsTextList()
                .size();
        Assert.assertTrue(characteristicsRowsAmount > 0, "list size  should contain at least 1 text field");

    }
}


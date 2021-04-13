package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.RozetkaCategoryPage;
import com.softserveinc.ita.rozetka.page_objects.RozetkaHomePage;
import com.softserveinc.ita.rozetka.page_objects.RozetkaProductPage;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$x;

public class RozetkaProductNavigationTest extends TestRunner {
    private RozetkaHomePage rozetkaHomePage;

    @BeforeMethod
    public void openHomepage() {
        rozetkaHomePage = new RozetkaHomePage().openHomePage();
    }


    @Test
    public void testSelectProductByCataloge() {
        WebElement pageTitle = $x("//h1[@class='portal__heading']");
        WebElement productTitle = $x("//h1[@class='product__title']");
        RozetkaCategoryPage catalogeCategoryItem = rozetkaHomePage
                .navigateToCategoryPage(0);
        Assert.assertTrue(pageTitle.getText().contains("Компьютеры"));
        RozetkaProductPage chosenProduct = catalogeCategoryItem
                .navigateToProductCategoryPage(1)
                .navigateToProductByNumber(0);
        Assert.assertTrue(productTitle.getText().contains("Компьютер"));
        chosenProduct.returnToCategoryPage(1);
        Assert.assertTrue(pageTitle.getText().contains("Компьютеры"));
    }

    @Test
    public void TestPossibilityViewingProductDescriptions() {
     RozetkaProductPage productPage=rozetkaHomePage.navigateToCategoryPageFromLeftSidebar(1)
             .navigateToConcreteCategotyPage(7)
             .openProductPageByNumber(1).switchProductDescription(2);
    }
}

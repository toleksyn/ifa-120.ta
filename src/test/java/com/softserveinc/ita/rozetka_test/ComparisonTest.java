package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.ProductsListPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static java.util.stream.IntStream.range;
import static org.testng.Assert.assertNotEquals;

public class ComparisonTest extends TestRunner {

    private ProductsListPage productsListPage;

    @BeforeMethod
    public void addProductsToComparison() {
        productsListPage = new HomePage()
                .openHomePage()
                .getCatalogMenu()
                .openCategory("Товари для геймерів")
                .openProductsListPage("Ігрові приставки")
                .addProductToComparisonByNumber(1)
                .addProductToComparisonByNumber(2);
    }

    @Test
    public void testComparisonSettings() {
        var comparisonPage = productsListPage
                .getHeader()
                .openComparisonPopup()
                .openComparisonPageFromList(1);
        var firstProductCharacteristics = comparisonPage.getCharacteristicForProduct(1);
        var secondProductCharacteristics = comparisonPage.getCharacteristicForProduct(2);

        var allCharacteristicsSize = firstProductCharacteristics.size() + secondProductCharacteristics.size();
        var areCharacteristicsHaveSameItem = range(0, allCharacteristicsSize)
                .anyMatch(index -> firstProductCharacteristics
                        .get(index)
                        .equals(secondProductCharacteristics.get(index)));
        var softAssert = new SoftAssert();
        softAssert.assertTrue(areCharacteristicsHaveSameItem, "Characteristics of two products should have same item");

        comparisonPage.showDifferences();
        var differentFirstProductCharacteristics = comparisonPage.getCharacteristicForProduct(1);
        var differentSecondProductCharacteristics = comparisonPage.getCharacteristicForProduct(2);

        var areAllCharacteristicsDifferent = range(0, differentFirstProductCharacteristics.size())
                .noneMatch(index -> differentFirstProductCharacteristics
                        .get(index)
                        .equals(differentSecondProductCharacteristics.get(index)));
        softAssert.assertTrue(areAllCharacteristicsDifferent, "Characteristics of two products should be different");
        softAssert.assertAll();
        comparisonPage
                .getHeader()
                .openComparisonPopup()
                .deleteComparisonList(1);
    }

    @Test
    public void testAddingProductFromDifferentCategoryToDifferentComparisonList() {
        var comparisonPopup = productsListPage
                .getHeader()
                .openHomePageByLogo()
                .getCatalogMenu()
                .openCategory("Ноутбуки та комп’ютери")
                .openProductsListPage("Планшети")
                .addProductToComparisonByNumber(1)
                .addProductToComparisonByNumber(2)
                .getHeader()
                .openComparisonPopup();
        var firstListName = comparisonPopup.getNameFromList(1);
        var secondListName = comparisonPopup.getNameFromList(2);
        assertNotEquals(secondListName, firstListName, "Name of lists should be different");
        comparisonPopup
                .deleteComparisonList(2)
                .deleteComparisonList(1);
    }
}

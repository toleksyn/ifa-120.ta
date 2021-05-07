package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.ComparisonPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.util.stream.IntStream.range;
import static org.testng.Assert.assertTrue;

public class ComparisonTest extends TestRunner {

    private ComparisonPage comparisonPage;

    @BeforeMethod
    public void addProductsToComparison() {
        comparisonPage = new HomePage()
                .openHomePage()
                .getCatalogMenu()
                .openCategory("Товари для геймерів")
                .openProductsListPage("Ігрові приставки")
                .addProductToComparisonByNumber(1)
                .addProductToComparisonByNumber(2)
                .getHeader()
                .openComparisonPopup()
                .openComparisonPageFromList(1);
    }

    @Test
    public void testComparisonSettings() {
        var firstProductCharacteristics = comparisonPage.getCharacteristicForProduct(1);
        var secondProductCharacteristics = comparisonPage.getCharacteristicForProduct(2);

        var allCharacteristicsSize = firstProductCharacteristics.size() + secondProductCharacteristics.size();
        var isCharacteristicHaveSameItem = range(0, allCharacteristicsSize)
                .anyMatch(index -> firstProductCharacteristics
                        .get(index)
                        .equals(secondProductCharacteristics.get(index)));
        assertTrue(isCharacteristicHaveSameItem, "Characteristics of two products should have same item");

        comparisonPage.showDifferences();
        var differentFirstProductCharacteristics = comparisonPage.getCharacteristicForProduct(1);
        var differentSecondProductCharacteristics = comparisonPage.getCharacteristicForProduct(2);

        var isAllCharacteristicsDifferent = range(0, differentFirstProductCharacteristics.size())
                .noneMatch(index -> differentFirstProductCharacteristics
                        .get(index)
                        .equals(differentSecondProductCharacteristics.get(index)));
        assertTrue(isAllCharacteristicsDifferent, "Characteristics of two products should be different");
import static org.testng.Assert.assertEquals;

public class ComparisonTest extends TestRunner {
    private ComparisonPage openComparisonPage;
    @Test
    public void deleteProductFromComparison() {
        openComparisonPage = new HomePage()
                .openHomePage()
                .getCatalogMenu()
                .openCategory("Товари для дому")
                .openSubcategory("Приготування їжі")
                .openProductsListPage("Сковороди")
                .addProductToComparison(1)
                .addProductToComparison(2)
                .openComparisonPage(1)
                .deleteProduct(1);
        assertEquals(openComparisonPage.getAlertMassage(),
                "Недостатньо товарів для порівняння",
                "Product shouldn't delete from comparison");
    }
}

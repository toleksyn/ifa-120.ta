package com.softserveinc.ita.rozetka_test;

import com.codepoetics.protonpack.Indexed;
import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.ComparisonPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.*;

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
    public void testDeleteProductFromComparison() {
        comparisonPage
                .deleteProduct(1);
        assertEquals(comparisonPage.getAlertMessage(),
                "Недостатньо товарів для порівняння");
    }

    @Test
    public void testShowOnlyDifferenceCharacteristic() {
        comparisonPage.showDifferences();
        List<Indexed<String>> firstProductCharacteristic = comparisonPage
                .getProductCharacteristicsIndexedList(1);
        List<Indexed<String>> secondProductCharacteristic = comparisonPage
                .getProductCharacteristicsIndexedList(2);
        assertNotSame(firstProductCharacteristic, secondProductCharacteristic);
    }
}

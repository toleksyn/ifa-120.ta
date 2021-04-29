package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.rozetka.page_objects.ComparisonPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ComparisonTest {
    ComparisonPage openComparisonPage;
    @BeforeMethod
    public ComparisonPage addTwoProductsToComparison() {
        return openComparisonPage = new HomePage()
                .openHomePage()
                .getCatalogMenu()
                .openCategory("Товари для дому")
                .openProductsListPage("Сковороди")
                .addProductToComparison(1)
                .addProductToComparison(2)
                .openComparisonPage(1);
    }

    @Test
    public void deleteProductFromComparison() {
        openComparisonPage.deleteProduct(1);
        assertEquals(openComparisonPage.getAlertMassage(), "Недостатньо товарів для порівняння");
    }
}

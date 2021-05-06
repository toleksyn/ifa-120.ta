package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.ComparisonPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

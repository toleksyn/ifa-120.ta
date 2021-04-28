package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.rozetka.page_objects.CategoryPage;
import com.softserveinc.ita.rozetka.page_objects.ComparisonPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;

public class ComparisonTest {
    ComparisonPage openComparisonPage;
    @BeforeMethod
    public CategoryPage addTwoProductsToComparison() {
        var productPage = new HomePage()
                .openHomePage()
                .getCatalogMenu()
                .openCategory("Товари для дому")
                .openProductsListPage("Сковороди")
                .
    }
}

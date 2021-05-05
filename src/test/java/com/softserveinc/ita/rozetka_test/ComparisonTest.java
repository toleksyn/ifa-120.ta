package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.ComparisonPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static java.util.stream.IntStream.*;
import static org.testng.Assert.*;

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
                .getComparisonPopup()
                .openComparisonPageByNumber(1);
    }

    @Test
    public void testComparisonSettings() {
        var allCharacteristics = comparisonPage.getCharacteristicList();
        var firstProductCharacteristics = new ArrayList<>();
        var secondProductCharacteristics = new ArrayList<>();

        range(0, allCharacteristics.size())
                .forEach(index -> (index % 2 == 0 ? firstProductCharacteristics : secondProductCharacteristics)
                        .add(allCharacteristics.get(index)));
        var isCharacteristicHaveSameItem = range(0, allCharacteristics.size())
                .anyMatch(index -> firstProductCharacteristics
                        .get(index)
                        .equals(secondProductCharacteristics.get(index)));
        assertTrue(isCharacteristicHaveSameItem, "Characteristics of two products should have same item");

        var differentCharacteristics = comparisonPage
                .openDifferentCharacteristics()
                .getCharacteristicList();
        var differentFirstProductCharacteristics = new ArrayList<>();
        var differentSecondProductCharacteristics = new ArrayList<>();
        range(0, differentCharacteristics.size())
                .forEach(index -> (index % 2 == 0 ? differentFirstProductCharacteristics : differentSecondProductCharacteristics)
                        .add(differentCharacteristics.get(index)));
        var isAllCharacteristicsDifferent = range(0, firstProductCharacteristics.size())
                .noneMatch(index -> differentFirstProductCharacteristics
                        .get(index)
                        .equals(differentSecondProductCharacteristics.get(index)));
        assertTrue(isAllCharacteristicsDifferent, "Characteristics of two products should be different");
    }
}

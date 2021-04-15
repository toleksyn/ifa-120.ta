package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;

public class RozetkaProductNavigationTest extends TestRunner {

    //This added for future tests
    private HomePage rozetkaHomePage;

    //This added for future tests
    @BeforeMethod
    public void openHomepage() {
        rozetkaHomePage = new HomePage()
                .openHomePage();
    }
}


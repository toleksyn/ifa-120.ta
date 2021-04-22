package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HamburgerBar;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;

public class HamburgerTest extends TestRunner {

    //This added for future tests
    private HamburgerBar hamburgerBar;

    //This added for future tests
    @BeforeMethod
    public void openHamburgerBar() {
        hamburgerBar = new HomePage()
                .openHomePage()
                .getHeader()
                .openHamburgerBar();
    }
}

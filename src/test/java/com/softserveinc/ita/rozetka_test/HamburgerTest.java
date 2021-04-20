package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HamburgerPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;

public class HamburgerTest extends TestRunner {

    //This added for future tests
    private HamburgerPage hamburgerPage;

    //This added for future tests
    @BeforeMethod
    public void openHamburgerPage() {
        hamburgerPage = new HomePage()
                .openHomePage()
                .getHeaderPage()
                .openHamburgerPage();
    }
}

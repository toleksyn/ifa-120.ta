package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.Test;

public class Sprint2Test extends TestRunner {

    @Test
    public void testValidateSellerRating() {
    var productPage= new HomePage()
            .openHomePage()
            .getLeftSidebar()
            .openCategory("Алкогольні напої та продукти")
            .openProductByNumber(1)
            .getProductTitle();

    }
}

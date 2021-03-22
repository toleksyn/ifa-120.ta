package com.softserveinc.ita.pageobjects_task.nromanchuk;

import com.softserveinc.ita.nromanchuk.GoogleHomePage;
import org.testng.annotations.Test;

public class GoogleTest {

    @Test
    public void TestGoogleSearch(){
        new GoogleHomePage()
                .open()
                .searchFor();
    }
}

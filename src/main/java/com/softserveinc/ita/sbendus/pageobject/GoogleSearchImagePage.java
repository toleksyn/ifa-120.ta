
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchImagePage {

    public List<String> getListOfSearchTitleResults(int amount) {
        return Selenide.$$x("//h3[@class='LC20lb DKV0Md']")
                .should(CollectionCondition.sizeGreaterThan(amount))
                .stream()
                .map(SelenideElement -> SelenideElement.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage navigateToHomePageByLogo() {
        Selenide.$x("//a[@class='F1hUFe jbTlie']").click();
        return new GoogleHomePage();
    }
}


=======
//package com.softserveinc.ita.sbendus.pageobject;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.softserveinc.ita.common.WebElementUtil.clickElement;
//import static com.softserveinc.ita.common.WebElementUtil.getElementsListAtLeast;
//
//public class GoogleSearchImagePage {
//
//    public List<String> getListOfSearchTitleResults() {
//        return getElementsListAtLeast("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']", 10)
//                .stream()
//                .map(WebElement -> WebElement.getAttribute("title"))
//                .collect(Collectors.toList());
//    }
//
//    public GoogleHomePage navigateToHomePageByLogo() {
//        clickElement("//a[@class='F1hUFe jbTlie']");
//        return new GoogleHomePage();
//    }
//}
//
//
>>>>>>> c821ece0506b3f82a159b45e6c902da2250bfd3d

package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.page_objects.BasketPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.ProductsListPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class Header {

    @Step("Header: search for request {request}")
    public ProductsListPage searchFor(String request) {
        $x("//input[contains(@class, 'search-form__input')]").val(request);
        $x("//button[contains(@class, 'search-form__submit')]").click();
        return new ProductsListPage();
    }

    @Step("Header: open hamburger bar")
    public HamburgerBar openHamburgerBar() {
        $x("(//button[@class='header__button'])[1]").click();
        return new HamburgerBar();
    }

    public String getSearchFieldText() {
        return $x("//input[contains(@class, 'search-form__input')]").attr("placeholder");
    }

    @Step("Header: open basket page")
    public BasketPage openBasketPage() {
        $x("//button[@class='header__button header__button--active']").click();
        return new BasketPage();
    }

    @Step("Header: open home page by logo")
    public HomePage openHomePageByLogo() {
        $x("//*[@class='header__logo']").click();
        return new HomePage();
    }

    @Step("Header: open catalog menu" )
    public CatalogMenu openCatalogMenu() {
        $x(format("//button[contains(@class, 'menu__toggle')]")).click();
        return new CatalogMenu();
    }
}

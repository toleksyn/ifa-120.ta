package com.softserveinc.ita.rozetka.page_objects;
import lombok.Getter;

@Getter
public abstract class BasePage {

    private final Header headerPage = new Header();
    private final Footer footerPage = new Footer();

}

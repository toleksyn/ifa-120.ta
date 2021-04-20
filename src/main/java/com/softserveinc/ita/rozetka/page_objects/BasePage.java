package com.softserveinc.ita.rozetka.page_objects;
import lombok.Getter;

@Getter
public abstract class BasePage {

    private final Header header = new Header();
    private final Footer footer = new Footer();

}

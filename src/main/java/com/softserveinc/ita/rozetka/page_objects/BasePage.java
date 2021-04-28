package com.softserveinc.ita.rozetka.page_objects;

import com.softserveinc.ita.rozetka.components.Footer;
import com.softserveinc.ita.rozetka.components.Header;
import lombok.Getter;

@Getter
public abstract class BasePage {

    private final Header header = new Header();
    private final Footer footer = new Footer();

}

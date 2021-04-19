package com.softserveinc.ita.rozetka.page_objects;

public abstract class BasePage {

    private final Header headerPage = new Header();
    private final Footer footerPage = new Footer();

    public Header getHeaderPage() {
        return headerPage;
    }

    public Footer getFooterPage() {
        return footerPage;
    }
}

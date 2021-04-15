package com.softserveinc.ita.rozetka.page_objects;

public abstract class BasePage {

    private final Header headerPage;
    private final Footer footerPage;

    public BasePage() {
        this.headerPage = new Header();
        this.footerPage = new Footer();
    }

    public Header getHeaderPage() {
        return headerPage;
    }

    public Footer getFooterPage() {
        return footerPage;
    }
}

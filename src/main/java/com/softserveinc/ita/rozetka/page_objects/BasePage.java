package com.softserveinc.ita.rozetka.page_objects;

public abstract class BasePage {

    private final HeaderPage headerPage;
    private final FooterPage footerPage;

    public BasePage() {
        this.headerPage = new HeaderPage();
        this.footerPage = new FooterPage();
    }

    public HeaderPage getHeaderPage() {
        return headerPage;
    }

    public FooterPage getFooterPage() {
        return footerPage;
    }
}

package com.softserveinc.ita.nromanchuk;

import static com.codeborne.selenide.Selenide.$x;

public class BooksResultsPage {

    public String getResultLinkTextByIndex(int index) {
        return $x(String.format("//h3", index)).getText().toLowerCase();
    }
}

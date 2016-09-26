package com.vgen.htmlutils.htmlformatter;

enum HtmlAttribute {
    CLASS("class"),
    HREF("href");

    private final String friendlyName;

    private HtmlAttribute(String value) {
        friendlyName = value;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}

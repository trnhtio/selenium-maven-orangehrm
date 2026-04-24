package org.opencart.PageUIs.portal;

import org.openqa.selenium.By;

public final class DemoPageUI {

    public static final By HERO_TITLE = By.cssSelector("h1");
    public static final By STORE_FRONT_LINK = By.xpath("//a[contains(translate(normalize-space(.), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'STORE FRONT')]");
    public static final By ADMINISTRATION_LINK = By.xpath("//a[contains(translate(normalize-space(.), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'ADMINISTRATION')]");

    private DemoPageUI() {
    }
}

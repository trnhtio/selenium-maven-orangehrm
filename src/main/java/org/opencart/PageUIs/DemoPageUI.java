package org.opencart.PageUIs;

import org.openqa.selenium.By;

public final class DemoPageUI {

    public static final String DEMO_PAGE_URL = "https://www.opencart.com/index.php?route=cms/demo";
    public static final String STORE_FRONT_URL = "https://opencart.chankov.net/4/";
    public static final String REGISTER_PAGE_PATH = "index.php?route=account/register&language=en-gb";
    public static final String LOGIN_PAGE_PATH = "index.php?route=account/login&language=en-gb";
    public static final String LOGOUT_PAGE_PATH = "index.php?route=account/logout&language=en-gb";
    public static final By STORE_FRONT_LINK = By.cssSelector("a[href*='demo.opencart.com']");

    private DemoPageUI() {
    }
}

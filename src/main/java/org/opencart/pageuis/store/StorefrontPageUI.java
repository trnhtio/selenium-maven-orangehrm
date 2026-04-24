package org.opencart.PageUIs.store;

import org.openqa.selenium.By;

public final class StorefrontPageUI {

    public static final By HEADER_BRAND = By.cssSelector("#logo a, header #logo a");
    public static final By SEARCH_INPUT = By.name("search");
    public static final By FEATURED_SECTION = By.cssSelector("#content .product-thumb, #content .swiper-wrapper");

    private StorefrontPageUI() {
    }
}

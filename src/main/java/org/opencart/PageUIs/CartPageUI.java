package org.opencart.PageUIs;

import org.openqa.selenium.By;

public final class CartPageUI {

    public static final By PAGE_TITLE = By.cssSelector("#content h1");
    public static final By PRODUCT_NAMES = By.cssSelector("#content form table tbody td.text-start a, #content .table-responsive tbody td.text-start a");
    public static final By CHECKOUT_LINK = By.cssSelector("#shopping-cart a[href*='route=checkout/checkout'], #checkout-cart a[href*='route=checkout/checkout']");

    private CartPageUI() {
    }
}

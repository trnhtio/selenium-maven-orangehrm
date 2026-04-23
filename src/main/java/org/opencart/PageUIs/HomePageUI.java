package org.opencart.PageUIs;

import org.openqa.selenium.By;

public final class HomePageUI {

    public static final By SEARCH_INPUT = By.name("search");
    public static final By SEARCH_BUTTON = By.cssSelector("#search button[type='button'], #search button[type='submit'], input[name='search'] + button[type='submit']");
    public static final By PRODUCT_NAMES = By.cssSelector("#content .product-thumb h4 a, #content .product-thumb .description h4 a");
    public static final By CART_LINK = By.cssSelector("a[title='Shopping Cart']");

    private HomePageUI() {
    }
}

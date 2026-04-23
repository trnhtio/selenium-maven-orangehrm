package org.opencart.PageUIs;

import org.openqa.selenium.By;

public final class ProductPageUI {

    public static final By PRODUCT_TITLE = By.cssSelector("#content h1");
    public static final By RADIO_SMALL = By.id("input-option-value-5");
    public static final By CHECKBOX_ONE = By.id("input-option-value-8");
    public static final By TEXT_OPTION = By.id("input-option-208");
    public static final By SELECT_OPTION = By.id("input-option-217");
    public static final By TEXTAREA_OPTION = By.id("input-option-209");
    public static final By FILE_OPTION = By.id("input-option-222");
    public static final By DATE_OPTION = By.id("input-option-219");
    public static final By TIME_OPTION = By.id("input-option-221");
    public static final By DATE_TIME_OPTION = By.id("input-option-220");
    public static final By QUANTITY_INPUT = By.name("quantity");
    public static final By ADD_TO_CART_BUTTON = By.id("button-cart");
    public static final By SUCCESS_ALERT = By.cssSelector(".alert-success, #alert .alert-success");
    public static final By CART_LINK = By.cssSelector("a[title='Shopping Cart']");

    private ProductPageUI() {
    }
}

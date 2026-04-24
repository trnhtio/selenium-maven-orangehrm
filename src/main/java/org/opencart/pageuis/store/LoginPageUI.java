package org.opencart.PageUIs.store;

import org.openqa.selenium.By;

public final class LoginPageUI {

    public static final By PAGE_TITLE = By.cssSelector("#content h1");
    public static final By EMAIL_INPUT = By.id("input-email");
    public static final By PASSWORD_INPUT = By.id("input-password");
    public static final By LOGIN_BUTTON = By.cssSelector(
            "#form-login button[type='submit'], button[type='submit'][form='form-login'], form button[type='submit'], input[type='submit'][value='Login']"
    );
    public static final By WARNING_ALERT = By.cssSelector("#alert .alert-danger, .alert-danger");
    public static final By MY_ACCOUNT_TITLE = By.cssSelector("#content h2");

    private LoginPageUI() {
    }
}

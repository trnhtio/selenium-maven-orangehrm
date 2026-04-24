package org.opencart.PageUIs.admin;

import org.openqa.selenium.By;

public final class AdministrationLoginPageUI {

    public static final By USERNAME_INPUT = By.id("input-username");
    public static final By PASSWORD_INPUT = By.id("input-password");
    public static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    public static final By PAGE_HEADER = By.cssSelector(".card-header, .login-container h1, h1");

    private AdministrationLoginPageUI() {
    }
}

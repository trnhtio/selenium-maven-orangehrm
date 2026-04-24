package org.opencart.PageUIs.store;

import org.openqa.selenium.By;

public final class RegisterPageUI {

    public static final By PAGE_TITLE = By.cssSelector("#content h1");
    public static final By FIRST_NAME_INPUT = By.id("input-firstname");
    public static final By LAST_NAME_INPUT = By.id("input-lastname");
    public static final By EMAIL_INPUT = By.id("input-email");
    public static final By PASSWORD_INPUT = By.id("input-password");
    public static final By NEWSLETTER_TOGGLE = By.id("input-newsletter");
    public static final By PRIVACY_POLICY_CHECKBOX = By.name("agree");
    public static final By CONTINUE_BUTTON = By.cssSelector("#form-register button[type='submit']");
    public static final By SUCCESS_TITLE = By.cssSelector("#content h1");
    public static final By WARNING_ALERT = By.cssSelector("#alert .alert-danger, .alert-danger");
    public static final By VALIDATION_ERRORS = By.cssSelector(".is-invalid, .invalid-feedback.d-block, .invalid-feedback[style*='block']");

    private RegisterPageUI() {
    }
}

package pages.ui;

import org.openqa.selenium.By;

public class LoginPageUI {
    public static final By USERNAME_TEXTBOX = By.name("username");
    public static final By PASSWORD_TEXTBOX = By.name("password");
    public static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    public static final By INVALID_CREDENTIALS_ALERT = By.xpath("//p[normalize-space()='Invalid credentials']");
    public static final By USERNAME_REQUIRED_MESSAGE = By.xpath("//input[@name='username']/ancestor::div[contains(@class,'oxd-input-group')]//span[normalize-space()='Required']");
    public static final By PASSWORD_REQUIRED_MESSAGE = By.xpath("//input[@name='password']/ancestor::div[contains(@class,'oxd-input-group')]//span[normalize-space()='Required']");
}

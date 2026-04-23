package org.opencart.PageObjects;

import Base.BasePage;
import org.opencart.PageUIs.DemoPageUI;
import org.openqa.selenium.WebDriver;

public class DemoPage extends BasePage {

    public DemoPage(WebDriver driver) {
        super(driver);
    }

    public DemoPage open() {
        driver.get(System.getProperty("baseUrl", DemoPageUI.DEMO_PAGE_URL));
        return this;
    }

    public HomePage openStoreFront() {
        driver.get(System.getProperty("storeUrl", DemoPageUI.STORE_FRONT_URL));
        return new HomePage(driver);
    }

    public RegisterPage openRegisterPage() {
        String storeUrl = System.getProperty("storeUrl", DemoPageUI.STORE_FRONT_URL);
        driver.get(storeUrl + DemoPageUI.REGISTER_PAGE_PATH);
        return new RegisterPage(driver);
    }

    public LoginPage openLoginPage() {
        String storeUrl = System.getProperty("storeUrl", DemoPageUI.STORE_FRONT_URL);
        driver.get(storeUrl + DemoPageUI.LOGIN_PAGE_PATH);
        return new LoginPage(driver);
    }

    public void logout() {
        String storeUrl = System.getProperty("storeUrl", DemoPageUI.STORE_FRONT_URL);
        driver.get(storeUrl + DemoPageUI.LOGOUT_PAGE_PATH);
    }
}

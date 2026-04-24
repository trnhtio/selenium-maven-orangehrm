package org.opencart.PageObjects.store;

import org.opencart.PageUIs.store.MyAccountPageUI;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends StorePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getMyAccountTitle() {
        return getText(MyAccountPageUI.MY_ACCOUNT_TITLE);
    }

    public boolean isMyAccountPageDisplayed() {
        waitForElementVisible(MyAccountPageUI.ACCOUNT_CONTENT);
        return driver.getCurrentUrl().contains("route=account/account");
    }
}

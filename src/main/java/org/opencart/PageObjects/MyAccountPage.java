package org.opencart.PageObjects;

import Base.BasePage;
import org.opencart.PageUIs.MyAccountPageUI;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage {

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

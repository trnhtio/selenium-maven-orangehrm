package org.opencart.PageObjects.admin;

import Base.BasePage;
import org.openqa.selenium.WebDriver;

public abstract class AdminPage extends BasePage {

    protected AdminPage(WebDriver driver) {
        super(driver);
    }

    public String getCurrentAdminUrl() {
        return getCurrentUrl();
    }

    public String getCurrentAdminTitle() {
        return getBrowserTitle();
    }
}

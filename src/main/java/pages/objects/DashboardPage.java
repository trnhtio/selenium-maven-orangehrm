package pages.objects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pages.ui.DashboardPageUI;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Checks whether the Dashboard header is displayed.
     */
    public boolean isDashboardHeaderDisplayed() {
        return isElementDisplayed(DashboardPageUI.DASHBOARD_HEADER);
    }
}

package core;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(20);

    protected final WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    /**
     * Waits until an element is visible on the page.
     */
    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until an element is clickable on the page.
     */
    protected WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until an element is no longer visible.
     */
    protected boolean waitForElementInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Waits until the current URL contains the expected value.
     */
    protected boolean waitForUrlContains(String expectedUrlValue) {
        return wait.until(ExpectedConditions.urlContains(expectedUrlValue));
    }

    /**
     * Waits until one of the supplied conditions is true.
     */
    @SafeVarargs
    protected final boolean waitForAnyCondition(ExpectedCondition<?>... conditions) {
        wait.until(ExpectedConditions.or(conditions));
        return true;
    }

    /**
     * Clicks an element after waiting for it to be clickable.
     */
    protected void click(By locator) {
        waitForElementClickable(locator).click();
    }

    /**
     * Clears an input and enters text after waiting for the field to be visible.
     */
    protected void sendKeys(By locator, String value) {
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Selects the current input value and replaces it with text.
     */
    protected void replaceText(By locator, String value) {
        WebElement element = waitForElementClickable(locator);
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(value);
    }

    /**
     * Gets visible element text.
     */
    protected String getText(By locator) {
        return waitForElementVisible(locator).getText().trim();
    }

    /**
     * Checks whether an element becomes visible within the configured timeout.
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            return waitForElementVisible(locator).isDisplayed();
        } catch (TimeoutException exception) {
            return false;
        }
    }

    /**
     * Checks whether any one of the supplied elements becomes visible.
     */
    protected boolean isAnyElementDisplayed(By... locators) {
        try {
            ExpectedCondition<?>[] conditions = new ExpectedCondition<?>[locators.length];
            for (int index = 0; index < locators.length; index++) {
                conditions[index] = ExpectedConditions.visibilityOfElementLocated(locators[index]);
            }
            waitForAnyCondition(conditions);
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    /**
     * Waits until either an element is visible or the URL contains the expected value.
     */
    protected void waitForElementVisibleOrUrlContains(By locator, String expectedUrlValue) {
        waitForAnyCondition(
                ExpectedConditions.visibilityOfElementLocated(locator),
                ExpectedConditions.urlContains(expectedUrlValue)
        );
    }

    /**
     * Opens a URL using the active browser session.
     */
    protected void openUrl(String url) {
        driver.get(url);
    }
}

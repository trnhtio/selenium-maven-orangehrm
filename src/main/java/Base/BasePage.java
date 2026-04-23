package Base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected final WebDriver driver;
    private final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        WebElement element = waitForElementClickable(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try {
            element.click();
        } catch (ElementClickInterceptedException exception) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void sendKeys(By locator, String value) {
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected String getText(By locator) {
        return waitForElementVisible(locator).getText().trim();
    }

    protected boolean isElementVisible(By locator) {
        return !driver.findElements(locator).isEmpty() && driver.findElement(locator).isDisplayed();
    }

    protected void waitForUrlContains(String value) {
        wait.until(ExpectedConditions.urlContains(value));
    }

    protected void waitForTextPresent(By locator, String value) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, value));
    }

    protected void waitForAnyCondition(By successLocator, String successText, By errorLocator) {
        wait.until(driver -> {
            try {
                boolean hasSuccess = !driver.findElements(successLocator).isEmpty()
                        && driver.findElement(successLocator).getText().contains(successText);
                boolean hasError = !driver.findElements(errorLocator).isEmpty()
                        && driver.findElement(errorLocator).isDisplayed();
                return hasSuccess || hasError;
            } catch (NoSuchElementException | StaleElementReferenceException exception) {
                return false;
            }
        });
    }

    protected void selectByValue(By locator, String value) {
        new Select(waitForElementVisible(locator)).selectByValue(value);
    }

    protected void setValueByJavaScript(By locator, String value) {
        WebElement element = waitForElementPresent(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                element,
                value
        );
    }
}

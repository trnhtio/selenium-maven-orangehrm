package org.opencart.utils;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowManager {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public WindowManager(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    public String switchToNewWindow(String currentWindowHandle) {
        Set<String> windowHandles = driver.getWindowHandles();
        boolean hasNewWindow = windowHandles.stream().anyMatch(windowHandle -> !windowHandle.equals(currentWindowHandle));
        if (!hasNewWindow) {
            int expectedWindowCount = windowHandles.size() + 1;
            wait.until(ExpectedConditions.numberOfWindowsToBe(expectedWindowCount));
            windowHandles = driver.getWindowHandles();
        }

        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                return windowHandle;
            }
        }

        throw new NoSuchWindowException("Unable to find a newly opened browser tab or window.");
    }

    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public void closeCurrentWindowAndSwitchBack(String originalWindowHandle) {
        if (!driver.getWindowHandle().equals(originalWindowHandle)) {
            driver.close();
        }
        driver.switchTo().window(originalWindowHandle);
    }

    public boolean waitForWindowCount(int expectedWindowCount) {
        try {
            wait.until(ExpectedConditions.numberOfWindowsToBe(expectedWindowCount));
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }
}

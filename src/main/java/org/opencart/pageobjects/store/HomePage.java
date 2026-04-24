package org.opencart.PageObjects.store;

import java.util.List;

import org.opencart.PageUIs.store.HomePageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends StorePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage searchForProduct(String productName) {
        sendKeys(HomePageUI.SEARCH_INPUT, productName);
        click(HomePageUI.SEARCH_BUTTON);
        return this;
    }

    public ProductPage openProduct(String productName) {
        waitForElementVisible(HomePageUI.PRODUCT_NAMES);
        List<WebElement> products = driver.findElements(HomePageUI.PRODUCT_NAMES);

        WebElement product = products.stream()
                .filter(item -> item.getText().trim().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Product not found: " + productName));

        product.click();
        return new ProductPage(driver);
    }

    public CartPage openCart() {
        click(HomePageUI.CART_LINK);
        return new CartPage(driver);
    }
}

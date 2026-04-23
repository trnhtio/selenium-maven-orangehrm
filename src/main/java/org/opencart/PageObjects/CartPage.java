package org.opencart.PageObjects;

import java.util.List;

import Base.BasePage;
import org.opencart.PageUIs.CartPageUI;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(CartPageUI.PAGE_TITLE);
    }

    public List<String> getProductNames() {
        waitForElementVisible(CartPageUI.PRODUCT_NAMES);
        return driver.findElements(CartPageUI.PRODUCT_NAMES)
                .stream()
                .map(element -> element.getText().trim())
                .filter(text -> !text.isEmpty())
                .toList();
    }

    public boolean isProductDisplayed(String productName) {
        return getProductNames().stream()
                .anyMatch(product -> product.equalsIgnoreCase(productName));
    }

    public CheckoutPage proceedToCheckout() {
        click(CartPageUI.CHECKOUT_LINK);
        return new CheckoutPage(driver);
    }
}

package org.opencart.PageObjects;

import java.util.Map;

import Base.BasePage;
import org.opencart.PageUIs.ProductPageUI;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductTitle() {
        return getText(ProductPageUI.PRODUCT_TITLE);
    }

    public ProductPage fillRequiredOptions() {
        click(ProductPageUI.RADIO_SMALL);
        click(ProductPageUI.CHECKBOX_ONE);
        sendKeys(ProductPageUI.TEXT_OPTION, "Automation text option");
        selectByValue(ProductPageUI.SELECT_OPTION, "4");
        sendKeys(ProductPageUI.TEXTAREA_OPTION, "Automation textarea option");
        uploadFileOption();
        setValueByJavaScript(ProductPageUI.DATE_OPTION, "2026-04-23");
        setValueByJavaScript(ProductPageUI.TIME_OPTION, "10:30");
        setValueByJavaScript(ProductPageUI.DATE_TIME_OPTION, "2026-04-23T10:30");
        return this;
    }

    private void uploadFileOption() {
        waitForElementPresent(ProductPageUI.FILE_OPTION);
        Object response = ((JavascriptExecutor) driver).executeAsyncScript("""
                const done = arguments[arguments.length - 1];
                const button = document.querySelector('#button-upload-222');
                const target = document.querySelector('#input-option-222');
                const file = new File(['OpenCart automation upload'], 'automation-upload.txt', { type: 'text/plain' });
                const formData = new FormData();
                formData.append('file', file);

                fetch(button.getAttribute('data-oc-url'), {
                    method: 'POST',
                    body: formData
                })
                    .then(response => response.json())
                    .then(json => {
                        if (json.code) {
                            target.value = json.code;
                            target.dispatchEvent(new Event('change', { bubbles: true }));
                        }
                        done(json);
                    })
                    .catch(error => done({ error: error.toString() }));
                """);

        if (response instanceof Map<?, ?> uploadResponse && !uploadResponse.containsKey("code")) {
            throw new IllegalStateException("File option upload failed: " + uploadResponse);
        }
    }

    public ProductPage setQuantity(int quantity) {
        sendKeys(ProductPageUI.QUANTITY_INPUT, String.valueOf(quantity));
        return this;
    }

    public ProductPage addToCart() {
        click(ProductPageUI.ADD_TO_CART_BUTTON);
        return this;
    }

    public String getSuccessMessage() {
        return getText(ProductPageUI.SUCCESS_ALERT);
    }

    public CartPage openCart() {
        click(ProductPageUI.CART_LINK);
        return new CartPage(driver);
    }
}

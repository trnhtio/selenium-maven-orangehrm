package org.opencart.tests.store;

import Base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.opencart.PageObjects.portal.DemoPage;
import org.opencart.PageObjects.store.CartPage;
import org.opencart.PageObjects.store.CheckoutPage;
import org.opencart.PageObjects.store.ProductPage;
import org.opencart.PageObjects.store.StorefrontPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("OpenCart Storefront")
@Feature("Shopping Cart")
public class AddProductToCartTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    @Story("Customer adds a product to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Navigate to OpenCart demo storefront, search Apple Cinema, fill all required product option elements, add it to cart, and proceed to checkout.")
    public void addProductToCartAndProceedToCheckout() {
        String searchKeyword = "Apple Cinema";
        String demoProductName = "Apple Cinema 30\"";

        StorefrontPage storefrontPage = new DemoPage(driver)
                .open()
                .openStoreFront();

        ProductPage productPage = storefrontPage
                .searchForProduct(searchKeyword)
                .openProduct(demoProductName);

        Assert.assertEquals(productPage.getProductTitle(), demoProductName, "Product details page is not opened.");

        productPage
                .fillRequiredOptions()
                .setQuantity(2)
                .addToCart();

        Assert.assertTrue(
                productPage.getSuccessMessage().contains("Success"),
                "Add-to-cart success message is not displayed."
        );

        CartPage cartPage = productPage.openCart();

        Assert.assertTrue(
                cartPage.getPageTitle().contains("Shopping Cart"),
                "Shopping cart page is not opened."
        );
        Assert.assertTrue(
                cartPage.isProductDisplayed(demoProductName),
                "Added product is not displayed in the cart."
        );

        CheckoutPage checkoutPage = cartPage.proceedToCheckout();

        Assert.assertTrue(
                checkoutPage.isCheckoutPageDisplayed(),
                "Checkout page is not displayed. URL: " + checkoutPage.getCurrentUrl()
                        + ", title: " + checkoutPage.getPageTitle()
        );
    }
}

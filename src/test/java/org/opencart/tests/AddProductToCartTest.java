package org.opencart.tests;

import Base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.opencart.PageObjects.CartPage;
import org.opencart.PageObjects.CheckoutPage;
import org.opencart.PageObjects.DemoPage;
import org.opencart.PageObjects.HomePage;
import org.opencart.PageObjects.ProductPage;
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
        String productName = "Apple Cinema 300";
        String searchKeyword = "Apple Cinema";
        String demoProductName = "Apple Cinema 30\"";

        HomePage homePage = new DemoPage(driver)
                .open()
                .openStoreFront();

        ProductPage productPage = homePage
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

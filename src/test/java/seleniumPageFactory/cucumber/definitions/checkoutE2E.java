package seleniumPageFactory.cucumber.definitions;



import io.cucumber.java.en.And;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumPageFactory.pages.cartPages;
import seleniumPageFactory.pages.checkoutPages;
import seleniumPageFactory.pages.dashboardPages;
import seleniumPageFactory.pages.loginPages;
import seleniumPageFactory.pages.orderPages;
import seleniumPageFactory.pages.productDisplayPage;

public class checkoutE2E {
    public loginPages loginPage;
    public dashboardPages dashboardPage;
    public productDisplayPage productDisplayPage;
    public cartPages cartPage;
    public checkoutPages checkoutPage;
    public orderPages orderPage;


    @And("Init all pages for run automation for checkout flow")
    public void initAllPages() {
        this.loginPage = new loginPages(hook.driver, hook.wait);
        this.dashboardPage = new dashboardPages(hook.driver, hook.wait);
        this.productDisplayPage = new productDisplayPage(hook.driver, hook.wait);
        this.cartPage = new cartPages(hook.driver, hook.wait);
        this.checkoutPage = new checkoutPages(hook.driver, hook.wait);
        this.orderPage = new orderPages(hook.driver, hook.wait);
    }

    @When("Input email {string} and password {string} in login page for checkout flow")
    public void inputEmailAndPassword(String email, String password) throws InterruptedException {
        // loginPage.fillEmail(System.getProperty("email"));
        // Thread.sleep(2000); // Wait for email to be filled

        loginPage.fillEmail(email);
        loginPage.fillPassword(password);
    }

    @Then("Click login button for checkout flow")
    public void clickLoginButton() throws InterruptedException {
        Thread.sleep(3000); // Wait for email and password to be filled
        loginPage.clickLoginButton();
    }

    @Then("Search product with keyword {string} for checkout flow")
    public void searchProduct(String name) throws InterruptedException {
        dashboardPage.doSearch(name);
        Thread.sleep(2000); // Wait for search results to load
    }

    @Then("Click view product for checkout flow")
    public void viewProduct() throws InterruptedException {
        dashboardPage.clickButtonViewProduct();
        Thread.sleep(2000); 
    }

    @And("Verify product name, to make sure the product {string} is shown for checkout flow")
    public void verifyProductName(String name) throws InterruptedException {
        productDisplayPage.verifyDataProduct(name);
        Thread.sleep(2000); 
    }

    @Then("Click ATC button for checkout flow")
    public void clickATCProduct() throws InterruptedException {
        productDisplayPage.clickATCButton();
        Thread.sleep(2000); 
    }

    @Then("Click checkout button for checkout flow")
    public void clickCheckoutButton() throws InterruptedException {
        cartPage.clickButtonCheckout();
        Thread.sleep(2000); 
    }


    @Then("Enter the details payment for checkout flow")
    public void enterPaymentDetail() throws InterruptedException {
        checkoutPage.enterCVV();
        checkoutPage.enterNameCard();
        checkoutPage.enterCountry();
        checkoutPage.clickRecomendationCountry();
        Thread.sleep(2000); 
    }

    @Then("Click place order button for checkout flow")
    public void clickPlaceOrder() throws InterruptedException {
        checkoutPage.clickPlaceOrder();
        Thread.sleep(2000); 
    }

    @And("Verify order, to make sure {string} successfully ordered")
    public void verifyOrder(String productName) throws InterruptedException {
        orderPage.verifyOrderCreated(productName);
        Thread.sleep(2000); 
    }

}

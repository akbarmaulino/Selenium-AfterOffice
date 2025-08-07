package seleniumPageFactory.testSuites;


import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.demoqa.seleniumPageFactory.baseTestSuites;

import seleniumPageFactory.pages.cartPages;
import seleniumPageFactory.pages.checkoutPages;
import seleniumPageFactory.pages.dashboardPages;
import seleniumPageFactory.pages.loginPages;
import seleniumPageFactory.pages.productDisplayPage;

public class E2ECheckout extends baseTestSuites {

    public loginPages loginPage;
    public dashboardPages dashboardPage;
    public productDisplayPage productDisplayPage;
    public cartPages cartPage;
    public checkoutPages checkoutPage;
    // init order page

    @BeforeSuite
    public void setup() {
        super.setUp();
        this.setupURL("https://rahulshettyacademy.com/client");

        this.loginPage = new loginPages(driver, wait);
        this.dashboardPage = new dashboardPages(driver, wait);
        this.productDisplayPage = new productDisplayPage(driver, wait);
        this.cartPage = new cartPages(driver, wait);
        this.checkoutPage = new checkoutPages(driver, wait);
        // init order page
    }

    @Test
    public void doCheckoutFlow() throws InterruptedException {
        loginPage.fillEmail("simanjuntakalbert57@gmail.com");
        loginPage.fillPassword("XBf@rWNvByn!#K8");
        loginPage.clickLoginButton();
        Thread.sleep(2000);
        dashboardPage.doSearch("ZARA COAT 3");
        dashboardPage.clickButtonViewProduct();
        Thread.sleep(2000);
        productDisplayPage.verifyDataProduct("ZARA COAT 3");
        productDisplayPage.clickATCButton();
        Thread.sleep(2000);
        cartPage.clickButtonCheckout();
        Thread.sleep(2000);
        checkoutPage.enterCVV();
        checkoutPage.enterNameCard();
        checkoutPage.enterCountry();
        checkoutPage.clickRecomendationCountry();
        checkoutPage.clickPlaceOrder();
        Thread.sleep(2000);
        // action order page

        Thread.sleep(2000);
    }

    @AfterSuite
    public void teardown() {
        super.tearDown();
    }
}

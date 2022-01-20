package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;

import static org.testng.Assert.assertTrue;
import static units.WaitUtils.waitForElementToShowUp;

public class LoginTest extends BaseTest {

    @Test (priority = 0)
    public void acceptAllCookiesLoginAndRegister() {
        BasePage basePage = new BasePage(driver);
        basePage.clickAcceptCookies();
        basePage.clickLogin();
    }

    @Test(priority = 1)
    public void loginAndRegistrationFormAndErrorMessageIsDisplayed() {
        LoginPage loginPage = new LoginPage(driver);

        boolean loginVisible = loginPage.getLogin().isDisplayed();
        assertTrue(loginVisible, "Something is wrong");

        boolean registrationFormVisible = loginPage.getRegistration().isDisplayed();
        assertTrue(registrationFormVisible, "Registration form not visible");

        waitForElementToShowUp(driver,loginPage.registrationForm, 5);
        loginPage.registrationFormIsFillUp();

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)");

        loginPage.pressRegistrationButton();

        //Reikiamas error message neatsiranda
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");

        waitForElementToShowUp(driver, loginPage.errorMessage,4);
        boolean errorMessageVisible = driver.findElement(loginPage.errorMessage).isDisplayed();
        assertTrue(errorMessageVisible, "Error message not visible");
    }
}

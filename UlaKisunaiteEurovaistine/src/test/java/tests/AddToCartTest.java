package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;
import pages.AddToCartPage;
import pages.BasePage;

import java.util.List;

import static org.testng.Assert.assertEquals;

import static units.WaitUtils.waitForElementToShowUp;

public class AddToCartTest extends BaseTest {

    public List<WebElement> element;

    //String inputText;
    //String expectedText;

    // @Factory(dataProvider = "search")
    // public enterInformation(String inputText, String expectedText){
    //    this.inputText = inputText;
    //    this.expectedText = expectedText;
    // }

    //@DataProvider(name = "search")
    //public static Object[][] searchData(){
    //    return new Object[][] {
    //       {("Gripex, plėvele dengtos tabletės, N12"), ("Gripex, plėvele dengtos tabletės, N24")}
    // };
    //}

    @Test(priority = 0)
    public void acceptAllCookies() {
        BasePage basePage = new BasePage(driver);
        basePage.clickAcceptCookies();
    }

    @Test(priority = 1)
    public void enterTextClickSearchAndPressEnterCheckSearchName() {
        AddToCartPage addToCartPage = new AddToCartPage(driver);
        WebElement element = driver.findElement(addToCartPage.searchInput);
        String searchText = "Nereceptiniai vaistai";
        String expectedResults = "Paieškos rezultatai ieškant \"Nereceptiniai vaistai\"";
        element.sendKeys(searchText);
        element.sendKeys(Keys.ENTER);
        String pageName = driver.findElement(addToCartPage.pageTitle).getText();
        assertEquals(pageName, expectedResults, "Page title was not correct");
    }

    @Test(priority = 2)
    public void enterProductBrand() {
        AddToCartPage addToCartPage = new AddToCartPage(driver);
        waitForElementToShowUp(driver, addToCartPage.productBrand, 5);

        element = driver.findElements(addToCartPage.productBrandTitle);
        String tradeMark = "Prekės ženklas:";
        WebElement productBrand = element.stream()
                .filter(zodis -> zodis.getText().contains(tradeMark)).findAny().get();
        productBrand.click();
        String searchKey = "GRIPEX";
        addToCartPage.getProductName().sendKeys(searchKey);
        waitForElementToShowUp(driver, addToCartPage.checkboxVisible, 2);
        addToCartPage.getCheckboxVisible().click();

    }
}

   // @Test(priority = 3)
    //public void checkProductsTitles() {
    //    List<WebElement> searchResults = driver.findElements(By.cssSelector(".product-card--link"));
     //   System.out.println("Puslapyje atvaizduojama prekių: " + searchResults.size());

     //   searchResults.stream()
       //         .filter(searchResult -> searchResult.getText().contains("GRIPEX"))
       //         .findFirst().get().click();
   // }



package org.testsuites;

import data.DataDriven;
import file.FileController;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pageobjects.CardPage;
import org.pageobjects.MainPage;
import org.pageobjects.ProductPage;
import org.pageobjects.SearchPage;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Home {

    private static WebDriver driver = new ChromeDriver();
    private static String productDetails;
    private static String priceOnPage;
    private static String priceOnCard;
    private static ArrayList<String> testData= new ArrayList<>();


    public static void main(String[] args) throws InterruptedException, IOException {

        WebDriverManager.chromedriver().setup();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        DataDriven dataDriven = new DataDriven();
        testData = dataDriven.exportData();

        MainPage mainPage = new MainPage(driver);
        mainPage.goTo();
        mainPage.closeCookieAndPopup();
        mainPage.searchProduct(testData);

        SearchPage searchPage = new SearchPage(driver);
        searchPage.pickRandomProduct();

        ProductPage productPage = new ProductPage(driver);
        productDetails =  productPage.getDetailText();
        priceOnPage = productPage.getPriceText();

        FileController.writeToFile(productDetails, priceOnPage);


        productPage.selectEnabledSize();
        productPage.addCard();
        productPage.goToCard();

        CardPage cardPage = new CardPage(driver);
        priceOnCard = cardPage.getProductSalePrice();

        checkPriceEquality(priceOnPage, priceOnCard);


        checkQuantity("2", cardPage.selectAndGetOption("2"));

        cardPage.removeProduct();
        Thread.sleep(5000);
        checkProductIsRemoved(cardPage.getEmptyCardMessage());



    }

    @Test
    public static void checkPriceEquality(String priceOnPage, String priceOnCard){
        assertEquals(priceOnPage, priceOnCard);
    }

    @Test
    public static void checkQuantity(String selectedValue, String observedValue){
        assertTrue(observedValue.contains(selectedValue));
    }

    @Test
    public static void checkProductIsRemoved(WebElement messageTitle){
        assertTrue(messageTitle.isDisplayed());
    }

}

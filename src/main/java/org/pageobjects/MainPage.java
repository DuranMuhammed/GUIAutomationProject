package org.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class MainPage {

    WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="onetrust-accept-btn-handler")
    WebElement cookieAcceptButton;

    @FindBy(className = "o-modal__closeButton")
    WebElement genderPopupCloseButton;

    @FindBy(xpath = "//div[contains(@class, 'search')]//input")
    WebElement searchInput;

    public void goTo(){
        driver.get("https://www.beymen.com");
    }

    public void closeCookieAndPopup(){
        cookieAcceptButton.click();
        genderPopupCloseButton.click();;
    }

    public void searchProduct(ArrayList<String> testData){
        for (String specificData: testData) {
            searchInput.sendKeys(specificData);
            if(testData.indexOf(specificData) != (testData.size()-1)){
                searchInput.sendKeys(Keys.chord(Keys.CONTROL,"a") + Keys.DELETE);
                continue;
            }
            else{
                searchInput.sendKeys(Keys.ENTER);
                break;
            }
        }
    }
}

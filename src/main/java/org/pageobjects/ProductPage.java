package org.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {

    WebDriver driver;
    public ProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "o-productDetail__description")
    WebElement descriptionText;

    @FindBy(className = "m-price__new")
    WebElement priceText;

    @FindBy(className = "m-variation__item")
    List<WebElement> sizeVariations;

    @FindBy(id="addBasket")
    WebElement addBasketButton;

    @FindBy(className = "m-notification__button")
    WebElement notificationButton;

    public String getDetailText(){
        return descriptionText.getText();
    }

    public String getPriceText(){
        return priceText.getText().split(" ")[0];
    }

    public void selectEnabledSize(){
        WebElement enabledSize = null;
        for(WebElement i : sizeVariations) {
            if(!i.getAttribute("class").contains("disabled")){
                enabledSize = i;
                break;
            }
        }
        assert enabledSize != null;
        enabledSize.click();
    }

    public void addCard(){
        addBasketButton.click();
    }

    public void goToCard(){
        notificationButton.click();
    }

}

package org.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CardPage {

    WebDriver driver;
    public CardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className="m-productPrice__salePrice")
    WebElement productSalePrice;

    @FindBy(id = "quantitySelect0-key-0")
    WebElement quantityDropdown;

    @FindBy(id = "removeCartItemBtn0-key-0")
    WebElement removeProductButton;

    @FindBy(className = "m-empty__messageTitle")
    WebElement emptyCardMessage;

    public String getProductSalePrice(){

       return productSalePrice.getText().split(" ")[0];
    }

    public String selectAndGetOption(String option){
        Select options = new Select(quantityDropdown);

        options.getOptions().contains(option);
        options.selectByValue(option);
        return options.getFirstSelectedOption().getText();
    }

    public void removeProduct(){
        removeProductButton.click();
    }

    public WebElement getEmptyCardMessage(){
         return emptyCardMessage;
    }


}

package org.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {

    WebDriver driver;
    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='productList']/div[@data-page='1']")
    List<WebElement> listedProducts;

    public void pickRandomProduct(){
        int productCount = listedProducts.size();
        WebElement selectedProduct = driver.findElement(By.xpath("//div[@id='productList']/div["
                + randomNumberGenerator(productCount)
                + "]"));
        selectedProduct.click();
    }

    public static int randomNumberGenerator(int range){
        return (int) (Math.random() * (range));
    }




}

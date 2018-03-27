package com.qulix.selenium.learn;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by Starovoytovdv on 10.03.2018.
 */


public class EditMessagePage {

    private WebDriver driver;

    private static String URL_MATCH = "Edit Message";

    @FindBy(id = "headline")
    private WebElement headline;

    @FindBy(id = "text")
    private WebElement message;

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(xpath = "//input[@class='save']")
    private WebElement buttonSave;

    public EditMessagePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        if (!title.getText().equals(URL_MATCH)){
            throw new IllegalStateException("This is not EditMessagePage are your expected.");
        }
        this.driver = driver;
    }


    @Step("Check message")
    public EditMessagePage checkMessage(Message mess){
        Assert.assertEquals(this.headline.getAttribute("value"), mess.getHeadline());
        Assert.assertEquals(this.message.getAttribute("value"), mess.getDescription());
        return this;
    }


    @Step("Edit message")
    public EditMessagePage editMessage(Message mess){
        headline.clear();
        headline.sendKeys(mess.getHeadline());
        message.clear();
        message.sendKeys(mess.getDescription());
        return this;
    }


    @Step("Click Save Button")
    public ShowMessagePage clickSaveButton(){
        buttonSave.click();
        return new ShowMessagePage(driver);
    }
}

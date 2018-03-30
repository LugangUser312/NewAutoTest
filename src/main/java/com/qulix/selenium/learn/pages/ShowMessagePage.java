package com.qulix.selenium.learn.pages;

import com.qulix.selenium.learn.data.Message;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by Starovoytovdv on 09.03.2018.
 */


public class ShowMessagePage {

    private WebDriver driver;

    private static String URL_MATCH = "Show Message";

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(xpath = "//a[@class='list']")
    private WebElement messageListButton;

    @FindBy(xpath = "//td[text()='Headline']/following::td[1]")
    private WebElement headline;

    @FindBy(xpath = "//td[text()='Text']/following::td[1]")
    private WebElement message;

    @FindBy(xpath = "//a[@class='create']")
    private WebElement newMessageButton;

    public ShowMessagePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        if (!title.getText().equals(URL_MATCH)){
            throw new IllegalStateException("This is not the ShowMessagePage are your expected.");
        }
        this.driver = driver;
    }


    @Step("Check message")
    public ShowMessagePage checkMessage(Message mess){
        Assert.assertEquals(this.headline.getText(), mess.getHeadline());
        Assert.assertEquals(this.message.getText(), mess.getDescription());
        return this;
    }


    @Step("Click button List")
    public MessageListPage messageListButtonClick(){
        messageListButton.click();
        return new MessageListPage(driver);
    }


    @Step("Click button New Message")
    public CreateMessagePage clickNewMessageButton(){
        newMessageButton.click();
        return new CreateMessagePage(driver);
    }
}

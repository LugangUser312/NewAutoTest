package com.qulix.selenium.learn;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Starovoytovdv on 09.03.2018.
 */

public class CreateMessagePage {

    private WebDriver driver;

    private static String URL_MATCH = "Create Message";

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(id = "headline")
    private WebElement headline;

    @FindBy(id = "text")
    private WebElement text;

    @FindBy(xpath = "//input[@class='save']")
    private WebElement buttonCreate;

    @FindBy(xpath = "//a[@class='list']")
    private WebElement messageListButton;

    public CreateMessagePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        if (!title.getText().equals(URL_MATCH)){
            throw new IllegalStateException("This is not CreateMessagePage you are expected");
        }
        this.driver = driver;
    }


    //TODO Попробуй Enter headline {0} and description {1} for message.
    // Сразу везде, где есть параметры в методе, так сделал, но решил что без них нагляднее
    // т.к степы с параметрами можно развернуть и посомтерть что передовалось
    @Step("Enter message")
    public CreateMessagePage enterMessage(Message message){
        headline.clear();
        headline.sendKeys(message.getHeadline());
        text.clear();
        text.sendKeys(message.getDescription());
        return this;
    }


    @Step("Click button Create")
    public ShowMessagePage createMessage(){
        buttonCreate.click();
        return new ShowMessagePage(driver);
    }


    @Step("Check fields headline and message are not empty")
    public CreateMessagePage checkFieldsIsNotEmpty(){
        if (headline.getAttribute("value").isEmpty() || text.getAttribute("value").isEmpty()){
            throw new RuntimeException("Fields are empty");
        }else{
            return this;
        }
    }


    @Step("Click button List")
    public MessageListPage messageListButtonClick(){
        messageListButton.click();
        return new MessageListPage(driver);
    }
}

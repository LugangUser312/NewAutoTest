import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Starovoytovdv on 06.03.2018.
 */

public class MessageListPage {

    private WebDriver driver;

    private static String URL_MATCH = "Message List";

    @FindBy(xpath = "//a[@class='create']")
    private WebElement newMessageButton;

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement showAllMessage;

    private By nextButton = By.xpath("//a[@class='nextLink']");

    private By previusButoon = By.xpath("//a[@class='prevLink']");

    @FindBy(linkText = "Logout")
    private WebElement logoutButton;

    private By view = By.linkText("View");

    private By edit = By.linkText("Edit");

    private By delete = By.linkText("Delete");


    public MessageListPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        if (!title.getText().equals(URL_MATCH)){
            throw new IllegalStateException("This is not the MessageListPage you are expected");
        }
        if (!showAllMessage.isSelected()) {
            showAllMessage.click();
        }
        this.driver = driver;
    }


    @Step("Click button New Message")
    public CreateMessagePage clickNewMessageButton(){
        newMessageButton.click();
        return new CreateMessagePage(driver);
    }


    @Step("Сounting the messages")
    public int getMessageCount(){
        toLastPage();
        List<WebElement> trLists = driver.findElements(By.tagName("tr"));
        return trLists.size();
    }


    @Step("Open last page of messages list")
    private void toLastPage(){
        while(!driver.findElements(nextButton).isEmpty()){
            driver.findElement(nextButton).click();
        }
    }


    @Step("Check last message")
    public MessageListPage checkLastMessage(String headline, String message){
        toLastPage();
        List<WebElement> trLists = driver.findElements(By.tagName("tr"));
        List<WebElement> tdList = trLists.get(trLists.size()-1).findElements(By.tagName("td"));
        Assert.assertEquals(tdList.get(1).getText(), headline);
        Assert.assertEquals(tdList.get(2).getText(), message);
        return this;
    }


    @Step("Open previous page")
    private void toPreviousPage(){
        driver.findElement(previusButoon).click();
    }


    private List<WebElement> getCurrentPageLastRowCells(){
        List<WebElement> trLists = driver.findElements(By.tagName("tr"));
        return trLists.get(trLists.size()-1).findElements(By.tagName("td"));
    }

    private List<WebElement> getCurrentPagePreviousRowCells(){
        List<WebElement> trLists = driver.findElements(By.tagName("tr"));
        return trLists.get(trLists.size()-2).findElements(By.tagName("td"));
    }

    private int getRowsCountOnCurrentPage(){
        List<WebElement> trLists = driver.findElements(By.tagName("tr"));
        return trLists.size();
    }


    @Step("Check two last message")
    public MessageListPage checkTwoLastMessage(String headline1, String message1, String headline2, String message2){
        toLastPage();
        List<WebElement> lastRowCells = getCurrentPageLastRowCells();
        List<WebElement> previousRowCells;
        if(getRowsCountOnCurrentPage()>2){
            previousRowCells = getCurrentPagePreviousRowCells();
        }else{
            toPreviousPage();
            previousRowCells = getCurrentPageLastRowCells();
        }
        Assert.assertEquals(lastRowCells.get(1).getText(), headline2);
        Assert.assertEquals(lastRowCells.get(2).getText(), message2);
        Assert.assertEquals(previousRowCells.get(1).getText(), headline1);
        Assert.assertEquals(previousRowCells.get(2).getText(), message1);
        return this;
    }


    private WebElement getButtonsForLastMessage(){
        toLastPage();
        List<WebElement> trLists = driver.findElements(By.tagName("tr"));
        List<WebElement> tdList = trLists.get(trLists.size() - 1).findElements(By.tagName("td"));
        return tdList.get(0);
    }


    @Step("Click View Last Message")
    public ShowMessagePage clickViewLastMessage(){
        getButtonsForLastMessage().findElement(view).click();
        return new ShowMessagePage(driver);
    }


    @Step("Click Edit Last Message")
    public EditMessagePage clickEditMessage(){
        getButtonsForLastMessage().findElement(edit).click();
        return new EditMessagePage(driver);
    }


    @Step("Click Delete Last Message")
    public MessageListPage clickDeleteMessage(){
        getButtonsForLastMessage().findElement(delete).click();
        return this;
    }


    @Step("Check Last Message is not exist")
    public MessageListPage checkMessageNotExist(int countOfMessageBefore){
        while(!driver.findElements(nextButton).isEmpty()){
            driver.findElement(nextButton).click();
        }
        List<WebElement> trLists = driver.findElements(By.tagName("tr"));
        if(trLists.size() == countOfMessageBefore){
            return this;
        } else {
         throw new RuntimeException("Message is created or didn't delete");
        }
    }


    @Step("Click Logout")
    public LoginPage clickLogout(){
        logoutButton.click();
        return new LoginPage(driver);
    }
}
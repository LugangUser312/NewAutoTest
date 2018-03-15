import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
    private WebElement showAllMessage;//TODO Называть все таки надо по бизнес логике, а не по типу. Что за чекбокс

    private By nextButton = By.xpath("//a[@class='nextLink']");

    private By previusButoon = By.xpath("//a[@class='prevLink']");

    @FindBy(linkText = "Logout")
    private WebElement logoutButton;

    private By view = By.linkText("View");

    private By edit = By.linkText("Edit");

    private By delete = By.linkText("Delete");

    // b = 1 , a = b, a = 2    b = 2???
    //TODO Потому что без static поле принадлежит объекту. А у тебя постоянно создается новая страница
    //TODO этому полю и значению здесь не место. Если тебе очень надо его зачем-то хранить используй какой-нибудь другой класс

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

    public CreateMessagePage clickNewMessageButton(){
        newMessageButton.click();
        return new CreateMessagePage(driver);
    }

    public int getMessageCount(){
        toLastPage();
        List<WebElement> trLists = driver.findElements(By.tagName("tr"));
        return trLists.size();
    }

    private void toLastPage(){
        while(!driver.findElements(nextButton).isEmpty()){
            driver.findElement(nextButton).click();
        }
    }

    public MessageListPage checkLastMessage(String headline, String message){
        toLastPage();
        List<WebElement> trLists = driver.findElements(By.tagName("tr"));
        List<WebElement> tdList = trLists.get(trLists.size()-1).findElements(By.tagName("td"));
        Assert.assertEquals(tdList.get(1).getText(), headline);
        Assert.assertEquals(tdList.get(2).getText(), message);
        return new MessageListPage(driver);
    }

    public MessageListPage checkTwoLastMessage(String headline1, String message1, String headline2, String message2){
        toLastPage();
        WebElement firstHeadline;
        WebElement firstMessage;
        WebElement secondHeadline;
        WebElement secondMessage;
        List<WebElement> trLists = driver.findElements(By.tagName("tr"));
        if(trLists.size() > 2){
            firstHeadline = trLists.get(trLists.size()-1).findElements(By.tagName("td")).get(1);
            System.out.println(firstHeadline.getText());
            secondHeadline = trLists.get(trLists.size()-2).findElements(By.tagName("td")).get(1);
            System.out.println(secondHeadline.getText());
            firstMessage = trLists.get(trLists.size()-1).findElements(By.tagName("td")).get(2);
            System.out.println(firstMessage.getText());
            secondMessage = trLists.get(trLists.size()-2).findElements(By.tagName("td")).get(2);
            System.out.println(secondMessage.getText());
        }else{
            firstHeadline = trLists.get(trLists.size()-1).findElements(By.tagName("td")).get(1);
            firstMessage = trLists.get(trLists.size()-1).findElements(By.tagName("td")).get(2);
            driver.findElement(previusButoon).click();
            List<WebElement> trListsBefore = driver.findElements(By.tagName("tr"));
            secondHeadline = trListsBefore.get(trLists.size()-1).findElements(By.tagName("td")).get(1);
            secondMessage = trListsBefore.get(trLists.size()-1).findElements(By.tagName("td")).get(2);
        }
        Assert.assertEquals(firstHeadline.getText(), headline2);
        Assert.assertEquals(firstMessage.getText(), message2);
        Assert.assertEquals(secondHeadline.getText(), headline1);
        Assert.assertEquals(secondMessage.getText(), message1);
        return new MessageListPage(driver);
    }

    //TODO Этот метод стоит вообще удалить
   /* private Map<String, List> getLastTwoTdOfMessage(){
        toLastPage();
        Map<String, List> tdMap;
        List<WebElement> trLists = driver.findElements(By.tagName("tr"));
        List<WebElement> tdList;
        List<WebElement> tdListBefore;
        if(trLists.size() >= 2) {
            tdList = trLists.get(trLists.size() - 1).findElements(By.tagName("td"));
            tdListBefore = trLists.get(trLists.size() - 2).findElements(By.tagName("td"));
            tdMap = new HashMap<String, List>();
            tdMap.put("list1", tdList);
            tdMap.put("list2", tdListBefore);
        }else{
            tdList = trLists.get(trLists.size() - 1).findElements(By.tagName("td"));
            driver.findElement(previusButoon).click();
            //TODO И это работает? У тебя должен быть StaleElementReferenceException
            tdListBefore = trLists.get(trLists.size() - 1).findElements(By.tagName("td"));
            tdMap = new HashMap<String, List>();
            tdMap.put("list1", tdList);
            tdMap.put("list2", tdListBefore);
        }
        return tdMap;
    }*/

    private WebElement getButtonsForLastMessage(){
        toLastPage();
        List<WebElement> trLists = driver.findElements(By.tagName("tr"));
        List<WebElement> tdList = trLists.get(trLists.size() - 1).findElements(By.tagName("td"));
        return tdList.get(0);
    }

    public ShowMessagePage clickViewLastMessage(){
        getButtonsForLastMessage().findElement(view).click();
        return new ShowMessagePage(driver);
    }

    public EditMessagePage clickEditMessage(){
        getButtonsForLastMessage().findElement(edit).click();
        return new EditMessagePage(driver);
    }

    public MessageListPage clickDeleteMessage(){
        getButtonsForLastMessage().findElement(delete).click();
        return new MessageListPage(driver);
    }

    public MessageListPage checkMessageNotExist(int countOfMessageBefore){
        while(!driver.findElements(nextButton).isEmpty()){
            driver.findElement(nextButton).click();
        }
        List<WebElement> trLists = driver.findElements(By.tagName("tr"));
        if(trLists.size() == countOfMessageBefore){
            return new MessageListPage(driver);
        } else {
         throw new RuntimeException("Message is created or didn't delete");
        }
    }

    public LoginPage clickLogout(){
        logoutButton.click();
        return new LoginPage(driver);
    }
}
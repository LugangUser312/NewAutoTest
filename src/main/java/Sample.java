import io.qameta.allure.Features;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;

/**
 * Created by Starovoytovdv on 05.02.2018.
 */

public class Sample {

    private WebDriver driver;

    @BeforeMethod
    public void doBeforeTest(){
        System.setProperty("webdriver.chrome.driver", "D:/Selenium/selenium/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        //driver.get("http://localhost:8080/QulixTeachingSite");
    }

    @AfterMethod
    public void doAfterTest(){
        driver.close();
    }

    @Test (description = "Check ability to create a message")
    @Parameters({"Login", "Password"})
    public void test1(String login, String password) {
        System.out.println("Test 1 has started");
        Message mess = new Message("Zagolovok", "Textik");
        StartPage.openInDriver(driver).clickUserController()
                .signIn(login, password).clickNewMessageButton()
                .enterMessage(mess.getHeadline(), mess.getDescription()).checkFieldsIsNotEmpty().createMessage()
                .messageListButtonClick().checkLastMessage(mess.getHeadline(), mess.getDescription());

    }

    @Test (description = "Check view of creating message")
    @Parameters({"Login", "Password"})
    public void test2(String login, String password){
        System.out.println("Test 2 has started");
        Message mess = new Message("Zagolovok", "Textik");
        StartPage.openInDriver(driver).clickUserController().
                signIn(login, password).clickNewMessageButton()
                .enterMessage(mess.getHeadline(), mess.getDescription()).checkFieldsIsNotEmpty()
                .createMessage().checkMessage(mess.getHeadline(), mess.getDescription()).messageListButtonClick()
                .checkLastMessage(mess.getHeadline(), mess.getDescription()).clickViewLastMessage()
                .checkMessage(mess.getHeadline(), mess.getDescription());

    }

    @Test (description = "Check ability to edit a message")
    @Parameters({"Login", "Password"})
    public void test3(String login, String password){
        System.out.println("Test 3 has started");
        Message mess = new Message("Zagolovok", "Textik");
        Message newMess = new Message("11111", "222222");
        StartPage.openInDriver(driver).clickUserController().
                signIn(login, password).clickNewMessageButton()
                .enterMessage(mess.getHeadline(), mess.getDescription()).checkFieldsIsNotEmpty().createMessage()
                .checkMessage(mess.getHeadline(), mess.getDescription()).messageListButtonClick()
                .checkLastMessage(mess.getHeadline(), mess.getDescription()).clickEditMessage()
                .checkMessage(mess.getHeadline(), mess.getDescription())
                .editMessage(newMess.getHeadline(), newMess.getDescription())
                .checkMessage(newMess.getHeadline(), newMess.getDescription()).clickSaveButton()
                .checkMessage(newMess.getHeadline(), newMess.getDescription()).messageListButtonClick()
                .checkLastMessage(newMess.getHeadline(), newMess.getDescription());
    }

    @Test (description = "Check ability to delete a message")
    @Parameters({"Login", "Password"})
    public void test4(String login, String password){
        System.out.println("Test 4 has started");
        Message mess = new Message("Zagolovok", "Textik");
        int messageCount = StartPage.openInDriver(driver).clickUserController().signIn(login, password)
                .getMessageCount();
        StartPage.openInDriver(driver).clickUserController().
                signIn(login, password).clickNewMessageButton()
                .enterMessage(mess.getHeadline(), mess.getDescription()).checkFieldsIsNotEmpty()
                .createMessage().checkMessage(mess.getHeadline(), mess.getDescription()).messageListButtonClick()
                .checkLastMessage(mess.getHeadline(), mess.getDescription()).clickDeleteMessage()
                .checkMessageNotExist(messageCount);
    }


    @Test (description = "Check the message isn't created if doesn't click save button")
    @Parameters({"Login", "Password"})
    public void test5(String login, String password){
        System.out.println("Test 5 has started");
        Message mess = new Message("Zagolovok", "Textik");
        int messageCount = StartPage.openInDriver(driver).clickUserController().signIn(login, password)
                .getMessageCount();
        StartPage.openInDriver(driver).clickUserController().
                signIn(login, password).clickNewMessageButton()
                .enterMessage(mess.getHeadline(), mess.getDescription()).checkFieldsIsNotEmpty().messageListButtonClick()
                .checkMessageNotExist(messageCount);
    }


    @Test (description = "Check the creation of two message")
    @Parameters({"Login", "Password"})
    public void test6(String login, String password){
        System.out.println("Test 6 has started");
        Message mess = new Message("Zagolovok", "Textik");
        Message newMess = new Message("11111", "222222");
        StartPage.openInDriver(driver).clickUserController().
                signIn(login, password).clickNewMessageButton().
                enterMessage(mess.getHeadline(), mess.getDescription()).checkFieldsIsNotEmpty().createMessage()
                .checkMessage(mess.getHeadline(), mess.getDescription()).clickNewMessageButton()
                .enterMessage(newMess.getHeadline(), newMess.getDescription())
                .checkFieldsIsNotEmpty().createMessage().checkMessage(newMess.getHeadline(), newMess.getDescription())
                .messageListButtonClick()
                .checkTwoLastMessage(mess.getHeadline(), mess.getDescription(), newMess.getHeadline(), newMess.getDescription());
    }

    @Test (description = "Check the creation of two messages from different users")
    @Parameters({"Login", "Password", "NewLogin", "NewPassword"})
    public void test7(String login, String password, String newLogin, String newPassword){
        System.out.println("Test 7 has started");
        Message mess = new Message("Zagolovok", "Textik");
        Message newMess = new Message("11111", "222222");
        StartPage.openInDriver(driver).clickUserController()
                .signIn(login, password)
                .clickNewMessageButton()
                .enterMessage(mess.getHeadline(), mess.getDescription()).checkFieldsIsNotEmpty().createMessage()
                .checkMessage(mess.getHeadline(), mess.getDescription()).messageListButtonClick()
                .checkLastMessage(mess.getHeadline(), mess.getDescription())
                .clickViewLastMessage()
                .checkMessage(mess.getHeadline(), mess.getDescription()).messageListButtonClick()
                .checkLastMessage(mess.getHeadline(), mess.getDescription()).clickLogout().signIn(newLogin, newPassword)
                .clickNewMessageButton()
                .enterMessage(newMess.getHeadline(),newMess.getDescription()).checkFieldsIsNotEmpty().
                createMessage().checkMessage(newMess.getHeadline(),newMess.getDescription()).messageListButtonClick()
                .checkLastMessage(newMess.getHeadline(),newMess.getDescription()).clickViewLastMessage()
                .checkMessage(newMess.getHeadline(),newMess.getDescription()).messageListButtonClick().clickLogout()
                .signIn(login, password)
                .checkTwoLastMessage(mess.getHeadline(), mess.getDescription(), newMess.getHeadline(),newMess.getDescription());
    }
}
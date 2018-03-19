import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
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
        driver.get("http://localhost:8080/QulixTeachingSite");
    }

    @AfterMethod
    public void doAfterTest(){
        driver.close();
    }

    @Test
    @Parameters({"Login", "Password", "Message", "Headline"})
    public void test1(String login, String password, String message, String headline) {
        System.out.println("Test 1 has started");

        new StartPage(driver).clickUserController()
                .signIn(login, password).clickNewMessageButton()
                .enterMessage(headline, message).checkFieldsIsNotEmpty().createMessage()
                .messageListButtonClick().checkLastMessage(headline, message);
    }

    @Test
    @Parameters({"Login", "Password", "Message", "Headline"})
    public void test2(String login, String password, String message, String headline){
        System.out.println("Test 2 has started");
        new StartPage(driver).clickUserController().
                signIn(login, password).clickNewMessageButton()
                .enterMessage(headline, message).checkFieldsIsNotEmpty()
                .createMessage().checkMessage(headline, message).messageListButtonClick()
                .checkLastMessage(headline, message).clickViewLastMessage().checkMessage(headline, message);

    }

    @Test
    @Parameters({"Login", "Password", "Message", "Headline", "NewMessage", "NewHeadline"})
    public void test3(String login, String password, String message, String headline,
                      String newMessage, String newHeadline){
        System.out.println("Test 3 has started");
        new StartPage(driver).clickUserController().
                signIn(login, password).clickNewMessageButton()
                .enterMessage(headline, message).checkFieldsIsNotEmpty().createMessage()
                .checkMessage(headline, message).messageListButtonClick()
                .checkLastMessage(headline, message).clickEditMessage().checkMessage(headline, message)
                .editMessage(newHeadline, newMessage).checkMessage(newHeadline, newMessage).clickSaveButton()
                .checkMessage(newHeadline, newMessage).messageListButtonClick()
                .checkLastMessage(newHeadline, newMessage);
    }

  @Test
    @Parameters({"Login", "Password", "Message", "Headline"})
    public void test4(String login, String password, String message, String headline){
        System.out.println("Test 4 has started");
        int messageCount = new StartPage(driver).clickUserController().signIn(login, password).getMessageCount();
        driver.get("http://localhost:8080/QulixTeachingSite");
        new StartPage(driver).clickUserController().
                signIn(login, password).clickNewMessageButton()
                .enterMessage(headline, message).checkFieldsIsNotEmpty()
                .createMessage().checkMessage(headline, message).messageListButtonClick()
                .checkLastMessage(headline, message).clickDeleteMessage().checkMessageNotExist(messageCount);
    }

    @Test
    @Parameters({"Login", "Password", "Message", "Headline"})
    public void test5(String login, String password, String message, String headline){
        System.out.println("Test 5 has started");
        int messageCount = new StartPage(driver).clickUserController().signIn(login, password).getMessageCount();
        driver.get("http://localhost:8080/QulixTeachingSite");
        new StartPage(driver).clickUserController().
                signIn(login, password).clickNewMessageButton()
                .enterMessage(headline, message).checkFieldsIsNotEmpty().messageListButtonClick()
                .checkMessageNotExist(messageCount);
    }


    @Test
    @Parameters({"Login", "Password", "Message", "Headline", "NewMessage", "NewHeadline"})
    public void test6(String login, String password, String message, String headline,
                      String newMessage, String newHeadline){
        System.out.println("Test 6 has started");
        new StartPage(driver).clickUserController().
                signIn(login, password).clickNewMessageButton().
                enterMessage(headline, message).checkFieldsIsNotEmpty().createMessage()
                .checkMessage(headline, message).clickNewMessageButton().enterMessage(newHeadline, newMessage)
                .checkFieldsIsNotEmpty().createMessage().checkMessage(newHeadline, newMessage)
                .messageListButtonClick().checkTwoLastMessage(headline, message, newHeadline, newMessage);
    }

    @Test
    @Parameters({"Login", "Password", "Message", "Headline", "NewMessage", "NewHeadline", "NewLogin", "NewPassword"})
    public void test7(String login, String password, String message, String headline, String newMessage,
                      String newHeadline, String newLogin, String newPassword){
        System.out.println("Test 7 has started");
        new StartPage(driver).clickUserController()
                .signIn(login, password)
                .clickNewMessageButton()
                .enterMessage(headline, message).checkFieldsIsNotEmpty().createMessage()
                .checkMessage(headline, message).messageListButtonClick().checkLastMessage(headline, message)
                .clickViewLastMessage().checkMessage(headline, message).messageListButtonClick()
                .checkLastMessage(headline, message).clickLogout().signIn(newLogin, newPassword)
                .clickNewMessageButton().enterMessage(newHeadline,newMessage).checkFieldsIsNotEmpty().
                createMessage().checkMessage(newHeadline, newMessage).messageListButtonClick()
                .checkLastMessage(newHeadline, newMessage).clickViewLastMessage()
                .checkMessage(newHeadline, newMessage).messageListButtonClick().clickLogout()
                .signIn(login, password)
                .checkTwoLastMessage(headline, message, newHeadline, newMessage);
    }
}
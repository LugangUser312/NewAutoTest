import com.qulix.selenium.learn.Message;
import com.qulix.selenium.learn.StartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

/**
 * Created by Starovoytovdv on 05.02.2018.
 */


@Listeners(ListnerTest.class)
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
                .enterMessage(mess).checkFieldsIsNotEmpty().createMessage()
                .messageListButtonClick().checkLastMessage(mess);

    }

    @Test (description = "Check view of creating message")
    @Parameters({"Login", "Password"})
    public void test2(String login, String password){
        System.out.println("Test 2 has started");
        Message mess = new Message("Zagolovok", "Textik");
        StartPage.openInDriver(driver).clickUserController().
                signIn(login, password).clickNewMessageButton()
                .enterMessage(mess).checkFieldsIsNotEmpty()
                .createMessage().checkMessage(mess).messageListButtonClick()
                .checkLastMessage(mess).clickViewLastMessage()
                .checkMessage(mess);

    }

    @Test (description = "Check ability to edit a message")
    @Parameters({"Login", "Password"})
    public void test3(String login, String password){
        System.out.println("Test 3 has started");
        Message mess = new Message("Zagolovok", "Textik");
        Message newMess = new Message("11111", "222222");
        StartPage.openInDriver(driver).clickUserController().
                signIn(login, password).clickNewMessageButton()
                .enterMessage(mess).checkFieldsIsNotEmpty().createMessage()
                .checkMessage(mess).messageListButtonClick()
                .checkLastMessage(mess).clickEditMessage()
                .checkMessage(mess)
                .editMessage(newMess)
                .checkMessage(newMess).clickSaveButton()
                .checkMessage(newMess).messageListButtonClick()
                .checkLastMessage(newMess);
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
                .enterMessage(mess).checkFieldsIsNotEmpty()
                .createMessage().checkMessage(mess).messageListButtonClick()
                .checkLastMessage(mess).clickDeleteMessage()
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
                .enterMessage(mess).checkFieldsIsNotEmpty().messageListButtonClick()
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
                enterMessage(mess).checkFieldsIsNotEmpty().createMessage()
                .checkMessage(mess).clickNewMessageButton()
                .enterMessage(newMess)
                .checkFieldsIsNotEmpty().createMessage().checkMessage(newMess)
                .messageListButtonClick()
                .checkTwoLastMessage(mess, newMess);
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
                .enterMessage(mess).checkFieldsIsNotEmpty().createMessage()
                .checkMessage(mess).messageListButtonClick()
                .checkLastMessage(mess)
                .clickViewLastMessage()
                .checkMessage(mess).messageListButtonClick()
                .checkLastMessage(mess).clickLogout().signIn(newLogin, newPassword)
                .clickNewMessageButton()
                .enterMessage(newMess).checkFieldsIsNotEmpty().
                createMessage().checkMessage(newMess).messageListButtonClick()
                .checkLastMessage(newMess).clickViewLastMessage()
                .checkMessage(newMess).messageListButtonClick().clickLogout()
                .signIn(login, password)
                .checkTwoLastMessage(mess, newMess);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
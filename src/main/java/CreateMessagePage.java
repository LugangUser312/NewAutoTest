import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

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
            throw new IllegalStateException("This is not the CreateMessagePage you are expected");
        }
        this.driver = driver;
    }


    @Step("Enter headline and description for message.")
    public CreateMessagePage enterMessage(String textHeadline, String textMessage){
        headline.clear();
        headline.sendKeys(textHeadline);
        text.clear();
        text.sendKeys(textMessage);
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


    @Step("Click button Message List")
    public MessageListPage messageListButtonClick(){
        messageListButton.click();
        return new MessageListPage(driver);
    }
}

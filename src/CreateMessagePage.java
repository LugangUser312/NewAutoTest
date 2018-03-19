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
            throw new IllegalStateException("This is not the CreateMessagePage you are expected");
        }
        this.driver = driver;
    }

    public CreateMessagePage enterMessage(String textHeadline, String textMessage){
        headline.clear();
        headline.sendKeys(textHeadline);
        text.clear();
        text.sendKeys(textMessage);
        return this;
    }

    public ShowMessagePage createMessage(){
        buttonCreate.click();
        return new ShowMessagePage(driver);
    }

    public CreateMessagePage checkFieldsIsNotEmpty(){
        //TODO Может тут все таки ||, а не &&?
        if (headline.getAttribute("value").isEmpty() || text.getAttribute("value").isEmpty()){
            throw new RuntimeException("Fields are empty");
        }else{
            //todo если хочешь вернуть эту же страницу, то и возвращай this. незачем каждый раз новый экземпляр создавать
            return this;
        }
    }

    public MessageListPage messageListButtonClick(){
        messageListButton.click();
        return new MessageListPage(driver);
    }

}

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
            throw new IllegalStateException("This is not the EditMessagePage are your expected.");
        }
        this.driver = driver;
    }

    public EditMessagePage checkMessage(String headline, String message){
        Assert.assertEquals(this.headline.getAttribute("value"), headline);
        Assert.assertEquals(this.message.getAttribute("value"), message);
        return this;
    }

    public EditMessagePage editMessage(String newHeadline, String newMessage){
        headline.clear();
        headline.sendKeys(newHeadline);
        message.clear();
        message.sendKeys(newMessage);
        return this;
    }

    public ShowMessagePage clickSaveButton(){
        buttonSave.click();
        return new ShowMessagePage(driver);
    }
}

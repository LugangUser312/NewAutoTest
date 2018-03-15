import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Starovoytovdv on 06.03.2018.
 */
public class LoginPage {

    private WebDriver driver;

    private static String URL_MATCH = "Login";

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(id = "login")
    private WebElement logineField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@class='save']")
    private WebElement buttonLogin;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        if (!title.getText().equals(URL_MATCH)){
            throw new IllegalStateException("This is not the LoginPage you are expected");
        }
        this.driver = driver;
    }

    public MessageListPage signIn(String login, String password) {
        logineField.clear();
        logineField.sendKeys(login);
        passwordField.clear();
        passwordField.sendKeys(password);//TODO А почему это поле не чистишь?
        buttonLogin.click();
        return new MessageListPage(driver);
    }

}

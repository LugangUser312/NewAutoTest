import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Starovoytovdv on 09.03.2018.
 */
public class StartPage {

    private WebDriver driver;

    private By userControllerLink = By.linkText("qulixteachingsite.UserController");


    public StartPage(WebDriver driver){
        //TODo почему это здесь? Не надо оно тут. Страница должна работать со своими элементами.
        // Можно сделать статик метод а-ля StartPage page = StartPage.openInDriver(driver),
        // Но ни в коем случае не зашивать навигацию куда-то внутрь и тем более в конструктор
        driver.get("http://localhost:8080/QulixTeachingSite");
        if (driver.findElements(userControllerLink).size() == 0){
            throw new IllegalStateException(
                    "This is not the StartPage you are expected");
        }
        this.driver = driver;
    }

    public LoginPage clickUserController(){
        driver.findElement(userControllerLink).click();
        return new LoginPage(driver);
    }
}

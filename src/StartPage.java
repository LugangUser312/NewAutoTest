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

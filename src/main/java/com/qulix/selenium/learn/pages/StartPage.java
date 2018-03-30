package com.qulix.selenium.learn.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Starovoytovdv on 09.03.2018.
 */

public class StartPage {

    private WebDriver driver;

    private By userControllerLink = By.linkText("qulixteachingsite.UserController");


    public StartPage(WebDriver driver){
        if (driver.findElements(userControllerLink).size() == 0){
            throw new IllegalStateException(
                    "This is not the StartPage you are expected");
        }
        this.driver = driver;
    }


    @Step("Open main page")
    public static StartPage openInDriver(WebDriver driver){
        driver.get("http://localhost:8080/QulixTeachingSite");
        return new StartPage(driver);
    }


    @Step("Click UserController link")
    public LoginPage clickUserController(){
        driver.findElement(userControllerLink).click();
        return new LoginPage(driver);
    }
}

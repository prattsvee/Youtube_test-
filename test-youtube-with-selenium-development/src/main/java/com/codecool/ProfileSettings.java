package com.codecool;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class ProfileSettings extends BasePage{

    public ProfileSettings(WebDriver driver)
    {
        super(driver);
    }

    //TODO LOGIN
    private final String url = "https://www.youtube.com/";

    private final By addEmail = By.xpath("//input[@type='email']");
    private final By clickONEmailButton = By.xpath("//button[@class=\"VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 qIypjc TrZEUc lw1w4b\"]");

    private final By passwordField2 = By.xpath("//div[@id='password']//input[@data-initial-value='']");
    private final By clickOnLogIn = By.xpath("(//button[@jsname=\"LgbsSe\"])[2]");

    public void navigate()
    {
        driver.navigate().to(url);
    }


    /*public void logInToYouTube()
    {
        try {
            WebElement signInButton = driver.findElement(By.xpath("//ytd-button-renderer[@class='signin style-scope ytd-consent-bump-v2-lightbox']"));
            if (signInButton.isDisplayed()) {
                signInButton.click();
            }
        } catch (NoSuchElementException e) {
            WebElement logInButton = driver.findElement(By.xpath("(//div/ytd-button-renderer[@class=\"style-scope ytd-masthead\"])[2]"));
            if (logInButton.isDisplayed()) {
                logInButton.click();
            }
        }
    }*/

    public void addEmail(String email)
    {
        driver.findElement(addEmail).sendKeys(email);
    }

    public void clickONEmailButton()
    {
        driver.findElement(clickONEmailButton).click();
    }

    public void addPassword(String word)
    {
        driver.findElement(passwordField2).sendKeys(word);
    }

    public void clickLogIn()
    {
        driver.findElement(clickOnLogIn).click();
    }

    //TODO PROFILE SETTINGS


    public void scrollDown()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    private final By settings = By.xpath("//a[@title='Beállítások']");

    public void clickSettings()
    {
        driver.findElement(settings).click();
    }

    private final By notifications = By.linkText("Értesítések");

    public void clickNotification()
    {
        driver.findElement(notifications).click();
    }

    private final By toggle = By.xpath("//tp-yt-paper-toggle-button[@role='button']");


    public void clickToggle() throws InterruptedException {
        List<WebElement> toggleButtons = driver.findElements(toggle);
        //toggleButtons.subList(0,1).clear();

        List<WebElement> newToggleButtons = toggleButtons.subList(2, 7);
        for(WebElement button: newToggleButtons)
        {
                button.click();
                Thread.sleep(300);
        }
    }
    public void diSelectToggle() throws InterruptedException {
        List<WebElement> toggleButtons = driver.findElements(toggle);
        //toggleButtons.subList(0,1).clear();

        List<WebElement> newToggleButtons = toggleButtons.subList(2, 7);
        for(WebElement button: newToggleButtons)
        {
            String checked = button.getAttribute("checked");
            if (checked != null /*&& checked.equals("true")*/){
                    button.click();
                    Thread.sleep(300);}

        }
    }

    private final By logOut = By.linkText("Kijelentkezés");

    //TODO LOGOUT

    private final By profileButton = By.id("avatar-btn");

    public void clickProfileButton()
    {
        driver.findElement(profileButton).click();
    }
    public void logOutYouTube()
    {
        if(driver.findElement(logOut).isDisplayed())
        {
            driver.findElement(logOut).click();
        }
    }
}

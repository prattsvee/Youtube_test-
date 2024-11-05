package com.codecool;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PlayVideo extends BasePage{

    public PlayVideo(WebDriver driver)
    {
        super(driver);
    }

    private final WebElement searchBar = driver.findElement(By.xpath("(//div[@class='style-scope ytd-searchbox'])[1]"));

    private final WebElement searchIcon = driver.findElement(By.id("search-icon-legacy"));
    public void searchVideo()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(searchBar));

        Actions actions = new Actions(driver);

        actions.moveToElement(searchBar).click().perform();
        actions.keyDown(Keys.SHIFT);
        actions.sendKeys("B");
        actions.keyUp(Keys.SHIFT);
        actions.sendKeys("ródy");
        actions.keyDown(Keys.SHIFT);
        actions.sendKeys("J");
        actions.keyUp(Keys.SHIFT);
        actions.sendKeys("ános ");

        actions.keyDown(Keys.SHIFT);
        actions.sendKeys("E");
        actions.keyUp(Keys.SHIFT);
        actions.sendKeys("zek ");
        actions.keyDown(Keys.SHIFT);
        actions.sendKeys("U");
        actions.keyUp(Keys.SHIFT);
        actions.sendKeys("gyanazok");

        actions.moveToElement(searchIcon).click().perform();
    }


    /*private final WebElement firstVideo = driver.findElement(By.xpath("(//div[@id=\"dismissible\" and @class=\"style-scope ytd-video-renderer\"])[1]"));

    public void selectFirstVideo()
    {

        firstVideo.click();

    }*/

    private final By playButton = By.xpath("//button[@class='ytp-play-button ytp-button' and @data-title-no-tooltip='Lejátszás']");
    private final By pauseButton = By.xpath("//button[@class='ytp-play-button ytp-button' and @data-title-no-tooltip='Szüneteltetés']");

    public void playYouTubeVideo()
    {
        driver.findElement(playButton).click();
    }
    public void pauseYouTubeVideo()
    {
        driver.findElement(pauseButton).click();
    }

    private final By fullScreen = By.xpath("//button[@class='ytp-fullscreen-button ytp-button']");

    public void switchToFullScreen()
    {
        driver.findElement(fullScreen).click();
    }
    public void exitFullScreen() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        /*js.executeScript("document.querySelector('video').requestFullscreen();");
        Thread.sleep(1000);*/
        js.executeScript("document.exitFullscreen();");
    }


    private final By settingsButton = By.xpath("//button[@class='ytp-button ytp-settings-button ytp-hd-quality-badge']");

    private final By playSpeedOption = By.xpath("(//div[@role='menuitem'])[1]");

    private final By acceleratePlaySpeed = By.xpath("//div[@class='ytp-menuitem-label' and text()=1.5]");

    public void clickSettingsButton()
    {
        driver.findElement(settingsButton).click();
    }
    public void choosePlaySpeed()
    {
        driver.findElement(playSpeedOption).click();
    }
    public void accelerateSpeed()
    {
        driver.findElement(acceleratePlaySpeed).click();
    }

    private final By likeButton = By.xpath("(//div[@id=\"segmented-like-button\"])[1]");
    private final By disLikeButton = By.xpath("(//div[@id=\"segmented-dislike-button\"])[1]");

    public void clickDisLike()
    {
        driver.findElement(disLikeButton).click();
    }

    public void clickLike()
    {
        driver.findElement(likeButton).click();
    }


}

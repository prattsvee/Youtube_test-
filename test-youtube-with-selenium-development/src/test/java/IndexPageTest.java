import com.codecool.PlayVideo;
import com.codecool.ProfileSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
//import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class IndexPageTest {

    WebDriver driver;

    @BeforeEach
    public void init()
    {
        //WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver",  "chromedriver");


        ChromeOptions options = new ChromeOptions();
        options.addArguments("ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        //options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");


        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void testLogIn() throws InterruptedException {
        ProfileSettings profileSettings = new ProfileSettings(driver);
        profileSettings.navigate();


        /*WebElement signInButton = null;
        try {
            signInButton = driver.findElement(By.xpath("//ytd-button-renderer[@class='signin style-scope ytd-consent-bump-v2-lightbox']"));
            if (signInButton.isDisplayed()) {
                signInButton.click();
            }
        } catch (NoSuchElementException e) {
            if(signInButton == null || !signInButton.isDisplayed()){
            WebElement logInButton = driver.findElement(By.xpath("(//div/ytd-button-renderer[@class=\"style-scope ytd-masthead\"])[2]"));
                if (logInButton.isDisplayed()) {
                    logInButton.click();
                }
            }
        }*/

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        if(wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ytd-button-renderer[@class='signin style-scope ytd-consent-bump-v2-lightbox']"))) != null){
                WebElement logInButton = driver.findElement(By.xpath("//ytd-button-renderer[@class='signin style-scope ytd-consent-bump-v2-lightbox']"));
                if (logInButton.isDisplayed()) {
                logInButton.click();
            }
        }   else {
            WebElement signInButton = driver.findElement(By.xpath("(//div/ytd-button-renderer[@class=\"style-scope ytd-masthead\"])[2]"));
            if (signInButton.isDisplayed()) {
                signInButton.click();

            }

        }

        profileSettings.addEmail("automationtester00666@gmail.com");
        profileSettings.clickONEmailButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@id='password'])"))));
        profileSettings.addPassword("Fecske111!");
        profileSettings.clickLogIn();
        Thread.sleep(3000);

    }
    public void testLogOut() throws InterruptedException {
        ProfileSettings profileSettings = new ProfileSettings(driver);
        profileSettings.clickProfileButton();
        Thread.sleep(1000);
        profileSettings.logOutYouTube();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.urlMatches("https://www.youtube.com/"));

        String urlExpected = "https://www.youtube.com/";
        Assertions.assertEquals(urlExpected, driver.getCurrentUrl());
        driver.quit();
    }
    @Test
    public void testChangeSettings() throws InterruptedException {

        testLogIn();

        ProfileSettings profileSettings = new ProfileSettings(driver);
        Thread.sleep(2000);
        profileSettings.scrollDown();
        Thread.sleep(4000);
        profileSettings.clickSettings();
        Thread.sleep(2000);
        profileSettings.clickNotification();
        Thread.sleep(4000);
        profileSettings.clickToggle();
        Thread.sleep(2000);
        profileSettings.diSelectToggle();
        Thread.sleep(2000);

        testLogOut();
    }


    @Test
    public void testPlayVideoYoutube() throws InterruptedException
    {
        testLogIn();

        PlayVideo playVideo = new PlayVideo(driver);
        playVideo.searchVideo();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id=\"dismissible\" and @class=\"style-scope ytd-video-renderer\"])[1]")));
        WebElement firstVideo = driver.findElement(By.xpath("(//div[@id=\"dismissible\" and @class=\"style-scope ytd-video-renderer\"])[1]"));
        firstVideo.click();


        boolean adIsPresent = true;
        while (adIsPresent) {
            try {
                WebElement adContainer = driver.findElement(By.className("ytp-ad-module"));
                if (adContainer.isDisplayed())
                {
                    List<WebElement> skipButtons = driver.findElements(By.className("ytp-ad-skip-button-container"));
                    if (skipButtons.size() > 0)
                    {
                        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
                        wait2.until(ExpectedConditions.elementToBeClickable(skipButtons.get(0)));

                        skipButtons.get(0).click();
                    } else {
                        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(30));
                        wait3.until(ExpectedConditions.invisibilityOf(adContainer));
                    }
                } else {
                    adIsPresent = false;
                }
            } catch (Exception e) {
                adIsPresent = false;
            }
        }

        playVideo.pauseYouTubeVideo();
        Thread.sleep(3000);
        playVideo.playYouTubeVideo();
        playVideo.switchToFullScreen();
        Thread.sleep(6000);
        playVideo.exitFullScreen();
        Thread.sleep(3000);


        playVideo.clickSettingsButton();
        playVideo.choosePlaySpeed();
        playVideo.accelerateSpeed();

        Thread.sleep(1500);
        playVideo.clickDisLike();
        Thread.sleep(1500);
        playVideo.clickLike();

        testLogOut();
    }



}

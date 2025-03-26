import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Sending_Photos_Test {
    AndroidDriver driver;


    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", "Android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:platformVersion", "16.0");
        caps.setCapability("appium:deviceName", "Android Emulator");
        caps.setCapability("appium:appPackage", "com.google.android.apps.photos");
        caps.setCapability("appium:appActivity", "com.google.android.apps.photos.home.HomeActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);

    }

    @Test
    public void sendPhotoTest() throws IOException {

        driver.pushFile("/sdcard/Pictures/TAU-logo.png", new File("C:/Users/Neto/Desktop/Appium-Project/resources/images/TAU-logo.png"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement getStarted = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.google.android.apps.photos:id/onboarding_action_button")));
        getStarted.click();

        WebElement allowBtn = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button")));
        allowBtn.click();
    }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
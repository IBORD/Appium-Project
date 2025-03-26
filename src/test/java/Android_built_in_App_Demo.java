import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Android_built_in_App_Demo {

    AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", "Android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:platformVersion", "16.0");
        caps.setCapability("appium:deviceName", "Android Emulator");
        caps.setCapability("appium:appPackage", "com.google.android.calculator");
        caps.setCapability("appium:appActivity", "com.android.calculator2.Calculator");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
    }

    @Test
    public void click_test() {
        WebElement digito1 = driver.findElement(AppiumBy.id("digit_1"));
        digito1.click();
        WebElement plus = driver.findElement(AppiumBy.id("op_add"));
        plus.click();
        WebElement digito3 = driver.findElement(AppiumBy.id("digit_3"));
        digito3.click();
        WebElement igual = driver.findElement(AppiumBy.id("eq"));
        igual.click();
        WebElement result = driver.findElement(AppiumBy.id("result_final"));
        String resultText = result.getText();
        Assert.assertEquals(resultText, "4");
    }
}

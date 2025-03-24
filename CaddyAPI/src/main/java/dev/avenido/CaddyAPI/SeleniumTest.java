package dev.avenido.CaddyAPI;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class SeleniumTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        // Configure headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run without UI

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.get("https://www.canadacomputers.com/en/916/powered-by-amd");


        System.out.println(driver.getTitle());

        //quit browser
        driver.quit();
    }
}

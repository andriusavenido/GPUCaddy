package dev.avenido.CaddyAPI.scraper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * If using singleton, might just want to prevent scrape calls from client
 * run scraping on a schedule
 */
@Configuration
public class ScraperConfig {

    private WebDriver webDriver;

    @Bean //bean singleton, one web driver
    @Lazy //created the first time it is accessed
    public WebDriver webDriver() {
        if (this.webDriver== null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--disable-gpu");
            this.webDriver = new ChromeDriver(options);
        }

        return this.webDriver;
    }

    public void restartWebDriver() { //quit driver until next use
        if (this.webDriver != null) {
            this.webDriver.quit();
        }
        this.webDriver = null;
    }
}

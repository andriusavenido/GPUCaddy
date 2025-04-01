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
    public WebDriver webDriver() {
        if (this.webDriver== null) {
            this.webDriver = createWebDriver();
        }

        return this.webDriver;
    }
    private WebDriver createWebDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu");
        return new ChromeDriver(options);
    }

    public synchronized void restartWebDriver() {
        System.out.println("From Config: Restarting WebDriver");

        if (this.webDriver != null) {
            try{
                this.webDriver.quit();
            }catch (Exception ignored){}
        }
        this.webDriver = createWebDriver();
    }
}

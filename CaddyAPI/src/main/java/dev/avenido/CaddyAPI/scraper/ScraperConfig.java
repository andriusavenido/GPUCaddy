package dev.avenido.CaddyAPI.scraper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScraperConfig {

    @Bean //bean singleton, one web driver
    public WebDriver webDriver(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        return new ChromeDriver(options);
    }
}

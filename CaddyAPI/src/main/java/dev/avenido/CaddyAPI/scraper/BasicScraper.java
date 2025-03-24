package dev.avenido.CaddyAPI.scraper;

import org.openqa.selenium.WebDriver;

//TODO scrapeByProduct
public abstract class BasicScraper {
    protected WebDriver driver;

    public BasicScraper(WebDriver driver) {
        this.driver = driver;
    }

    public abstract void scrape();

    protected void navigateToUrl(String url) {
        driver.get(url); //TODO exception handling
    }

}

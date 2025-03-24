package dev.avenido.CaddyAPI.scraper;

import dev.avenido.CaddyAPI.core.model.GPUProduct;
import org.openqa.selenium.WebDriver;

import java.util.List;

//TODO scrapeByProduct
public abstract class BasicScraper {
    protected WebDriver driver;

    public BasicScraper(WebDriver driver) {
        this.driver = driver;
    }

    public abstract List<GPUProduct> fetchAllGpuProducts();


    protected void navigateToUrl(String url) {
        driver.get(url); //TODO exception handling
    }

}

package dev.avenido.CaddyAPI.scraper;

import dev.avenido.CaddyAPI.core.model.GPUProduct;
import dev.avenido.CaddyAPI.core.model.GPUProductListing;
import dev.avenido.CaddyAPI.core.service.GPUProductList;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//TODO scrapeByProduct
//focus on how to scrape
public abstract class BasicScraper {
    protected WebDriver driver;
    protected ScraperConfig config;

    public BasicScraper(WebDriver driver, ScraperConfig config) {
        this.driver = driver;
        this.config = config;
    }

    public abstract List<GPUProduct> scrapeAllGpuProducts(GPUProductList gpuProductList);

    public abstract List<GPUProduct> scrapeByFilter(String[] modelList);


    protected void navigateToUrl(String url) {
        driver.get(url); //TODO exception handling
    }

}

package dev.avenido.CaddyAPI.scraper;

import dev.avenido.CaddyAPI.core.model.GPUProduct;
import dev.avenido.CaddyAPI.core.service.GPUProductList;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//TODO scrapeByProduct
//focus on how to scrape
public abstract class BasicScraper {
    protected WebDriver driver;
    protected ScraperConfig config;
    protected GPUProductList gpuProductList;

    public BasicScraper(WebDriver driver, ScraperConfig config, GPUProductList gpuProductList) {
        this.driver = driver;
        this.config = config;
        this.gpuProductList = gpuProductList;
    }

    public abstract List<GPUProduct> scrapeAllGpuProducts();

    public abstract List<GPUProduct> scrapeByModel(String[] modelList);


    protected void navigateToUrl(String url) {
        try {
            driver.get(url);
        } catch (IllegalArgumentException | InvalidArgumentException e) {
            System.err.println("Invalid URL provided: " + url);
            throw new RuntimeException("Navigation failed due to invalid URL.", e);
        } catch (NoSuchSessionException | SessionNotCreatedException e) {
            System.err.println("WebDriver session issue encountered. Restarting..." + e.getMessage());
            config.restartWebDriver();
            throw new RuntimeException("Navigation failed due to WebDriver session issue.", e);
        } catch (TimeoutException e) {
            System.err.println("Page load timed out for URL: " + url);
            throw new RuntimeException("Page timed out.", e);
        } catch (UnhandledAlertException e) {
            System.err.println("Unexpected alert blocked navigation.");
            driver.switchTo().alert().dismiss();
            driver.get(url); // Retry after handling the alert
        } catch (WebDriverException e) {
            System.err.println("A WebDriver error occurred: " + e.getMessage());
            throw new RuntimeException("Navigation failed due to WebDriver error.", e);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            throw new RuntimeException("Navigation failed.", e);
        }
    }

}

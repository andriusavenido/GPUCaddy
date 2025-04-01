package dev.avenido.CaddyAPI.scraper;

//Canada Computers Scraper

import dev.avenido.CaddyAPI.core.model.GPUProduct;
import dev.avenido.CaddyAPI.core.service.GPUProductList;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class CCScraper extends BasicScraper{

    private final String baseUrl = "https://www.canadacomputers.com/en/915/desktop-graphics-cards";

    public CCScraper(WebDriver driver, ScraperConfig config, GPUProductList gpuProductList) {
        super(driver, config, gpuProductList); //use config driver as context
    }

    @Override
    public List<GPUProduct> scrapeAllGpuProducts() {
        List<GPUProduct> scrapedProducts = new ArrayList<>();
        try{
            String[] modelList = gpuProductList.getGpuModelList();
            this.navigateToUrl(baseUrl);
            GenericScrape(scrapedProducts, modelList);
        } catch (Exception e){
            log.error("Failed Scrape:{}", e.getMessage());
            config.restartWebDriver();
            throw new RuntimeException(e.getMessage());
        }
        return scrapedProducts;
    }

    @Override
    public List<GPUProduct> scrapeByModel(String[] modelList) {
        List<GPUProduct> scrapedProducts = new ArrayList<>();
        try{
            StringBuilder filterUrl = new StringBuilder(baseUrl + "?q=GPU-");
            for (int i =0 ; i < modelList.length; i++) {
                if (i != 0) {
                    filterUrl.append("-");
                    filterUrl.append(modelList[i].replace(" ", "+"));
                }
                else{
                    filterUrl.append(modelList[i].replace(" ", "+"));
                }
            }
            this.navigateToUrl(filterUrl.toString());
            GenericScrape(scrapedProducts, modelList);


        } catch (Exception e){
            log.error("Failed Scrape:{}", e.getMessage());
            config.restartWebDriver();
            throw new RuntimeException(e.getMessage());
        }

        return scrapedProducts;
    }

    /**
     * Specific Scraping Method for Canada Computers
     * @param scrapedProducts
     * @param modelList
     */
    private void GenericScrape(List<GPUProduct> scrapedProducts, String[] modelList){
        //loop to address Canada Computers page, new products must be loaded into the page by scrolling/clicking load more button
        while (true) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // Find the "Load More" button
                WebElement loadMore = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".load-more")));

                // Scroll the button into view using JavaScript
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loadMore);

                // Wait until it becomes visible and clickable
                wait.until(ExpectedConditions.elementToBeClickable(loadMore));

                // Click the "Load More" button
                loadMore.click();

                // Wait for new products/content to load before proceeding
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".js-product")));
            } catch (NoSuchElementException | TimeoutException | ElementNotInteractableException e) {
                log.info("CC Scraper: No 'Load More' button or no more content to load");
                break;
            } catch (Exception e) {
                log.error("An unexpected error occurred: {}",e.getMessage());
                break;
            }
        }

        //logic to aggregate the page
        List<WebElement> productCards = driver.findElements(By.cssSelector(".js-product"));

        if (productCards.isEmpty()) {
            log.info("CC Scraper: No products found");
        }

        //create GPU product objects
        for (WebElement productCard : productCards){
            try{
                String name = productCard.findElement(By.cssSelector(".product-title a")).getText();
                Optional<String> matchModel = Arrays.stream(modelList).filter(model -> name.toLowerCase().contains(model.toLowerCase())).findFirst();
                String model =matchModel.orElse("");
                String manufacturer = name.split(" ")[0];
                String price = productCard.findElement(By.cssSelector(".price")).getText();
                if (!model.isEmpty()){
                    scrapedProducts.add(new GPUProduct(name, model, manufacturer, price));
                }

            } catch (NoSuchElementException e){
               log.info("CC Scraper:{}", e.getMessage());
            }
        }
    }

}

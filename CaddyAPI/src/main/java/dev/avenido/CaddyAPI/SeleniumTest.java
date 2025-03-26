package dev.avenido.CaddyAPI;

import dev.avenido.CaddyAPI.core.model.GPUProduct;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class SeleniumTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        // Configure headless mode
        ChromeOptions options = new ChromeOptions();

        WebDriver driver = new ChromeDriver(options);

        List<GPUProduct> scrapedProducts = scrapeByFilter(new String[] {"Radeon RX 9070 XT", "Radeon RX 9070"},"https://www.canadacomputers.com/en/915/desktop-graphics-cards",driver);

        for (GPUProduct gpuProduct : scrapedProducts){
            System.out.println(gpuProduct.toString());
        }

        //quit browser
        driver.quit();
    }

    public static List<GPUProduct> scrapeByFilter(String[] modelList, String baseUrl, WebDriver driver) {
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
            System.out.println(filterUrl.toString());
            driver.get(filterUrl.toString());

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
                    System.out.println("No 'Load More' button or no more content to load.");
                    break;
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                    break;
                }
            }

            //logic to aggregate the page
            List<WebElement> productCards = driver.findElements(By.cssSelector(".js-product"));

            if (productCards.isEmpty()) {
                System.out.println("No products found");
                return scrapedProducts;
            }

            for (WebElement productCard : productCards){
                try{
                    String name = productCard.findElement(By.cssSelector(".product-title a")).getText();
                    Optional<String> matchModel = Arrays.stream(modelList).filter(model -> name.toLowerCase().contains(model.toLowerCase())).findFirst();
                    String model =matchModel.orElse("");
                    String manufacturer = name.split(" ")[0];
                    String price = productCard.findElement(By.cssSelector(".price")).getText();
                    scrapedProducts.add(new GPUProduct(name, model, manufacturer, price));
                } catch (NoSuchElementException e){
                    System.out.println("No product found" + e.getMessage());
                }

            }

        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        return scrapedProducts;
    }
}

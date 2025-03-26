package dev.avenido.CaddyAPI.scraper;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ScraperFactory {

    private final Map<String, BasicScraper> scrapers = new HashMap<>();

    public ScraperFactory(CCScraper ccScraper) {
        scrapers.put("Canada Computers", ccScraper);
    }

    public BasicScraper getScraper(String site){
        BasicScraper scraper = scrapers.get(site);

        if (scraper == null){
            System.out.println("Scraper not found");
            throw new IllegalArgumentException("Scraper not found");
        }
        System.out.println("Scraper found");
        return scraper;
    }
}

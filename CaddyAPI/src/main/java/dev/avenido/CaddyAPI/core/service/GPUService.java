package dev.avenido.CaddyAPI.core.service;

import dev.avenido.CaddyAPI.scraper.BasicScraper;
import dev.avenido.CaddyAPI.scraper.ScraperFactory;
import org.springframework.stereotype.Service;

/**
 * This is part of the service layer, which handles business logic related to GPU data processing, database operations, and higher-level
 * Focus on the "what to do with scraped data"
 */

@Service
public class GPUService {
    private final ScraperFactory scraperFactory;

    public GPUService(ScraperFactory scraperFactory) {
        this.scraperFactory = scraperFactory;
    }

    public void refreshAllData(String site){
        BasicScraper scraper = scraperFactory.getScraper(site);
        //TODO: call scraper and update database
    }





}

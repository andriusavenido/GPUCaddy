package dev.avenido.CaddyAPI.core.service;

import dev.avenido.CaddyAPI.core.model.GPUProduct;
import dev.avenido.CaddyAPI.core.repository.GPURepository;
import dev.avenido.CaddyAPI.scraper.BasicScraper;
import dev.avenido.CaddyAPI.scraper.ScraperFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is part of the service layer, which handles business logic related to GPU data processing, database operations, and higher-level
 * Focus on the "what to do with scraped data"
 */
@Slf4j
@Service
public class GPUService {
    private final ScraperFactory scraperFactory;
    private final GPURepository gpuRepository;

    public GPUService(ScraperFactory scraperFactory, GPURepository gpuRepository) {
        this.scraperFactory = scraperFactory;
        this.gpuRepository = gpuRepository;
    }

    public boolean refreshAllDataBySite(String site){
        try {
            BasicScraper scraper = scraperFactory.getScraper(site);
            log.info("Scraping all data...");
            List<GPUProduct> scrapedList = scraper.scrapeAllGpuProducts();
            log.info("Scraping ended.");
            log.info("Updating data...");
            updateProductsByList(scrapedList);
            log.info("DB data updated.");
            return true;
        } catch (RuntimeException e) {
            System.err.println("Error refreshing site " + site + ": " + e.getMessage());
            return false;
        }
    }

    public boolean refreshByModels(String site, String[] modelList){
        try{
            BasicScraper scraper = scraperFactory.getScraper(site);
            log.info("Scraping all data...");
            List<GPUProduct> scrapedList = scraper.scrapeByModel(modelList);
            log.info("Scraping ended.");
            log.info("Updating data...");
            updateProductsByList(scrapedList);
            log.info("DB data updated.");
            return true;
        }catch (RuntimeException e) {
            System.err.println("Error refreshing site " + site + ": " + e.getMessage());
            return false;
        }

    }

    private void updateProductsByList(List<GPUProduct> scrapedList){
        for (GPUProduct gpuProduct : scrapedList){
            List<GPUProduct> existingProducts = gpuRepository.findByName(gpuProduct.getName());
            if (!existingProducts.isEmpty()){
                GPUProduct existingProduct = existingProducts.getFirst();
                existingProduct.setPrice(gpuProduct.getPrice());
                existingProduct.setLastUpdated(gpuProduct.getLastUpdated());
                gpuRepository.save(existingProduct);
            }else{
                gpuRepository.save(gpuProduct);
            }
        }
    }

    //BEEG: fetch methods from database

    //TODO filter might be done through repository
    public List<GPUProduct> getProductsByFilter(String[] filter){
        return null;
    }

    public List<GPUProduct> fetchAllGpuProducts(){return gpuRepository.findAll();}
    public List<GPUProduct> fetchGpuProductsByModel(String model){return gpuRepository.findByModel(model);}
    public List<GPUProduct> fetchGpuProductsByManufacturer(String manufacturer){return gpuRepository.findByManufacturer(manufacturer);}
    public List<GPUProduct> fetchGpuProductsByName(String name){return gpuRepository.findByName(name);}



}

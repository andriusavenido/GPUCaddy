package dev.avenido.CaddyAPI.core.service;

import dev.avenido.CaddyAPI.core.model.GPUProduct;
import dev.avenido.CaddyAPI.core.repository.GPURepository;
import dev.avenido.CaddyAPI.scraper.BasicScraper;
import dev.avenido.CaddyAPI.scraper.ScraperFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is part of the service layer, which handles business logic related to GPU data processing, database operations, and higher-level
 * Focus on the "what to do with scraped data"
 */

@Service
public class GPUService {
    private final ScraperFactory scraperFactory;
    private final GPURepository gpuRepository;

    public GPUService(ScraperFactory scraperFactory, GPURepository gpuRepository) {
        this.scraperFactory = scraperFactory;
        this.gpuRepository = gpuRepository;
    }

    public void refreshAllDataBySite(String site){
        BasicScraper scraper = scraperFactory.getScraper(site);
        List<GPUProduct> scrapedList = scraper.scrapeAllGpuProducts();

        for (GPUProduct gpuProduct : scrapedList){
            List<GPUProduct> existingProducts = gpuRepository.findByName(gpuProduct.getName());

            //update existing products
            if (!existingProducts.isEmpty()){
                GPUProduct existingProduct = existingProducts.getFirst();
                existingProduct.setPrice(gpuProduct.getPrice());
                existingProduct.setLastUpdated(gpuProduct.getLastUpdated());
                gpuRepository.save(existingProduct);
            }else{
                //save new product
                gpuRepository.save(gpuProduct);
            }

        }
    }

    //TODO refresh a single model
    public void refreshModel(String site, String model){

    }

    //BEEG: fetch methods from database
    public List<GPUProduct> fetchAllGpuProducts(){return gpuRepository.findAll();}
    public List<GPUProduct> fetchGpuProductsByModel(String model){return gpuRepository.findByModel(model);}
    public List<GPUProduct> fetchGpuProductsByManufacturer(String manufacturer){return gpuRepository.findByManufacturer(manufacturer);}
    public List<GPUProduct> fetchGpuProductsByName(String name){return gpuRepository.findByName(name);}



}

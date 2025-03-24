package dev.avenido.CaddyAPI.scraper;

//Canada Computers Scraper

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service
public class CCScraper extends BasicScraper{

    public CCScraper(WebDriver driver) {
        super(driver); //use config driver as context
    }

    @Override
    public void scrape() {

    }
}

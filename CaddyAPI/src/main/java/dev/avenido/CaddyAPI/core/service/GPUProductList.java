package dev.avenido.CaddyAPI.core.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.avenido.CaddyAPI.core.model.GPUProductListing;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@Service
public class GPUProductList { // reads json file for generic product listings

    private List<GPUProductListing> gpuListing;

    @PostConstruct //do it right after
    public void loadGpuListings(){
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("gpu.json")) {
            if (inputStream != null) {
                gpuListing = objectMapper.readValue(inputStream, new TypeReference<List<GPUProductListing>>() {});
            }else{
                throw new RuntimeException("Cannot find file");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading JSON file",e);
        }
    }

    public List<GPUProductListing> getGpuListings(){
        return this.gpuListing;
    }

    public String[] getGpuModelList(){
        String[] gpuModelList = new String[gpuListing.size()];
        for (int i = 0; i < gpuListing.size(); i++) {
            gpuModelList[i] = gpuListing.get(i).getModel();
        }
        return gpuModelList;
    }

    //TODO: add further methods for fetching specs etc getSpecs(model)...

}

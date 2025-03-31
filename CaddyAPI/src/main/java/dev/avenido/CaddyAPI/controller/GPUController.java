package dev.avenido.CaddyAPI.controller;

import dev.avenido.CaddyAPI.core.model.GPUProduct;
import dev.avenido.CaddyAPI.core.model.GPURequest;
import dev.avenido.CaddyAPI.core.service.GPUService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GPUController {
    private GPUService gpuService;

    public GPUController(GPUService service) {
        this.gpuService = service;
    }

    @PostMapping("/refresh-gpu-data")
    public ResponseEntity<String> refreshAllData (@RequestBody GPURequest request){
        boolean success = gpuService.refreshAllDataBySite(request.getSite());
        if(success){
            return ResponseEntity.ok("Data refreshed successfully for "+request.getSite());
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to refresh data for " + request.getSite());
        }
    }
    @PostMapping("/refresh-gpu-by-model")
    public ResponseEntity<String> refreshAllDataByModel (@RequestBody GPURequest request){
        boolean success = gpuService.refreshAllDataBySite(request.getSite());
        if(success){
            return ResponseEntity.ok("Data refreshed successfully for "+request.getSite());
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to refresh data for " + request.getSite());
        }
    }

    @GetMapping
    public ResponseEntity<List<GPUProduct>> getAllGpuData(){
        List<GPUProduct> gpuList = gpuService.fetchAllGpuProducts();
        if(gpuList.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }else{
            return ResponseEntity.ok(gpuList);
        }
    }

}

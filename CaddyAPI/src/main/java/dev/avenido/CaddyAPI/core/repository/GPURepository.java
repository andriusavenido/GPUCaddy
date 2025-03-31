package dev.avenido.CaddyAPI.core.repository;

import dev.avenido.CaddyAPI.core.model.GPUProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GPURepository extends JpaRepository<GPUProduct, Long> {

    //single attribute query
    List<GPUProduct> findByModel (String model);
    List<GPUProduct> findByManufacturer (String manufacturer);
    List<GPUProduct> findByName (String name);

    //add filter query

}

package dev.avenido.CaddyAPI.core.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "gpu_products")
public class GPUProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String model;
    private String manufacturer;
    private String price;
//    private boolean inStore;
//    private boolean inPerson;
    private LocalDate lastUpdated;

    public GPUProduct() {
        this.name = "";
        this.model = "";
        this.manufacturer = "";
        this.price = "";
        this.lastUpdated = LocalDate.now();
    }

    public GPUProduct(String name, String model, String manufacturer, String price) {
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
        this.price = price;
        this.lastUpdated = LocalDate.now();
    }

    public String getName() {
        return name;
    }
    public String getModel() {
        return model;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public String getPrice() {
        return price;
    }
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String toString() {
        return "GPUProduct{" + "name=" + name + ", model=" + model + ", manufacturer=" + manufacturer + ", price=" + price + '}';
    }


}

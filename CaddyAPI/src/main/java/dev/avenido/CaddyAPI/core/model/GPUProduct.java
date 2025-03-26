package dev.avenido.CaddyAPI.core.model;

public class GPUProduct {
    private String name;
    private String model;
    private String manufacturer;
    private String price;
//    private boolean inStore;
//    private boolean inPerson;

    public GPUProduct(String name, String model, String manufacturer, String price) {
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
        this.price = price;
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

    public String toString() {
        return "GPUProduct{" + "name=" + name + ", model=" + model + ", manufacturer=" + manufacturer + ", price=" + price + '}';
    }


}

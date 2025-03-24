package dev.avenido.CaddyAPI.core.model;

public class GPUProduct {
    private String name;
    private String model;
    private String manufacturer;
    private double price;
    private boolean inStore;
    private boolean inPerson;

    public GPUProduct(String name, String model, String manufacturer, double price, boolean inStore, boolean inPerson) {
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
        this.price = price;
        this.inStore = inStore;
        this.inPerson = inPerson;
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
    public double getPrice() {
        return price;
    }
    public boolean isInStore() {
        return inStore;
    }
    public boolean isInPerson() {
        return inPerson;
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
    public void setPrice(double price) {
        this.price = price;
    }
    public void setInStore(boolean inStore) {
        this.inStore = inStore;
    }
    public void setInPerson(boolean inPerson) {
        this.inPerson = inPerson;
    }

}

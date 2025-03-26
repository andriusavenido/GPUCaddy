package dev.avenido.CaddyAPI.core.model;

public class GPUProductListing {
    private String model;
    private int vram;
    private String brand;

    public String getModel() {return model;}
    public int getVram() {return vram;}
    public String getBrand() {return brand;}

    public void setModel(String model) {this.model = model;}
    public void setVram(int vram) {this.vram = vram;}
    public void setBrand(String brand) {this.brand = brand;}

    public String toString() {
        return "GPUProductListing{" + "model=" + model + ", vram=" + vram + ", brand=" + brand + '}';
    }

}

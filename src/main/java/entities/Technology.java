package main.java.entities;
import entities.Item;

import java.util.ArrayList;

public class Technology extends Item{
    private String brand;
    private ArrayList<String> capabilities;
    private String colour;
    Technology(String brand, ArrayList<String> capabilities, String colour){
        this.brand = brand;
        this.capabilities = capabilities;
        this.colour = colour;
    }

    public String getBrand(){return brand;}
    public ArrayList<String> getCapabilities(){return capabilities;}
    public String getColour(){return colour;}
    public void setBrand(String brand){this.brand = brand;}

    public void setCapabilities(ArrayList<String> capabilities) {this.capabilities = capabilities;}

    public void setColour(String colour) {this.colour = colour;}
}

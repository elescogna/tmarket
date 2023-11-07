package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Clothing extends Item {
    // TODO: fill these in
    private static final HashMap<String, ArrayList<String>> filterableAttributes = new HashMap<>();

    private String brand;
    private String colour;
    private String size;
    private String material;

    public Clothing(String id, String name, String description, String condition,
            double price, int age, boolean soldYet, String pickupAddress,
            double radius, Student owner, String type, String picture,
            LocalDateTime creationTime, String brand, String colour,
            String size, String material) {
        super(id, name, description, condition, price, age, soldYet, pickupAddress,
                radius, owner, type, picture, creationTime);
        this.brand = brand;
        this.colour = colour;
        this.size = size;
        this.material = material;
    }

    public void setBrand(String brand) { this.brand = brand; }

    public String getBrand() { return this.brand; }

    public void setColour(String colour) { this.colour = colour; }

    public String getColour() { return this.colour; }

    public void setSize(String size) { this.size = size; }

    public String getSize() { return this.size; }

    public void setMaterial(String material) { this.material = material; }

    public String getMaterial() { return this.material; }

    public static HashMap<String, ArrayList<String>> getFilterableattributes() {
        return filterableAttributes;
    }
}

package entities;

import java.time.LocalDateTime;

public class Clothing extends Item {
    // TODO: fill these in
    private static final String[] filterableAttributes = {};
    private static final String[] allowedTypes = {};

    private String brand;
    private String colour;
    private String size;
    private String material;

    public Clothing(String name, String description, String condition,
            double price, int age, boolean soldYet, String pickupAddress,
            double radius, Student owner, String type, String picture,
            LocalDateTime creationTime, String brand, String colour,
            String size, String material) {
        super(name, description, condition, price, age, soldYet, pickupAddress,
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

    public static String[] getFilterableattributes() {
        return filterableAttributes;
    }

    public static String[] getAllowedtypes() { return allowedTypes; }
}

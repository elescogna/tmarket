package entities;

import java.time.LocalDateTime;

public class SchoolItem extends Item {
    // TODO: fill these in
    private static final String[] filterableAttributes = {};
    private static final String[] allowedTypes = {};

    private String brand;
    private String colour;

    public SchoolItem(String id, String name, String description,
            String condition, double price, int age, boolean soldYet,
            String pickupAddress, double radius, Student owner,
            String type, String picture, LocalDateTime creationTime,
            String brand, String colour) {
        super(id, name, description, condition, price, age, soldYet, pickupAddress,
                radius, owner, type, picture, creationTime);
        this.brand = brand;
        this.colour = colour;
    }

    public void setBrand(String brand) { this.brand = brand; }

    public String getBrand() { return this.brand; }

    public void setColour(String colour) { this.colour = colour; }

    public String getColour() { return this.colour; }

    public static String[] getFilterableattributes() {
        return filterableAttributes;
    }

    public static String[] getAllowedtypes() { return allowedTypes; }
}

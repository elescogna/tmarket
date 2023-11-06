package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Technology extends Item {
    // TODO: fill these in
    private static final String[] filterableAttributes = {};
    private static final String[] allowedTypes = {};

    private String brand;
    private ArrayList<String> capabilities;
    private String colour;

    public Technology(String name, String description, String condition,
            double price, int age, boolean soldYet,
            String pickupAddress, double radius, Student owner,
            String type, String picture, LocalDateTime creationTime,
            String brand, ArrayList<String> capabilities,
            String colour) {
        super(name, description, condition, price, age, soldYet, pickupAddress,
                radius, owner, type, picture, creationTime);
        this.brand = brand;
        this.capabilities = capabilities;
        this.colour = colour;
    }

    public String getBrand() { return brand; }

    public ArrayList<String> getCapabilities() { return capabilities; }

    public String getColour() { return colour; }

    public void setBrand(String brand) { this.brand = brand; }

    public void setCapabilities(ArrayList<String> capabilities) {
        this.capabilities = capabilities;
    }

    public void setColour(String colour) { this.colour = colour; }

    public static String[] getFilterableattributes() {
        return filterableAttributes;
    }

    public static String[] getAllowedtypes() { return allowedTypes; }
}
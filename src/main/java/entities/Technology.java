package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Technology extends Item {
    // TODO: fill these in
    private static final HashMap<String, ArrayList<String>> filterableAttributes = new HashMap<>();

    private String brand;
    private String capabilities;
    private String colour;

    public Technology(String name, String description,
            int condition, int price, int age, boolean soldYet,
            String pickupAddress, String ownerId,
            String type, String picture, LocalDateTime creationTime,
            String brand, String capabilities,
            String colour) {
        super(name, description, condition, price, age, soldYet, pickupAddress,
                 ownerId, type, picture, creationTime);
        this.brand = brand;
        this.capabilities = capabilities;
        this.colour = colour;
    }

    public String getBrand() { return brand; }

    public String getCapabilities() { return capabilities; }

    public String getColour() { return colour; }

    public void setBrand(String brand) { this.brand = brand; }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public void setColour(String colour) { this.colour = colour; }

    public static HashMap<String, ArrayList<String>> getFilterableattributes() {
        return filterableAttributes;
    }
}

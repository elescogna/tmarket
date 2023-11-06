package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SchoolItem extends Item {
    private String brand;
    private String colour;

    public SchoolItem(String name, String description, String condition,
            double price, int age, boolean soldYet,
            String pickupAddress, double radius,
            ArrayList<String> filterableAttributes, Student owner,
            String type, String picture, LocalDateTime creationTime,
            String brand, String colour) {
        super(name, description, condition, price, age, soldYet, pickupAddress,
                radius, filterableAttributes, owner, type, picture, creationTime);
        this.brand = brand;
        this.colour = colour;
    }

    public void setBrand(String brand) { this.brand = brand; }

    public String getBrand() { return this.brand; }

    public void setColour(String colour) { this.colour = colour; }

    public String getColour() { return this.colour; }
}

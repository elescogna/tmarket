package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Furniture extends Item {
    // TODO: fill these in
    private static final HashMap<String, ArrayList<String>> filterableAttributes =
        new HashMap<>();

    private double length;
    private double width;
    private double height;

    public Furniture(String id, String name, String description, String condition,
            double price, int age, boolean soldYet, String pickupAddress,
            Student owner, String type, String picture,
            LocalDateTime creationTime, double length, double width,
            double height) {
        super(id, name, description, condition, price, age, soldYet, pickupAddress,
                owner, type, picture, creationTime);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getLength() { return length; }

    public double getWidth() { return width; }

    public double getHeight() { return height; }

    public void setLength(double length) { this.length = length; }

    public void setWidth(double width) { this.width = width; }

    public void setHeight(double height) { this.height = height; }

    public static HashMap<String, ArrayList<String>> getFilterableattributes() {
        return filterableAttributes;
    }
}

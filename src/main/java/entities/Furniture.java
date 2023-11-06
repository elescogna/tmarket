package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Furniture extends Item {
    private double length;
    private double width;
    private double height;

    public Furniture(String name, String description, String condition,
            double price, int age, boolean soldYet, String pickupAddress,
            double radius, ArrayList<String> filterableAttributes,
            Student owner, String type, String picture,
            LocalDateTime creationTime, double length, double width,
            double height) {
        super(name, description, condition, price, age, soldYet, pickupAddress,
                radius, filterableAttributes, owner, type, picture, creationTime);
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
}

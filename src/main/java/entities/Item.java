package entities;

import java.time.LocalDateTime;

public abstract class Item {
    private String id;
    private String name;
    private String description;
    private int condition;
    private double price;
    private int age;
    private boolean soldYet;
    private String pickupAddress;
    private String ownerId;
    private String type;
    private String picture;
    private LocalDateTime creationTime;

    public Item(String id, String name, String description, int condition,
            int price, int age, boolean soldYet, String pickupAddress,
            String ownerId, String type, String picture,
            LocalDateTime creationTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.price = price;
        this.age = age;
        this.soldYet = soldYet;
        this.pickupAddress = pickupAddress;
        this.ownerId = ownerId;
        this.type = type;
        this.picture = picture;
        this.creationTime = creationTime;
    }
    public Item(String name, String description, int condition, int price,
            int age, boolean soldYet, String pickupAddress, String ownerId,
            String type, String picture, LocalDateTime creationTime) {
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.price = price;
        this.age = age;
        this.soldYet = soldYet;
        this.pickupAddress = pickupAddress;
        this.ownerId = ownerId;
        this.type = type;
        this.picture = picture;
        this.creationTime = creationTime;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCondition() { return condition; }

    public void setCondition(int condition) { this.condition = condition; }

    public double getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public boolean isSoldYet() { return soldYet; }

    public void setSoldYet(boolean soldYet) { this.soldYet = soldYet; }

    public String getPickupAddress() { return pickupAddress; }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getPicture() { return picture; }

    public void setPicture(String picture) { this.picture = picture; }

    public LocalDateTime getCreationTime() { return creationTime; }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerId() { return ownerId; }
}

package entities;

public abstract class Item {
    String name;
    String description;
    String condition;
    float price;
    int age;
    boolean soldYet;
    String pickupAddress;
    float radius;

    public Item(String name, String description, String condition, float price,
            int age, boolean soldYet, String pickupAddress, float radius) {
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.price = price;
        this.age = age;
        this.soldYet = soldYet;
        this.pickupAddress = pickupAddress;
        this.radius = radius;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() { return condition; }

    public void setCondition(String condition) { this.condition = condition; }

    public float getPrice() { return price; }

    public void setPrice(float price) { this.price = price; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public boolean isSoldYet() { return soldYet; }

    public void setSoldYet(boolean soldYet) { this.soldYet = soldYet; }

    public String getPickupAddress() { return pickupAddress; }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public float getRadius() { return radius; }

    public void setRadius(float radius) { this.radius = radius; }
}

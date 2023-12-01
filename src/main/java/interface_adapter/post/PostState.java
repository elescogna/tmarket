package interface_adapter.post;

import entities.Student;

public class PostState {
    private Student student = null;
    private String name = "";
    private String description = "";
    private String pickupAddress = "";
    private int age = 0;
    private int price = 0;
    private double length = 0.0;
    private double height = 0.0;
    private double width = 0.0;
    private String category = "";
    private String type = "";
    private int conditionScore;
    private String brand = "";
    private String capabilities = "";
    private String colour = "";
    private String size = "";
    private String material = "";

    public PostState() {}

    public void setStudent(Student student) { this.student = student; }
    public void setName(String text) { this.name = text; }

    public void setDescription(String text) { this.description = text; }

    public void setPickupAddress(String text) { this.pickupAddress = text; }

    public void setAge(int text) { this.age = text; }

    public void setPrice(int i) { this.price = i; }

    public void setLength(double v) { this.length = v; }

    public void setCategory(String item) { this.category = item; }

    public void setType(String item) { this.type = item; }

    public void setConditionScore(int item) { this.conditionScore = item; }

    public void setWidth(double v) { this.width = v; }

    public void setHeight(double v) { this.height = v; }

    public void setBrand(String text) { this.brand = text; }

    public void setCapabilities(String text) { this.capabilities = text; }

    public void setColour(String text) { this.colour = text; }

    public void setSize(String text) { this.size = text; }

    public void setMaterial(String text) { this.material = text; }

    public Student getStudent() { return student; }
    public String getCategory() { return category; }

    public String getType() { return type; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getPickupAddress() { return pickupAddress; }

    public int getConditionScore() { return conditionScore; }

    public int getAge() { return age; }

    public int getPrice() { return price; }

    public double getLength() { return length; }

    public double getWidth() { return width; }

    public double getHeight() { return height; }

    public String getBrand() { return brand; }

    public String getCapabilities() { return capabilities; }

    public String getColour() { return colour; }

    public String getSize() { return size; }

    public String getMaterial() { return material; }
}

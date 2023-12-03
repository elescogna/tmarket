package use_case.post;

import entities.Student;

public class PostInputData {
    private Student student;
    private String name;
    private String description;
    private String pickupAddress;
    private int age;
    private int price;
    private double length;
    private double height;
    private double width;
    private String category;
    private String type;
    private int conditionScore;
    private String brand;
    private String capabilities;
    private String colour;
    private String size;
    private String material;

    public PostInputData(Student student, String category, String type, String name, String description,
                         String pickupAddress, int conditionScore, int age, int price, double length,
                         double width, double height) {
        this.student = student;
        this.category = category;
        this.type = type;
        this.name = name;
        this.description = description;
        this.pickupAddress = pickupAddress;
        this.conditionScore = conditionScore;
        this.age = age;
        this.price = price;
        this.length = length;
        this.height = height;
        this.width = width;
    }

    public PostInputData(Student student, String category, String type,
                         String name, String description, String pickupAddress, int conditionScore,
                         int age, int price, String brand, String capabilities, String colour){
        this.student = student;
        this.category = category;
        this.type = type;
        this.name = name;
        this.description = description;
        this.pickupAddress = pickupAddress;
        this.conditionScore = conditionScore;
        this.age = age;
        this.price = price;
        this.brand = brand;
        this.capabilities = capabilities;
        this.colour = colour;

    }

    public PostInputData(Student student, String category, String type,
                         String name, String description, String pickupAddress, int conditionScore,
                         int age, int price, String brand, String colour){
        this.student = student;
        this.category = category;
        this.type = type;
        this.name = name;
        this.description = description;
        this.pickupAddress = pickupAddress;
        this.conditionScore = conditionScore;
        this.age = age;
        this.price = price;
        this.brand = brand;
        this.colour = colour;
    }

    public  PostInputData(Student student, String category, String type,
                          String name, String description, String pickupAddress, int conditionScore,
                          int age, int price, String brand, String colour, String size, String material){
        this.student = student;
        this.category = category;
        this.type = type;
        this.name = name;
        this.description = description;
        this.pickupAddress = pickupAddress;
        this.conditionScore = conditionScore;
        this.age = age;
        this.price = price;
        this.brand = brand;
        this.colour = colour;
        this.size = size;
        this.material = material;
    }

    public Student getStudent(){
        return student;
    }
    public String getCategory() {
        return category;
    }
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getPickupAddress(){
        return pickupAddress;
    }
    public int getConditionScore() {
        return conditionScore;
    }
    public int getAge() {
        return age;
    }
    public int getPrice() {
        return price;
    }
    public double getLength() {
        return length;
    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    public String getBrand() {
        return brand;
    }
    public String getCapabilities() {
        return capabilities;
    }
    public String getColour() {
        return colour;
    }
    public String getSize() {
        return size;
    }
    public String getMaterial() {
        return material;
    }
}

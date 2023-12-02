package use_case.create_order;

import entities.Item;
import entities.Student;

public class CreateOrderInputData {
    private Item item;
    private Student student;
    private String buyerEmail = "";
    private String sameAddress = "";
    private String otherAddress = "";
    private String itemName = "";

    public CreateOrderInputData(Item item, Student student, String buyerEmail, String sameAddress, String otherAddress, String itemName) {
        this.item = item;
        this.student = student;
        this.buyerEmail = buyerEmail;
        this.sameAddress = sameAddress;
        this.otherAddress = otherAddress;
        this.itemName = itemName;
    }

    public Item getItem() {
        return item;
    }

    public Student getStudent() {
        return student;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getSameAddress() {
        return sameAddress;
    }

    public String getOtherAddress() {
        return otherAddress;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public void setSameAddress(String sameAddress) {
        this.sameAddress = sameAddress;
    }

    public void setOtherAddress(String otherAddress) {
        this.otherAddress = otherAddress;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}

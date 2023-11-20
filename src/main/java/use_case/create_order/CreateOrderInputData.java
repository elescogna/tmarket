package use_case.create_order;

import entities.Item;
import entities.Student;

public class CreateOrderInputData {
    private Item item;
    private Student student;
    private String buyerEmail = "";
    private String sameAddress = "";
    private String otherAddress = "";

    public CreateOrderInputData(Item item, Student student, String buyerEmail, String sameAddress, String otherAddress) {
        this.item = item;
        this.student = student;
        this.buyerEmail = buyerEmail;
        this.sameAddress = sameAddress;
        this.otherAddress = otherAddress;
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
}
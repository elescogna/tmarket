package interface_adapter.create_order;

import entities.Item;
import entities.Student;

public class CreateOrderState {
    private Student student;
    private String emailError = null;
    private String buyerEmail = "";
    private String sameAddress = "";
    private String otherAddress = "";
    private Item item;

    /**
     * Default constructor for the CreateOrderState.
     */
    public CreateOrderState() {
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setSameAddress(String sameAddress) {
        this.sameAddress = sameAddress;
    }

    public String getSameAddress() {
        return sameAddress;
    }

    public void setOtherAddress(String otherAddress) {
        this.otherAddress = otherAddress;
    }

    public String getOtherAddress() {
        return otherAddress;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "CreateOrderState{" +
                "Student name='" + student.getName() + '\'' +
                ", buyerEmail='" + buyerEmail + '\'' +
                ", sameAddress='" + sameAddress + '\'' +
                ", otherAddress='" + otherAddress + '\'' +
                ", item ID='" + item.getId() + '\'' +
                '}';
    }
}


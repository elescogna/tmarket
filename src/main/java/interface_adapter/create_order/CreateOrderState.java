package interface_adapter.create_order;

import entities.Item;

public class CreateOrderState {
    private String sellerEmail = "";
    private String emailError = null;
    private String buyerEmail = "";
    private String sameAddress = "";
    private String otherAddress = "";
    private Item item;

    public CreateOrderState(CreateOrderState copy) {
        sellerEmail = copy.sellerEmail;
        emailError = copy.emailError;
        buyerEmail = copy.buyerEmail;
        otherAddress = copy.otherAddress;
        sameAddress = copy.sameAddress;
        item = copy.item;

    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public CreateOrderState() {
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerEmail() {
        return sellerEmail;
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
                "buyerEmail='" + buyerEmail + '\'' +
                ", sameAddress='" + sameAddress + '\'' +
                ", otherAddress='" + otherAddress + '\'' +
                ", item ID='" + item.getId() + '\'' +
                '}';
    }
}


package use_case.create_order;

import entities.Item;

public class CreateOrderInputData {
    private Item item;
    private String sellerEmail = "";
    private String buyerEmail = "";
    private String sameAddress = "";
    private String otherAddress = "";

    public CreateOrderInputData(Item item, String sellerEmail, String buyerEmail, String sameAddress, String otherAddress) {
        this.item = item;
        this.sellerEmail = sellerEmail;
        this.buyerEmail = buyerEmail;
        this.sameAddress = sameAddress;
        this.otherAddress = otherAddress;
    }

    public Item getItem() {
        return item;
    }

    public String getSellerEmail() {
        return sellerEmail;
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

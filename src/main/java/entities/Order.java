package entities;

public class Order {
    private String id;
    private String buyerEmail;
    private String sellerEmail;
    private Item item;
    private String pickupLocation;

    public Order(String id, String buyerEmail, String sellerEmail, Item item,
            String pickupLocation) {
        this.id = id;
        this.buyerEmail = buyerEmail;
        this.sellerEmail = sellerEmail;
        this.item = item;
        this.pickupLocation = pickupLocation;
    }

    public void setBuyerEmail(String buyerEmail) { this.buyerEmail = buyerEmail; }

    public String getBuyerEmail() { return buyerEmail; }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerEmail() { return sellerEmail; }

    public void setItem(Item item) { this.item = item; }

    public Item getItem() { return item; }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getPickupLocation() { return pickupLocation; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }
}

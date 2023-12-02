package entities;

public class Order {
    private String id;
    private String buyerEmail;
    private String sellerEmail;
    private String itemId;
    private String pickupLocation;
    private String itemName;

    public Order(String id, String buyerEmail, String sellerEmail, String itemId,
            String pickupLocation, String itemName) {
        this.id = id;
        this.buyerEmail = buyerEmail;
        this.sellerEmail = sellerEmail;
        this.itemId = itemId;
        this.pickupLocation = pickupLocation;
        this.itemName = itemName;
    }

    public void setBuyerEmail(String buyerEmail) { this.buyerEmail = buyerEmail; }

    public String getBuyerEmail() { return buyerEmail; }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerEmail() { return sellerEmail; }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getPickupLocation() { return pickupLocation; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getItemId() { return itemId; }

    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getItemName() { return itemName; }

    public void setItemName(String itemName) { this.itemName = itemName; }
}

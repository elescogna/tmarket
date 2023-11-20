package use_case.contact;

import entities.Item;

public class ContactInputData {
    final private Item itemToSell;
    final private String subject;
    final private String body;
    final private String destinationAddress;

    public ContactInputData(Item itemToSell, String subject, String body,
            String destinationAddress) {
        this.itemToSell = itemToSell;
        this.subject = subject;
        this.body = body;
        this.destinationAddress = destinationAddress;
    }

    public Item getItemToSell() { return itemToSell; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public String getDestinationAddress() { return destinationAddress; }
}

package use_case.contact;

import entities.Item;

public class ContactInputData {
    final private Item itemToSell;
    final private String subject;
    final private String body;

    /**
     * Creates a new ContactInputData with the given parameters
     *
     * @param itemToSell the item with which to create the ContactInputData
     * @param subject the email subject with which to create the ContactInputData
     * @param body the email body with which to create the ContactInputData
     */
    public ContactInputData(Item itemToSell, String subject, String body) {
        this.itemToSell = itemToSell;
        this.subject = subject;
        this.body = body;
    }

    public Item getItemToSell() { return itemToSell; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
}

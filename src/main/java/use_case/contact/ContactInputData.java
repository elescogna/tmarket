package use_case.contact;

import entities.Item;

public class ContactInputData {
    final private Item itemToSell;
    final private String subject;
    final private String body;

    public ContactInputData(Item itemToSell, String subject, String body) {
        this.itemToSell = itemToSell;
        this.subject = subject;
        this.body = body;
    }

    public Item getItemToSell() { return itemToSell; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
}

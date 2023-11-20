package use_case.contacting;

import entities.Item;

public class ContactingOutputData {
    final private Item itemToSell;

    public ContactingOutputData(Item itemToSell) { this.itemToSell = itemToSell; }

    public Item getItemToSell() { return itemToSell; }
}

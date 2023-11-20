package use_case.contacting;

import entities.Item;

public class ContactingInputData {
    final private Item itemToSell;

    public ContactingInputData(Item itemToSell) { this.itemToSell = itemToSell; }

    public Item getItemToSell() { return itemToSell; }
}

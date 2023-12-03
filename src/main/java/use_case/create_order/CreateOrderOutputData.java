package use_case.create_order;

import entities.Item;

public class CreateOrderOutputData {
    Item item;

    public CreateOrderOutputData(Item item) { this.item = item; }

    public Item getItem() { return item; }

    public void setItem(Item item) { this.item = item; }
}

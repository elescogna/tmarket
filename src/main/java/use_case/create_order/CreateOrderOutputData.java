package use_case.create_order;

import entities.Item;

public class CreateOrderOutputData {
    Item item;

    /**
     * Creates a new CreateOrderOutputData with the given item
     *
     * @param item the item with which to create the output data
     */
    public CreateOrderOutputData(Item item) { this.item = item; }

    public Item getItem() { return item; }

    public void setItem(Item item) { this.item = item; }
}

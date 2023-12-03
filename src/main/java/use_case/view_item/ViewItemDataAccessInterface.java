package use_case.view_item;

import entities.Item;

import java.io.IOException;

import javax.swing.ImageIcon;

public interface ViewItemDataAccessInterface {
    Item getItem(String idToGet) throws IOException;
}

package misc.backend.CRUDdemo.services;

import java.util.List;

import misc.backend.CRUDdemo.models.ItemEntity;

public interface ItemService {

    // Service function for getting all items
    List<ItemEntity> getAllItems();

    // Service function for getting an item by id
    ItemEntity getItemById(Long id);

    // Service function for creating an item
    ItemEntity createItem(ItemEntity item);

    // Service function for updating an item
    boolean updateItem(long id, ItemEntity item);

    // Service function for deleting an item by id
    void deleteItemById(Long id);

    // Service function for deleting all items
    void deleteAllItems();

}

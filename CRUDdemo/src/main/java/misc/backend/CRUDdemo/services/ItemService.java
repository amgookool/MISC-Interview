package misc.backend.CRUDdemo.services;

import java.util.List;

import misc.backend.CRUDdemo.models.ItemEntity;

// The service interface for the item entity
// We define the service functions that will be used in the controller
// This file is treated as a Java interface // header file in C/C++
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

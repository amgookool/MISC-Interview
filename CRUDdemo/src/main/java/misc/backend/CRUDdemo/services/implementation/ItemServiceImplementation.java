package misc.backend.CRUDdemo.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import misc.backend.CRUDdemo.models.ItemEntity;
import misc.backend.CRUDdemo.repository.ItemRepository;
import misc.backend.CRUDdemo.services.ItemService;

@Service
public class ItemServiceImplementation implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImplementation(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Service function for getting all items
    @Override
    public List<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }

    // Service function for getting an item by id
    @Override
    public ItemEntity getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    // Service function for creating an item
    @Override
    public ItemEntity createItem(ItemEntity item) {
        return itemRepository.save(item);
    }

    // Service function for updating an item
    @Override
    public boolean updateItem(long id, ItemEntity item) {
        ItemEntity existingItem = itemRepository.findById(id).orElse(null);
        if (existingItem != null) {
            existingItem.setName(item.getName());
            existingItem.setDescription(item.getDescription());
            itemRepository.save(existingItem);
            return true;
        } else {
            return false;
        }

        // ItemEntity itemData = itemService.getItemById(id);
        // if (itemData != null) {
        // itemData.setName(item.getName());
        // itemData.setDescription(item.getDescription());
        // return new ResponseEntity<>(itemService.createItem(itemData), HttpStatus.OK);
        // } else {
        // return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        // }





    }

    // Service function for deleting an item by id
    @Override
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }

    // Service function for deleting all items
    @Override
    public void deleteAllItems() {
        itemRepository.deleteAll();
    }

}

package misc.backend.CRUDdemo.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import misc.backend.CRUDdemo.models.ItemEntity;
import misc.backend.CRUDdemo.repository.ItemRepository;
import misc.backend.CRUDdemo.services.ItemService;

// The service implementation class implements the service interface for the item entity
// We define the service functions that will be used in the controller
// Here we can access the repository methods for performing CRUD operations on the entity
@Service
public class ItemServiceImplementation implements ItemService {
    // Initialize the item repository object
    private final ItemRepository itemRepository;

    // Constructor for the service implementation class
    public ItemServiceImplementation(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Service function for getting all items
    @Override // Override the function from the interface
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
        // Get the item by id
        ItemEntity existingItem = itemRepository.findById(id).orElse(null);
        // If the item exists, update it with the new values and save it
        if (existingItem != null) {
            existingItem.setName(item.getName());
            existingItem.setDescription(item.getDescription());
            itemRepository.save(existingItem);
            return true;
        } else {
            return false;
        }
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

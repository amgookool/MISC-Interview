package misc.backend.CRUDdemo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import misc.backend.CRUDdemo.models.ItemEntity;
import misc.backend.CRUDdemo.services.ItemService;

// Controller class for handling requests to the API
@RestController 
// Allow requests from this origin
@CrossOrigin(origins = "http://localhost:5173") 
// Base path for all requests to this controller
@RequestMapping("/api/items")
public class ItemController {
    // Autowire the item service
    @Autowired
    ItemService itemService;

    // Controller function for getting all items
    @GetMapping
    public ResponseEntity<List<ItemEntity>> getAllitems() {
        try {
            // Create a list of items
            List<ItemEntity> items = new ArrayList<ItemEntity>();
            // Add all items to the list
            itemService.getAllItems().forEach(items::add);
            // If the list is empty, return no content
            if (items.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            // Else return the list of items
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Controller function for getting a single item by id
    @GetMapping("/{id}")
    public ResponseEntity<ItemEntity> getItemById(@PathVariable("id") long id) {
        try {
            // Get the item by id
            ItemEntity item = itemService.getItemById(id);
            // If the item exists, return it
            if (item != null) {
                return new ResponseEntity<>(item, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Controller function for creating a new item
    @PostMapping
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemEntity item) {
        try {
            // Create the item
            ItemEntity _item = itemService.createItem(item);
            return new ResponseEntity<>(_item, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Controller function for updating an item
    @PutMapping("/{id}")
    public ResponseEntity<ItemEntity> updateItem(@PathVariable("id") long id, @RequestBody ItemEntity item) {
        try {
            // Update the item
            boolean updated = itemService.updateItem(id, item);
            // If the item was updated, return it
            if (updated) {
                return new ResponseEntity<>(item, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Controller function for deleting an item by id
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteItemById(@PathVariable("id") long id) {
        try {
            // Delete the item
            itemService.deleteItemById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Controller function for deleting all items
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllItems() {
        try {
            // Delete all items
            itemService.deleteAllItems();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package misc.backend.CRUDdemo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import misc.backend.CRUDdemo.models.ItemEntity;
import misc.backend.CRUDdemo.services.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    // Controller function for getting all items
    @GetMapping
    public ResponseEntity<List<ItemEntity>> getAllitems() {
        try {
            List<ItemEntity> items = new ArrayList<ItemEntity>();

            itemService.getAllItems().forEach(items::add);

            if (items.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Controller function for getting a single item by id
    @GetMapping("/{id}")
    public ResponseEntity<ItemEntity> getItemById(@PathVariable("id") long id) {
        try {
            ItemEntity item = itemService.getItemById(id);

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
            boolean updated = itemService.updateItem(id, item);

            if (updated) {
                return new ResponseEntity<>(item, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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

    // Controller function for deleting an item by id
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteItemById(@PathVariable("id") long id) {
        try {
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
            itemService.deleteAllItems();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package se.iths.complexjavaproject.controller;

import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.Address;
import se.iths.complexjavaproject.entity.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.iths.complexjavaproject.service.ItemService;

import java.io.FileNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Item>> getAddressById(@PathVariable Long id){
        Optional<Item> foundItem = itemService.getItemById(id);
        return new ResponseEntity<>(foundItem, HttpStatus.FOUND);

    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable Long id) throws FileNotFoundException {
        Item foundAddress = itemService.getItemById(id).orElseThrow(FileNotFoundException::new);
        itemService.deleteItem(foundAddress.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<Item>> findAllUsers() {
        Iterable<Item> allItems = itemService.getAllItems();
        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }
}

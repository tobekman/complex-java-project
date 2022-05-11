package se.iths.complexjavaproject.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.DTO.mapper.Mapper;
import se.iths.complexjavaproject.entity.DTO.ItemDTO;
import se.iths.complexjavaproject.entity.Item;
import se.iths.complexjavaproject.exception.EntityNotFoundException;
import se.iths.complexjavaproject.service.ItemService;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final Mapper mapper;

    public ItemController(ItemService itemService, Mapper mapper) {
        this.itemService = itemService;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody Item item) {
        itemService.createItem(item);
        ItemDTO itemDTO = (ItemDTO) mapper.toDto(item);
        return new ResponseEntity<>(itemDTO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Long id) throws FileNotFoundException {
        Item foundItem = itemService.getItemById(id).orElseThrow(() -> new EntityNotFoundException("Item with id: " + id + " is not found in database."));
        ItemDTO itemDTO = (ItemDTO) mapper.toDto(foundItem);
        return new ResponseEntity<>(itemDTO, HttpStatus.FOUND);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable Long id) throws FileNotFoundException {
        Item foundItem = itemService.getItemById(id).orElseThrow(() -> new EntityNotFoundException("Item with id: " + id + " is not found in database."));
        itemService.deleteItem(foundItem.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> findAllItems() {
        List allItemsDTO = StreamSupport.stream(itemService.getAllItems().spliterator(), false)
                .map(mapper::toDto)
                .collect(toList());
        return new ResponseEntity<>(allItemsDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ItemDTO> updateItem(@RequestBody Item item) throws FileNotFoundException {
        if(itemService.getItemById(item.getId()).isEmpty()){
            throw new EntityNotFoundException("Item is not found in database.");
        }else{
            itemService.createItem(item);
            ItemDTO itemDTO = (ItemDTO) mapper.toDto(item);
            return new ResponseEntity<>(itemDTO, HttpStatus.FOUND);
        }

    }
}

package se.iths.complexjavaproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.DTO.mapper.Mapper;
import se.iths.complexjavaproject.entity.DTO.ItemDTO;
import se.iths.complexjavaproject.entity.Item;
import se.iths.complexjavaproject.service.ItemService;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private Mapper mapper;

    public ItemController(ItemService itemService, Mapper mapper) {
        this.itemService = itemService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody Item item) {
        itemService.createItem(item);
        ItemDTO itemDTO = (ItemDTO) mapper.toDto(item);
        return new ResponseEntity<>(itemDTO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<ItemDTO>> getAddressById(@PathVariable Long id){
        Optional<Item> foundItem = itemService.getItemById(id);
        Optional<ItemDTO> itemDTO = (Optional<ItemDTO>) mapper.toDto(foundItem);
        return new ResponseEntity<>(itemDTO, HttpStatus.FOUND);

    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable Long id) throws FileNotFoundException {
        Item foundItem = itemService.getItemById(id).orElseThrow(FileNotFoundException::new);
        itemService.deleteItem(foundItem.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> findAllUsers() {
        List allItemsDTO = StreamSupport.stream(itemService.getAllItems().spliterator(), false)
                .map(mapper::toDto)
                .collect(toList());
        return new ResponseEntity<>(allItemsDTO, HttpStatus.OK);
    }
}

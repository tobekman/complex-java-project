package se.iths.complexjavaproject.service;

import se.iths.complexjavaproject.entity.Category;
import se.iths.complexjavaproject.entity.Item;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.repository.ItemRepository;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }



    public Optional<Item> getItemById(Long id){return itemRepository.findById(id);}

    public void deleteItem(Long id){Item result = itemRepository.findById(id).orElseThrow(EntityExistsException::new);
        itemRepository.deleteById(result.getId());}

    public Iterable<Item> getAllItems(){return itemRepository.findAll();}


}

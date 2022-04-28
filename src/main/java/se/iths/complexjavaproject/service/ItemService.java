package se.iths.complexjavaproject.service;

import se.iths.complexjavaproject.entity.Item;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }


}

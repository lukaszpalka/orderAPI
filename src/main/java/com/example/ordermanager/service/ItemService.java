package com.example.ordermanager.service;

import com.example.ordermanager.dto.ItemDto;
import com.example.ordermanager.model.Item;
import com.example.ordermanager.repositories.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepo;

    public ItemService(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public Item getItemById(Long id) {
        return itemRepo.findById(id).orElseThrow(() -> new NullPointerException("Not found"));
    }

    public void updateItemById(Long id, ItemDto itemDto) {
        Item item = getItemById(id);
        if (itemDto.name() != null) {
            item.setName(itemDto.name());
        }
        if (itemDto.price() != null) {
            item.setPrice(itemDto.price());
        }
        if (itemDto.amount() != null) {
            item.setAmount(itemDto.amount());
        }
        itemRepo.save(item);
    }

}

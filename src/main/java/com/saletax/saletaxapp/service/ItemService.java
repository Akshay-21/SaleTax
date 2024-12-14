package com.saletax.saletaxapp.service;

import com.saletax.saletaxapp.model.Item;
import com.saletax.saletaxapp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    public Item updateItems(Item item) {
        if (itemRepository.existsById(item.getId())) {
            return itemRepository.save(item);
        }
        return null;
    }

    public void deleteItems(Long id) {
        itemRepository.deleteById(id);
    }
}
package com.cpan228.ClothingStore.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.cpan228.ClothingStore.model.Item;
import com.cpan228.ClothingStore.model.Item.Brand;


public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByBrandName(Brand brandName);
    List<Item> findByItemYear(int itemYear);
}

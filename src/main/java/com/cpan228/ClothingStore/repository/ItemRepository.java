package com.cpan228.ClothingStore.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.cpan228.ClothingStore.model.Item;
import com.cpan228.ClothingStore.model.Item.Brand;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByBrandNameAndItemYear(Brand brandName, int itemYear);
}

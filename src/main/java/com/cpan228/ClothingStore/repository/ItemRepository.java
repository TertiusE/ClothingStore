package com.cpan228.ClothingStore.repository;
import org.springframework.data.repository.CrudRepository;
import com.cpan228.ClothingStore.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
    
}

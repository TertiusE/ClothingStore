package com.cpan228.ClothingStore.repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cpan228.ClothingStore.model.Item;

@Repository
public interface ItemRepositoryPaginated extends PagingAndSortingRepository<Item, Long> {
}

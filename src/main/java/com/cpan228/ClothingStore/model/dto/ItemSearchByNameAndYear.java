package com.cpan228.ClothingStore.model.dto;

import com.cpan228.ClothingStore.model.Item.Brand;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ItemSearchByNameAndYear {
    private Brand brandName;
    private int itemYear;
}

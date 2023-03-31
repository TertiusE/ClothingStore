package com.cpan228.ClothingStore.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ItemSearchByNameAndYear {
    private String brandName;
    private int itemYear;
}

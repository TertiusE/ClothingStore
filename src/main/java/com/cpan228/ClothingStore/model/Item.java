package com.cpan228.ClothingStore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    private Brand brandName;
    @Min(2021)
    private int year;
    @Min(1000)
    private double price;

    public enum Brand {
        BALENCIAGA("Balenciaga"), STONE_ISLAND("Stone Island"), DIOR("Dior"), GUCCI("Gucci"), LOUIS_VUITTON("Louis Vuitton"), KATE_SPADE("Kate Spade"), ADIDAS("Adidas"), NIKE("Nike");

        private String name;
        private Brand(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }

    
}

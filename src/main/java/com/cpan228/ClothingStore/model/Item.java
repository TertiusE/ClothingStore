package com.cpan228.ClothingStore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String itemName;
    private String brandName;
    @Min(2021)
    private int itemYear;
    @Min(1000)
    private double price;

    public enum Brand {
        BALENCIAGA("Balenciaga"), STONE_ISLAND("Stone Island"), DIOR("Dior"), GUCCI("Gucci"), LOUIS_VUITTON("Louis Vuitton"), KATE_SPADE("Kate Spade"), ADIDAS("Adidas"), NIKE("Nike");

        protected String title;
        private Brand(String title){
            this.title = title;
        }

        public String getTitle(){
            return title;
        }

    }

    
}

package com.cpan228.ClothingStore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cpan228.ClothingStore.model.Item;
import com.cpan228.ClothingStore.model.Item.Brand;
import com.cpan228.ClothingStore.repository.ItemRepository;

@SpringBootApplication
public class ClothingStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClothingStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner storeLoader(ItemRepository repository) {
		return args -> {
			repository.save(Item.builder()
			.name("Balenciaga Shoes")
			.brandName(Brand.BALENCIAGA)
			.year(2022)
			.price(1000.54));

			repository.save(Item.builder()
			.name("Adidas Joggers")
			.brandName(Brand.ADIDAS)
			.year(2022)
			.price(2200));
		};
	}

}

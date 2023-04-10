package com.cpan228.ClothingStore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cpan228.ClothingStore.model.Item;
import com.cpan228.ClothingStore.model.User;
import com.cpan228.ClothingStore.model.Item.Brand;
import com.cpan228.ClothingStore.repository.ItemRepository;
import com.cpan228.ClothingStore.repository.UserRepository;

@SpringBootApplication
public class ClothingStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClothingStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner storeLoader(ItemRepository repository, UserRepository users, PasswordEncoder encoder) {
		return args -> {
			users.save(new User(null, "User","One","user", encoder.encode("user"), "ROLE_USER"));
			users.save(new User(null, "Admin","One","admin", encoder.encode("admin"), "ROLE_USER,ROLE_ADMIN"));

			repository.save(Item.builder()
					.itemName("Balenciaga Shoes")
					.brandName(Brand.BALENCIAGA.getTitle())
					.itemYear(2022)
					.price(1000.54).build());

			repository.save(Item.builder()
					.itemName("Adidas Joggers")
					.brandName(Brand.ADIDAS.getTitle())
					.itemYear(2022)
					.price(2200).build());

			repository.save(Item.builder()
					.itemName("Nike Shirt")
					.brandName(Brand.NIKE.getTitle())
					.itemYear(2032)
					.price(2500).build());
		};
	}
}

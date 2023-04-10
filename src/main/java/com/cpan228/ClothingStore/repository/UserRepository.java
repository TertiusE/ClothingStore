package com.cpan228.ClothingStore.repository;

import org.springframework.data.repository.CrudRepository;

import com.cpan228.ClothingStore.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

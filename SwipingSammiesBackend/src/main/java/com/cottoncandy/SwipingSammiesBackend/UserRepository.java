package com.cottoncandy.SwipingSammiesBackend;

import org.springframework.data.repository.CrudRepository;

import com.cottoncandy.SwipingSammiesBackend.models.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
    
}
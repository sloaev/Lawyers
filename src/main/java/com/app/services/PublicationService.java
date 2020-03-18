package com.app.services;
import com.app.entities.Publication;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PublicationService extends CrudRepository<Publication, Integer> {}

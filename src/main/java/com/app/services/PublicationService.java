package com.app.services;
import com.app.entities.Publication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublicationService extends CrudRepository<Publication, Integer> {
    Publication getById(Integer id);
    List <Publication> findAllBy();
}

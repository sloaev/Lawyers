package com.app.services;
import com.app.entities.Publication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Service for working with publications db entity
 */
public interface PublicationService extends CrudRepository<Publication, Integer> {

    /**
     * @param id id of publication
     * @return Publication instance with current id
     */
    Publication getById(Integer id);

    /**
     * @return list of all Publication instances from db
     */
    List <Publication> findAllBy();

}

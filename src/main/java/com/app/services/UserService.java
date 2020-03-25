package com.app.services;

import com.app.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserService extends CrudRepository <User, Integer> {

    User getById (Integer id);
}

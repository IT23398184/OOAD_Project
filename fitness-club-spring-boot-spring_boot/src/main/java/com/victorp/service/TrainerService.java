package com.victorp.service;

import com.victorp.model.Trainer;
import com.victorp.model.User;

import java.util.List;


public interface TrainerService {
    List<Trainer> findTrainerByKeyword(String keyword) throws Exception;

    void delete(Long id) throws Exception;

    Trainer getByUsername(String username) throws Exception;

    List<Trainer> getAll() throws Exception;

	void update(User user);

}

package com.victorp.service.impl;

import com.victorp.model.Trainer;
import com.victorp.model.User;
import com.victorp.repository.TrainerRepository;
import com.victorp.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {

    private TrainerRepository trainerRepository;

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public List<Trainer> findTrainerByKeyword(String keyword) throws Exception {
        return trainerRepository.findTrainerByKeyword(keyword);
    }

    @Override
    public void delete(Long id) throws Exception {
        trainerRepository.deleteById(id);
    }

    @Override
    public Trainer getByUsername(String username) throws Exception {
        return trainerRepository.findByUsername(username);
    }

    @Override
    public List<Trainer> getAll() throws Exception {
        return trainerRepository.findAll();
    }

	@Override
	public void update(User user) {

        Optional<Trainer> optionalTrainer = trainerRepository.findById(user.getId());

        if (optionalTrainer.isPresent()) {
            Trainer existingTrainer = optionalTrainer.get();


            existingTrainer.setUsername(user.getUsername());


            try {
                trainerRepository.save(existingTrainer);
            } catch (Exception e) {
                throw new RuntimeException("Error updating Trainer: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("Trainer not found with ID: " + user.getId());
        }
    }
		
	}


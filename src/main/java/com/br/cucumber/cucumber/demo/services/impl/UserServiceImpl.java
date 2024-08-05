package com.br.cucumber.cucumber.demo.services.impl;

import com.br.cucumber.cucumber.demo.entities.User;
import com.br.cucumber.cucumber.demo.repositories.UserRepository;
import com.br.cucumber.cucumber.demo.services.UserService;
import exceptions.EmailAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public User save(final User user) {
        if (repository.findByEmail(user.getEmail()).isPresent())
            throw new EmailAlreadyExistsException("Email already exists: " + user.getEmail());
        return repository.save(user);
    }

    @Override
    public User find(final Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("user not found"));
    }
}

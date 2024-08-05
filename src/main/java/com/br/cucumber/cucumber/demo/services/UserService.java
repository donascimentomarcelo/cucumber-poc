package com.br.cucumber.cucumber.demo.services;

import com.br.cucumber.cucumber.demo.entities.User;

public interface UserService {
    User save(final User user);
    User find(final Long id) throws Exception;
}

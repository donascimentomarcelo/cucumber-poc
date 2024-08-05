package com.br.cucumber.cucumber.demo.step;

import com.br.cucumber.cucumber.demo.entities.User;
import com.br.cucumber.cucumber.demo.services.UserService;
import exceptions.EmailAlreadyExistsException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableJpaRepositories
public class StepDefinition {

    private final UserService userService;
    private User user;
    private User persistedUser;
    private Exception exception;

    public StepDefinition(final UserService userService) {
        this.userService = userService;
    }

    @When("the user should be named {string}, email {string} and aged {int}")
    public void set_user_name_email_age(final String name, final String email, final Integer age) throws Throwable {
        user = new User(name, email, age);
        persistedUser = userService.save(user);
    }

    @Then("the user should be persisted on the database")
    public void the_user_should_be_persisted_on_the_database() throws Throwable {
        Assert.assertNotNull(persistedUser);
    }

    @And("the persisted user name {string} and age {int}")
    public void persisted_user_name_and_age(final String name, final Integer age) throws Throwable {
        Assert.assertNotNull(persistedUser);
        Assert.assertEquals(persistedUser.getName(), name);
        Assert.assertEquals(persistedUser.getAge(), age);
    }

    @And("the user should be retrieved by id")
    public void the_user_should_be_retrieved_by_id() throws Throwable {
        final User userFromDB = userService.find(persistedUser.getId());
        Assert.assertNotNull(userFromDB);
    }

    @When("the user with duplicated email has been sent to repository")
    public void the_user_with_duplicated_email_has_been_sent_to_repository() throws Throwable {
        persistedUser.setId(null);
    }

    @Then("the user should not be persisted on the database")
    public void the_user_should_not_be_persisted_on_the_database() throws Throwable {
        exception = Assert.assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.save(persistedUser);
        });
    }

    @And("the exception message should be {string}")
    public void the_exception_message_should_be(final String message) throws Throwable {
        Assertions.assertEquals(message, exception.getMessage());
    }
}


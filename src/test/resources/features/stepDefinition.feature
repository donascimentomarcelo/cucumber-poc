Feature: the user can be persisted on the database

  Scenario: User created successfully
    When the user should be named 'marcelo', email 'marcelo@email.com' and aged 20
    Then the user should be persisted on the database
    And the persisted user name 'marcelo' and age 20
    And the user should be retrieved by id

  Scenario: Prevent duplicate email registration
    When the user should be named 'duplicated', email 'duplicated@email.com' and aged 18
    Then the user should be persisted on the database
    And the persisted user name 'duplicated' and age 18
    When the user with duplicated email has been sent to repository
    Then the user should not be persisted on the database
    And the exception message should be 'Email already exists: duplicated@email.com'

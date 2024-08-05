package com.br.cucumber.cucumber.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable = false, unique = true)
    private Long id;
    @Column(name="NAME", nullable = false)
    private String name;
    @Column(name="EMAIL", nullable = false, unique = true)
    private String email;
    @Column(name="AGE", nullable = false)
    private Integer age;

    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}

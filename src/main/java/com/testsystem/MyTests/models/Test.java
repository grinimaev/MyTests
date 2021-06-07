package com.testsystem.MyTests.models;


import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.List;

@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @OneToMany
    @OrderBy("id ASC")
    private List<Question> question;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

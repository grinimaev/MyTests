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
    private boolean publish;
    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> question;

    public Test(User user,String name, String description) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.publish=false;
    }
    public Test(){}

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void addQuest(Question question){
        this.question.add(question);
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }
}
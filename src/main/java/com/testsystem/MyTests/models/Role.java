package com.testsystem.MyTests.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {
    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "role")
    private Set<User> users;

    public Role(){
        id= Long.valueOf("1");
        name="ROLE_USER";
    }
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

    @Override
    public String getAuthority() {
        return getName();
    }
}

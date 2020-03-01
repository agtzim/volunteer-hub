package com.agtzim.volunteerhub.model;

import com.agtzim.volunteerhub.util.EventSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String country;

    @ManyToMany(mappedBy = "participants")
    @JsonSerialize(using = EventSerializer.class)
    private Set<Event> events = new HashSet<>();

    private boolean active = true;

    private String role;

    public User (String firstName, String lastName, String email, String password, String country, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.country = country;
        this.role = role;
    }

    public void addEvent(Event event) {
        events.add(event);
    }
}

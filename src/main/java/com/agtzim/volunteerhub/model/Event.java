package com.agtzim.volunteerhub.model;

import com.agtzim.volunteerhub.util.OwnerSerializer;
import com.agtzim.volunteerhub.util.ParticipantsSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventId;

    private String title;

    @Lob
    private String body;

    private String location;

    private LocalDate date;

    @ManyToOne
    @JsonSerialize(using = OwnerSerializer.class)
    private User owner;

    @ManyToMany
    @JsonSerialize(using = ParticipantsSerializer.class)
    private Set<User> participants = new HashSet<>();

    public Event(String title, String body, String location, LocalDate date) {
        this.title = title;
        this.body = body;
        this.location = location;
        this.date = date;
    }

    public void addParticipant(User participant) {
        participants.add(participant);
    }

}

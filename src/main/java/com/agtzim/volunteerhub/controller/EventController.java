package com.agtzim.volunteerhub.controller;

import com.agtzim.volunteerhub.model.Event;
import com.agtzim.volunteerhub.model.User;
import com.agtzim.volunteerhub.repository.EventRepository;
import com.agtzim.volunteerhub.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/events")
public class EventController {

    private UserRepository userRepository;
    private EventRepository eventRepository;

    public EventController(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/all")
    public List<Event> availableEvents() {
    	return eventRepository.findAll();
    }

    @GetMapping("/available")
    public List<Event> getCrazyEvents(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        return eventRepository.findAllByParticipantsNotContainingAndDateAfter(user, LocalDate.now());
    }

    @GetMapping("/completed")
    public List<Event> getCompletedEvents(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        return eventRepository.findAllByParticipantsContainingAndDateBefore(user, LocalDate.now());
    }

    @GetMapping("/upcoming")
    public List<Event> getUpcomingEvents(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        return eventRepository.findAllByParticipantsContainingAndDateAfter(user, LocalDate.now());
    }

    @CrossOrigin
    @GetMapping("{id}")
    public void registerToEvent(Authentication authentication, @PathVariable long id) {
        String name = authentication.getName();
        User user = userRepository.findByEmail(name);
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            event.addParticipant(user);
            user.addEvent(event);
            eventRepository.save(event);
            userRepository.save(user);
        }
    }
}

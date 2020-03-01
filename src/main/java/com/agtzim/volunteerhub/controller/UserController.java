package com.agtzim.volunteerhub.controller;

import com.agtzim.volunteerhub.model.Event;
import com.agtzim.volunteerhub.model.ProfileDTO;
import com.agtzim.volunteerhub.model.User;
import com.agtzim.volunteerhub.repository.EventRepository;
import com.agtzim.volunteerhub.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository userRepository;
    EventRepository eventRepository;

    public UserController(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping()
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @GetMapping("current")
    public ProfileDTO getUserProfile(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        return new ProfileDTO(user);
    }

    @GetMapping("current/events")
    public Set<Event> joinedEvents(Authentication authentication){
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        return user.getEvents();
    }
}

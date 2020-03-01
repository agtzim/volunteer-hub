package com.agtzim.volunteerhub.repository;

import com.agtzim.volunteerhub.model.Event;
import com.agtzim.volunteerhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByParticipantsNotContainingAndDateAfter(User user, LocalDate date);
    List<Event> findAllByParticipantsContainingAndDateBefore(User user, LocalDate date);
    List<Event> findAllByParticipantsContainingAndDateAfter(User user, LocalDate date);
}

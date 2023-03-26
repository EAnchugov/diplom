package ru.practicum.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.events.model.Event;
import ru.practicum.user.model.User;

import java.util.List;

public interface EventsRepository extends JpaRepository<Event,Integer> {
    List<Event> getAllByInitiator(User user);

    List<Event> findAllByInitiatorId(Integer id);

    List<Event> findAllByIdAndInitiator(Integer id, User user);
}

package ru.practicum.mainserver.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainserver.events.model.Events;
import ru.practicum.mainserver.user.dto.User;

import java.util.List;

public interface EventsRepository extends JpaRepository<Events,Integer> {
    List<Events> getAllByInitiator(User user);

    List<Events> findAllByIdAndInitiator(Integer id, User user);
}

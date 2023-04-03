package ru.practicum.events.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.categories.model.Category;
import ru.practicum.events.model.Event;
import ru.practicum.user.model.User;
import ru.practicum.variables.State;

import java.time.LocalDateTime;
import java.util.List;

public interface EventsRepository extends JpaRepository<Event,Integer> {
    List<Event> getAllByInitiator(User user);

    List<Event> findAllByInitiatorId(Integer id);

    List<Event> findAllByEventDateIsBeforeAndEventDateIsAfterAndInitiatorInAndStateInAndCategoryIn(
            LocalDateTime start,
            LocalDateTime end,
            List<User> initiators,
            List<State> states,
            List<Category> categories,
            Pageable pageable);

    List<Event> findAllByIdAndInitiator(Integer id, User user);

    List<Event> findAllByCategoryId(Integer id);

    List<Event> findAllByInitiatorAndStateAndCategoryAndEventDateIsBeforeAndEventDateIsAfter(
            User initiator, State state, Category category, LocalDateTime start, LocalDateTime end, Pageable pageable);
}

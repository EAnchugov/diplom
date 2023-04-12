package ru.practicum.events.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.events.model.Event;
import ru.practicum.user.model.User;
import ru.practicum.variables.State;

import java.time.LocalDateTime;
import java.util.List;

public interface EventsRepository extends JpaRepository<Event,Integer> {
    List<Event> findAllByInitiatorId(Integer id, Pageable pageable);

    List<Event> findAllByIdAndInitiator(Integer id, User user);

    List<Event> findAllByCategoryId(Integer id);

    @Query("SELECT e from Event e " +
            "where e.initiator.id = :users and " +
            "e.state = :state and " +
            "e.category.id = :categories and " +
            "e.publishedOn between :start and :end")
    List<Event> testMethod(Integer users, State state, Integer categories, LocalDateTime start, LocalDateTime end, Pageable pageable);

    @Query("SELECT e from Event e " +
            "where e.initiator.id = :users and " +
            "e.category.id = :categories and " +
            "e.publishedOn between :start and :end")
    List<Event> testMethod2(Integer users, Integer categories, LocalDateTime start, LocalDateTime end, Pageable pageable);

    @Query("SELECT e from Event e " +
            "where e.category.id = :category and " +
            "e.paid = :paid and " +
            "e.publishedOn between :start and :end and " +
            "e.state = :state and " +
            "(e.annotation like %:text% or e.description like %:text%)")
    List<Event> findAllWithFilter(String text, Integer category, Boolean paid, LocalDateTime start,
                                  LocalDateTime end, State state, Pageable pageable);
}

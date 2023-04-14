package ru.practicum.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.variables.GlobalVariables;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponse handle(final ItemNotAvailableException e) {
        log.error("Ошибка ItemNotAvailableException " + e.getMessage());
        return new ErrorResponse(
                "NOT_FOUND",
                "The required object was not found.",
                e.getMessage(),
                LocalDateTime.now().format(GlobalVariables.FORMAT));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    private ErrorResponse handle(final StateException e) {
        log.error("Ошибка StateException " + e.getMessage());
        return new ErrorResponse(
                "CONFLICT",
                "This event has wrong status.",
                e.getMessage(),
                LocalDateTime.now().format(GlobalVariables.FORMAT));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    private ErrorResponse handle(final TimeException e) {
        log.error("Ошибка TimeException " + e.getMessage());
        return new ErrorResponse(
                "CONFLICT",
                "This event has wrong time.",
                e.getMessage(),
                LocalDateTime.now().format(GlobalVariables.FORMAT));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    private ErrorResponse handle(final WrongParameterException e) {
        log.error("Ошибка WrongParameterException " + e.getMessage());
        return new ErrorResponse(
                "CONFLICT",
                "WrongParameter",
                e.getMessage(),
                LocalDateTime.now().format(GlobalVariables.FORMAT));
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    private ErrorResponse handle(final Exception e) {
//        log.error("Ошибка Exception " + e.getMessage());
//        return new ErrorResponse(
//                "INTERNAL_SERVER_ERROR",
//                "Exception",
//                e.getMessage(),
//                LocalDateTime.now().format(GlobalVariables.FORMAT));
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    private ErrorResponse handle(final MethodArgumentNotValidException e) {
//        log.error("Ошибка WrongParameterException " + e.getMessage());
//        return new ErrorResponse(
//                "NOT_FOUND",
//                "Невалидный аргумент",
//                e.getMessage(),
//                LocalDateTime.now().format(GlobalVariables.FORMAT));
//    }

}

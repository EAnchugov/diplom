package ru.practicum.mainserver.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserShortDto {
    @Email
    @NotBlank
    @Size(min = 1, max = 50, message = "Email не может быть длиннее 50 символов")
    private String email;
    @NotBlank(message = "Имя не может быть пустым или состоять из пробелов")
    @Size(min = 1, max = 50, message = "Имя не может быть длиннее 50 символов")
    private String name;
}
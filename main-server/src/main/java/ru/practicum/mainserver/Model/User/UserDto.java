package ru.practicum.mainserver.Model.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class UserDto {
    @Email
    @NotBlank
    @Size(min = 1, max = 50, message = "Email не может быть длиннее 50 символов")
    private String email;
    private Integer id;
    @NotBlank(message = "Имя не может быть пустым или состоять из пробелов")
    @Size(min = 1, max = 50, message = "Имя не может быть длиннее 50 символов")
    private String name;
}

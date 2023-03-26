package ru.practicum.request.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewUserRequest {
    @Email
    private String email;
    @NotBlank
    private String name;
}

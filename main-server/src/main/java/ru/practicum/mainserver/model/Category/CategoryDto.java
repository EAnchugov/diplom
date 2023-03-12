package ru.practicum.mainserver.model.Category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
public class CategoryDto {
    private Integer id;
    @NotBlank
    private String name;
}

package ru.practicum.mainserver.Model.Category;

import javax.validation.constraints.*;

public class CategoryDto {
    Integer id;
    @NotBlank
    String name;
}

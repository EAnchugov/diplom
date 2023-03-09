package ru.practicum.mainserver.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.Model.Category.CategoryDto;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/admin/categories")
public class AdminCategoriesController {
    @PostMapping
    public CategoryDto AddCategory (@Valid @RequestBody CategoryDto categoryDto){
        return null;
    }
    @DeleteMapping("/catId")
    //Обратите внимание: с категорией не должно быть связано ни одного события.
    public void DeleteCategory(@Positive @PathVariable Integer catId){
    }

    @PatchMapping("/catId")
    public CategoryDto patchCategory (@Positive @PathVariable CategoryDto categoryDto){
        return null;
    }
}

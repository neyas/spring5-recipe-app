package com.neyas.spring5recipeapp.services;

import com.neyas.spring5recipeapp.commands.RecipeCommand;
import com.neyas.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipies();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}

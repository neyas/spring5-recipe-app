package com.neyas.spring5recipeapp.services;

import com.neyas.spring5recipeapp.commands.RecipeCommand;
import com.neyas.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.neyas.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.neyas.spring5recipeapp.domain.Recipe;
import com.neyas.spring5recipeapp.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
// @DataJpaTest This will fail because it won't get the spring context
public class RecipeServiceIT {
    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void saveOfDescription(){
        // given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        // then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());

    }
}

package com.neyas.spring5recipeapp.services;

import com.neyas.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.neyas.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.neyas.spring5recipeapp.domain.Recipe;
import com.neyas.spring5recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    RecipeServiceImpl   recipeService;

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipesByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Optional<Recipe> recipeReturned = recipeRepository.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();

    }

    @Test
    public void getRecipiesTest() {

        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);

        when(recipeService.getRecipies()).thenReturn(recipeData);

        Set<Recipe> recipeSet = recipeService.getRecipies();

        assertEquals(recipeSet.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }
}
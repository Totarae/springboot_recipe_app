package pckg.su.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pckg.su.commands.RecipeCommand;
import pckg.su.converters.RecipeCommandToRecipe;
import pckg.su.converters.RecipeToRecipeCommand;
import pckg.su.domains.Recipe;
import pckg.su.exceptions.NotFoundException;
import pckg.su.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository rcpR;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository rcpR, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.rcpR = rcpR;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I am alive!");
        Set<Recipe> recipeSet = new HashSet<>();
        rcpR.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long l){
        Optional<Recipe> recipeOptional = rcpR.findById(l);

        if (!recipeOptional.isPresent()) {
            //throw new RuntimeException("Recipe Not Found!");
            //custom exception handler added
            throw new NotFoundException("Recipe not found! For id: "+l.toString());
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachRecipe = recipeCommandToRecipe.convert(command);
        Recipe savedRecipe = rcpR.save(detachRecipe);
        log.debug("Saved Recipe with ID: - "+savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public RecipeCommand findCommandById(Long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }

    @Override
    public void deleteById(Long l) {
        rcpR.deleteById(l);
    }
}

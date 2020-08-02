package pckg.su.service;

import pckg.su.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    public void deleteById(Long recipeId, Long ingredientId);
}

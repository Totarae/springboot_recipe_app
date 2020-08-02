package pckg.su.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pckg.su.commands.IngredientCommand;
import pckg.su.commands.RecipeCommand;
import pckg.su.commands.UnitOfMeasureCommand;
import pckg.su.service.IngredientService;
import pckg.su.service.RecipeService;
import pckg.su.service.UnitOfMeasureService;

/**
 * Created by Admin on 26.03.2019.
 */
@Slf4j
@Controller
public class IngredientController {

    @Autowired
    private final RecipeService rcpS;
    @Autowired
    private final IngredientService ingS;
    @Autowired
    private final UnitOfMeasureService uomS;

    public IngredientController(RecipeService rcpS, IngredientService ingS, UnitOfMeasureService uomS) {
        this.rcpS = rcpS;
        this.ingS = ingS;
        this.uomS = uomS;
    }

    @GetMapping
    @RequestMapping({"/ingredients/{recipeId}"})
    public String getIndexPage(@PathVariable String recipeId, Model model) {

        log.debug("Retrieving ingredient's list for recipe: " + recipeId);

        model.addAttribute("recipe", rcpS.findCommandById(Long.valueOf(recipeId)));
        return "ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){

        model.addAttribute("ingredient", ingS.findByRecipeIdAndId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        return "ingredient/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId){

        log.debug("Deleting recipe Id = "+ recipeId);

        ingS.deleteById(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        return "redirect:/ingredients/"+ recipeId;
    }

    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String ingredientId, Model model){

        model.addAttribute("ingredient", ingS.findByRecipeIdAndId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        model.addAttribute("uomList", uomS.listAllUoms());
        return "ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        System.out.println("saveOrUpdate intiated");
        IngredientCommand savedCommand = ingS.saveIngredientCommand(command);

        System.out.println("saved receipe id:" + savedCommand.getRecipeId());
        System.out.println("saved ingredient id:" + savedCommand.getId());
        //System.out.println("saved UOM id:" + savedCommand.getUnitOfMeasure().getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable String recipeId, Model model){

        //make sure we have a good id value
        RecipeCommand recipeCommand = rcpS.findCommandById(Long.valueOf(recipeId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));

        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        model.addAttribute("uomList",  uomS.listAllUoms());

        return "ingredient/ingredientform";
    }
}

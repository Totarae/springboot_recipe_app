package pckg.su.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pckg.su.commands.RecipeCommand;
import pckg.su.exceptions.NotFoundException;
import pckg.su.service.RecipeService;

import javax.validation.Valid;
import java.util.Locale;

@Slf4j
@Controller
public class RecipeController {

    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";

    @Autowired
    private final RecipeService rcpS;

    public RecipeController(RecipeService rcpS) {
        this.rcpS = rcpS;
    }

    @GetMapping
    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", rcpS.findById(new Long(id)));

        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("recipe/update/{id}")
    public String updateRecipe(@PathVariable String id, Model model, Locale locale){
        model.addAttribute("recipe",rcpS.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return RECIPE_RECIPEFORM_URL;
        }

        RecipeCommand savedCommand = rcpS.saveRecipeCommand(command);
        return "redirect:/recipe/show/" + savedCommand.getId();
    }

    @GetMapping
    @RequestMapping ("recipe/delete/{id}")
    public String deleteRecipe(@PathVariable String id){
        log.debug("Deleting id: "+ id);
        rcpS.deleteById(Long.valueOf(id));
        log.debug("deletion complete...");
        return "redirect:/";
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        //add exception to model to view error message
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}

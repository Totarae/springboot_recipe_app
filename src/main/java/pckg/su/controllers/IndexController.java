package pckg.su.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pckg.su.service.RecipeService;

/**
 * Created by Admin on 26.03.2019.
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    private final RecipeService rcpS;

    public IndexController(RecipeService rcpS) {
        this.rcpS = rcpS;
    }

    /*private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository measureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository measureRepository) {
        this.categoryRepository = categoryRepository;
        this.measureRepository = measureRepository;
    }*/

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model) {

        log.debug("Index page");
        /*Optional<Category> categoryOptional = categoryRepository.findByDescription("american");

        Optional<UnitOfMeasure> unitOfMeasureOptional = measureRepository.findByUom("teaspoon");

        System.out.println("Cat id is: "+ categoryOptional.get().getId());
        System.out.println("UOM id is: "+ unitOfMeasureOptional.get().getId());*/
        model.addAttribute("recipes", rcpS.getRecipes());
        return "index";
    }
}

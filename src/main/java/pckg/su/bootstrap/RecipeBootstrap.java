package pckg.su.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pckg.su.domains.*;
import pckg.su.enumerations.Difficulty;
import pckg.su.repositories.CategoryRepository;
import pckg.su.repositories.RecipeRepository;
import pckg.su.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private RecipeRepository recipeRepo;
    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepo;

    public RecipeBootstrap(CategoryRepository categoryRepo, RecipeRepository recipeRepo, UnitOfMeasureRepository unitOfMeasureRepo) {
        this.categoryRepo = categoryRepo;
        this.recipeRepo = recipeRepo;
        this.unitOfMeasureRepo = unitOfMeasureRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepo.saveAll(getRecipes());
        log.debug("Bootstrap data!");
    }

    private List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<>();

        //get Optional UOMs
        Optional<UnitOfMeasure> teaSpoonOptional = unitOfMeasureRepo.findByUom("teaspoon");
        if(!teaSpoonOptional.isPresent()){
            throw new RuntimeException("expected UOM not found");
        }

        Optional<UnitOfMeasure> tableSpoonOptional = unitOfMeasureRepo.findByUom("tablespoon");
        if(!tableSpoonOptional.isPresent()){
            throw new RuntimeException("expected UOM not found");
        }

        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepo.findByUom("cup");
        if(!cupOptional.isPresent()){
            throw new RuntimeException("expected UOM not found");
        }

        Optional<UnitOfMeasure> pinchOptional = unitOfMeasureRepo.findByUom("pinch");
        if(!pinchOptional.isPresent()){
            throw new RuntimeException("expected UOM not found");
        }

        Optional<UnitOfMeasure> ounceOptional = unitOfMeasureRepo.findByUom("ounce");
        if(!ounceOptional.isPresent()){
            throw new RuntimeException("expected UOM not found");
        }

        Optional<UnitOfMeasure> pintOptional = unitOfMeasureRepo.findByUom("pint");
        if(!ounceOptional.isPresent()){
            throw new RuntimeException("expected UOM not found");
        }

        Optional<UnitOfMeasure> numberOptional = unitOfMeasureRepo.findByUom("number");
        if(!ounceOptional.isPresent()){
            throw new RuntimeException("expected UOM not found");
        }

        //get Actual UOMs
        UnitOfMeasure teaSpoonUom = teaSpoonOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonOptional.get();
        UnitOfMeasure cupUom = cupOptional.get();
        UnitOfMeasure pinchUom = pinchOptional.get();
        UnitOfMeasure ounceUom = ounceOptional.get();
        UnitOfMeasure pintUom = ounceOptional.get();
        UnitOfMeasure numberUom = ounceOptional.get();

        //get Optional Categories
        Optional<Category> americanOptional = categoryRepo.findByDescription("american");
        if(!americanOptional.isPresent()){
            throw new RuntimeException("expected Category not found");
        }

        Optional<Category> mexicanOptional = categoryRepo.findByDescription("mexican");
        if(!mexicanOptional.isPresent()){
            throw new RuntimeException("expected Category not found");
        }

        Optional<Category> indianOptional = categoryRepo.findByDescription("indian");
        if(!indianOptional.isPresent()){
            throw new RuntimeException("expected Category not found");
        }

        Optional<Category> italianOptional = categoryRepo.findByDescription("italian");
        if(!italianOptional.isPresent()){
            throw new RuntimeException("expected Category not found");
        }

        //get Actual Categories
        Category americanCategory = americanOptional.get();
        Category mexicanCategory = mexicanOptional.get();
        Category indianCategory = indianOptional.get();
        Category italianCategory = italianOptional.get();

        Recipe spcyGrldChknTaco = new Recipe();
        spcyGrldChknTaco.setDescription("Spicy grilled chicken tacos! Quick marinade, then grill. " +
                "Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.");
        spcyGrldChknTaco.setPrepTime(20);
        spcyGrldChknTaco.setCookTime(15);
        spcyGrldChknTaco.setServings(5);
        spcyGrldChknTaco.setSource("www.simplyrecipes.com");
        spcyGrldChknTaco.setUrl("http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        String spcyGrldChknTacoDirections = "Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, " +
                "cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose " +
                "paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer " +
                "inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. " +
                "As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for " +
                "a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. " +
                "Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned " +
                "sour cream. Serve with lime wedges.\n";
        spcyGrldChknTaco.setDirection(spcyGrldChknTacoDirections);
        String spcyGrldChknTacoNote = "Any and every kind of leftover can go inside a warm tortilla, " +
                "usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night " +
                "snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and " +
                "sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble " +
                "the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep " +
                "reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find " +
                "ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: " +
                "avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. " +
                "I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some " +
                "in the fridge and I think it adds a nice green crunch to the tacos.\n" ;

        Note note = new Note(spcyGrldChknTacoNote);
        note.setRecipe(spcyGrldChknTaco);
        spcyGrldChknTaco.setNotes(note);

        spcyGrldChknTaco.setDifficulty(Difficulty.MEDIUM);
        spcyGrldChknTaco.getCategories().add(mexicanCategory);
        spcyGrldChknTaco.addIngredient(new Ingredient("ancho chili powder",new BigDecimal(2),tableSpoonUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("dried oregano",new BigDecimal(1),teaSpoonUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("sugar",new BigDecimal(1),teaSpoonUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("dried cumin",new BigDecimal(1),teaSpoonUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("salt",new BigDecimal(1/2),teaSpoonUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("finely chopped garlic clove",new BigDecimal(1),numberUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("finely grated orange zest",new BigDecimal(1),tableSpoonUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("fresh squeezed orange juice",new BigDecimal(3),tableSpoonUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("olive oil",new BigDecimal(2),tableSpoonUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("chicken thighs",new BigDecimal(5),numberUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("corn tortillas",new BigDecimal(8),numberUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("baby arugula",new BigDecimal(3),cupUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("sliced ripe avocados",new BigDecimal(2),numberUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("thinly sliced radishes",new BigDecimal(4),numberUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("halved cherry tomatoes",new BigDecimal(0.5),pintUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("thinly sliced red onions",new BigDecimal(0.25),numberUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("roughly chopped cilantro",new BigDecimal(1),pintUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("sour cream",new BigDecimal(0.5),cupUom));
        spcyGrldChknTaco.addIngredient(new Ingredient("wedges cut lime",new BigDecimal(1),numberUom));

        //Sautéed Zucchini with Dill
        Recipe sautdZuchWdDill = new Recipe();
        sautdZuchWdDill.setDescription("This sautéed zucchini with dill is such a simple and easy side " +
                "dish for summer meals. Six ingredients. Takes 15 minutes. " +
                "Great on its own, or serve with grilled chicken or fish.");
        sautdZuchWdDill.setPrepTime(5);
        sautdZuchWdDill.setCookTime(25);
        sautdZuchWdDill.setServings(7);
        sautdZuchWdDill.setSource("www.simplyrecipes.com");
        sautdZuchWdDill.setUrl("http://www.simplyrecipes.com/recipes/sauteed_zucchini_with_dill/");
        String sautdZuchWdDillDirections = "1 Prepare the zucchini: Cut away the stem from the zucchini and trim the bottom end. Slice the zucchini into 1/8-inch rounds using  a mandolin, food processor, or your very best knife skills." +
                "2 Cook the zucchini in two batches: In a large skillet, heat 2 tablespoons of the olive oil over medium-high heat. Add half the zucchini. Cook without stirring for 4 minutes, or until some of the rounds are golden when you lift them with a spatula.\n" +
                "Sprinkle with 1/4 teaspoon of the salt and 1/8 teaspoon of the pepper. Turn and cook 2 minutes more without disturbing or until more rounds are brown. Do this two more times. Not all the rounds will be browned but a lot of them will be. Transfer to a bowl.\n" +
                "Cook the second batch of zucchini with the remaining 2 tablespoons oil in the same way. Return all the zucchini to the pan." +
                "3 Season the zucchini: Combine the dill and lemon rind on a cutting board and chop them together just to mix them. Sprinkle the hot zucchini with the dill and lemon mixture, and toss well. Taste for seasoning and add more salt and pepper, if you like.\n" +
                "4 Serve hot or warm.";
        sautdZuchWdDill.setDirection(sautdZuchWdDillDirections);
        String sautdZuchWdDillNote = "When the zucchini in your garden is still slender and about 7-inches long – before they get big as baseball bats — the seeds will be small and the flesh will be sweet.\n" +
                "Zucchini at this stage hardly needs any adornment. I like to cut my zucchini into thin coins and toss them in a hot skillet with some oil until they start to turn golden brown. That’s it!" +
                "This simple sauté takes just a few minutes. The zucchini can go to the table on its own, or you can use it as a bed for grilled chicken or fish. Any leftover zucchini is great served with a fried egg for breakfast the next day!\n" +
                "I like to use a mandoline to slice the zucchini into thin, uniform circles, but you can also use a food processor with a slicing blade. If the opening of your food processor is too small for whole zucchini, halve them lengthwise to make half-coins.\n" +
                "I like to add fresh dill and lemon zest to my sauté. They add just the right aromatics to the zucchini.\n" +
                "With only six ingredients and about 15 minutes of your time, you have a beautiful vegetable dish for your summer table.\n";
        Note note2 = new Note(spcyGrldChknTacoNote);
        note2.setRecipe(sautdZuchWdDill);
        sautdZuchWdDill.setNotes(note2);
        sautdZuchWdDill.setDifficulty(Difficulty.EASY);
        sautdZuchWdDill.getCategories().add(americanCategory);
        sautdZuchWdDill.addIngredient(new Ingredient("medium zucchini",new BigDecimal(6),numberUom));
        sautdZuchWdDill.addIngredient(new Ingredient("olive oil",new BigDecimal(4),tableSpoonUom));
        sautdZuchWdDill.addIngredient(new Ingredient("salt",new BigDecimal(0.5),teaSpoonUom));
        sautdZuchWdDill.addIngredient(new Ingredient("ground black pepper",new BigDecimal(0.25),teaSpoonUom));
        sautdZuchWdDill.addIngredient(new Ingredient("chopped fresh dill",new BigDecimal(2),tableSpoonUom));
        sautdZuchWdDill.addIngredient(new Ingredient("finely chopped garlic clove",new BigDecimal(1),numberUom));

        recipes.add(spcyGrldChknTaco);
        recipes.add(sautdZuchWdDill);

        return recipes;
    }
}

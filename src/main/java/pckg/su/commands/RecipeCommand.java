package pckg.su.commands;

import org.hibernate.validator.constraints.NotBlank;
import pckg.su.enumerations.Difficulty;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class RecipeCommand {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String direction;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private NoteCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();

    public RecipeCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public NoteCommand getNotes() {
        return notes;
    }

    public void setNotes(NoteCommand notes) {
        this.notes = notes;
    }

    public Set<CategoryCommand> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryCommand> categories) {
        this.categories = categories;
    }

    public Set<IngredientCommand> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientCommand> ingredients) {
        this.ingredients = ingredients;
    }
}

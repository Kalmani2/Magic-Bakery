package bakery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Layer extends Ingredient implements Comparable<Ingredient>, Serializable{
    private List<Ingredient> recipe;
    private static final long serialVersionUID = 1L;

    public Layer(String name, List<Ingredient> recipe){
        super(name);
        this.recipe = recipe;
    }

    public boolean canBake(List<Ingredient> ingredients){
        return true;
    }

    public String getName(){
        return super.toString();
    }

    public List<Ingredient> getRecipe(){
        return recipe;
    }
    
    public String getRecipeDescription(){
        int length = recipe.size();
        String recipeDiscription = "";
        for (int i = 0; i < length; i++){
            recipeDiscription += recipe.get(i) + ", ";
        }
        return("Recipe: " + recipeDiscription);
    }

    public int hashCode(){
        return 1;
    }
}

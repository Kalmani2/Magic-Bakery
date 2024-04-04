package bakery;
import java.util.ArrayList;

public class Layer extends Ingredient {
    private ArrayList<Ingredient> recipe;

    public Layer(String name, ArrayList<Ingredient> recipe){
        super(name);
        this.recipe = recipe;
    }

    public String getName(){
        return super.toString();
    }

    public ArrayList<Ingredient> getRecipe(){
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
}

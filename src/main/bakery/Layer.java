package bakery;
import java.util.ArrayList;

public class Layer extends Ingredient {
    private ArrayList<Ingredient> recipe;

    public Layer(String name, ArrayList<Ingredient> ingredients){
        super(name);
        recipe = ingredients;
    }

    public ArrayList<Ingredient> getRecipe(){
        return recipe;
    }
    
    public String getRecipeDescription(){
        return("recipe is");
    }
}

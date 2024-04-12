package bakery;
import java.io.Serializable;
import java.util.List;

public class Layer extends Ingredient implements Comparable<Ingredient>, Serializable{
    private List<Ingredient> recipe;
    private static final long serialVersionUID = 1L;

    public Layer(String name, List<Ingredient> recipe){
        super(name);
        this.recipe = recipe;
    }

    public boolean canBake(List<Ingredient> ingredients){
        for (Ingredient ing : ingredients){
            if (recipe.indexOf(ing) == -1){
                return false;
            }
        }
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
        String recipeDescription = "";
        for (int i = 0; i < length; i++){
            if (i == length-1){
                recipeDescription += recipe.get(i);
                break;
            }
            recipeDescription += recipe.get(i) + ", ";
        }
        return(recipeDescription);
    }

    public int hashCode(){
        return 1;
    }
}

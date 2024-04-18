package bakery;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a layer in a bakery item.
 * Each layer consists of a name and a recipe containing a list of ingredients.
 * @author Muhammad Kalmani
 * @version 1.0
 */
public class Layer extends Ingredient implements Comparable<Ingredient>, Serializable{
    private List<Ingredient> recipe;
    private static final long serialVersionUID = 1L;

    /**
     * Creates a layer with the given name and recipe
     *
     * @param name string name for the layer
     * @param recipe ingredients used for this layer
     */
    public Layer(String name, List<Ingredient> recipe){
        super(name);
        this.recipe = recipe;
    }

    /**
     * Takes a list of ingredients and see if the layer can be baked with it
     *
     * @param ingredients list of ingredients to check for bakeable
     * @return boolean value if ingredients can bake layer
     */
    public boolean canBake(List<Ingredient> ingredients){
        
        List<String> newIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredients){
            if (ingredient == Ingredient.HELPFUL_DUCK){
                newIngredients.add("Helpful duck 𓅭");
            }
            else{
                newIngredients.add(ingredient.toString());
            }
        }

        List<String> recipeIngredients = new ArrayList<>();
        for (Ingredient ingredient : recipe){
            recipeIngredients.add(ingredient.toString());
        }

        for (String name : recipeIngredients){
            if (newIngredients.contains(name)){
                newIngredients.remove(name);
            }
            else{
                if (newIngredients.contains("Helpful duck 𓅭")){
                    newIngredients.remove("Helpful duck 𓅭");
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }

    public String getName(){
        return super.toString();
    }

    /**
     * Gets a multi-line String representation of the specified CustomerOrders.
     *
     * @return the recipe for the instantiated layer
     */
    public List<Ingredient> getRecipe(){
        return recipe;
    }
    
    /**
     * Gets a multi-line String representation of the specified CustomerOrders.
     *
     * @return a description of the recipe of the instantiated layer
     */
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

    /**
     * Gets a multi-line String representation of the specified CustomerOrders.
     *
     * @return the hashcode for the layer
     */
    public int hashCode(){
        return 1;
    }
}

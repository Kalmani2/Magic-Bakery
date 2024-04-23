package bakery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer's order and its features
 * @author Muhammad Kalmani
 * @version 1.0
 */
public class CustomerOrder implements Serializable{
    private List<Ingredient> garnish;
    private int level;
    private String name;
    private List<Ingredient> recipe;
    private CustomerOrderStatus status;
    private static final long serialVersionUID = 1L;

    // CustomerOrderStatus enumeration
    public enum CustomerOrderStatus{
        WAITING, FULFILLED, GARNISHED, IMPATIENT, GIVEN_UP
    }

    /**
     * Constructor for the customer's order
     *
     * @param name Name of the customer's ordere
     * @param recipe ingredients used for this order
     * @param garnish list of ingredients to be used as garnish
     * @param level the difficulty level of this order
     * @throws WrongIngredientsException recipe is null
     * @throws WrongIngredientException recipe is empty
     * 
     */
    public CustomerOrder(String name, List<Ingredient> recipe, List<Ingredient> garnish, int level) throws WrongIngredientsException{
        this.garnish = garnish;
        this.level = level;
        this.name = name;
        this.recipe = recipe;
        this.status = CustomerOrderStatus.WAITING;

        if (recipe == null){
            throw new WrongIngredientsException("Recipe is null");
        }
        if (recipe.size() == 0){
            throw new WrongIngredientsException("Recipe is empty");
        }
    }

    /**
     * Function to abandon the customer's order
     */
    public void abandon(){
        this.status = CustomerOrderStatus.GIVEN_UP;
    }

    /**
     * Checks to see if the customer's order can be fulfilled with the given ingredients
     *
     * @param Ingredients ingredients to check for order fulfillment
     * @return boolean value of fulfillment
     */
    public boolean canFulfill(List<Ingredient> Ingredients){
        if (Ingredients.isEmpty()){
            return false;
        }

        List<String> newIngredients = new ArrayList<>();
        for (Ingredient ingredient : Ingredients){
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

    /**
     * Checks to see if the customer's order can be garnished with the given ingredients
     *
     * @param Ingredients ingredients to check for garnish fulfillment
     * @return boolean value of fulfillment
     */
    public boolean canGarnish(List<Ingredient> Ingredients){
        if (Ingredients.isEmpty()){
            return false;
        }

        List<String> newIngredients = new ArrayList<>();
        for (Ingredient ingredient : Ingredients){
            if (ingredient == Ingredient.HELPFUL_DUCK){
                newIngredients.add("Helpful duck 𓅭");
            }
            else{
                newIngredients.add(ingredient.toString());
            }
        }

        List<String> garnishIngredients = new ArrayList<>();
        for (Ingredient ingredient : garnish){
            garnishIngredients.add(ingredient.toString());
        }

        for (String name : garnishIngredients){
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

    /**
     * Fulfills the customer's order with the ingredient and checks for garnish
     *
     * @param ingredients ingredients to check for order fulfillment
     * @param garnish boolean value of if garnish is needed
     * @return a new list of ingredients
     * @throws WrongIngredientsException if wrong ingredients used
     */
    public List<Ingredient> fulfill(List<Ingredient> ingredients, boolean garnish) throws WrongIngredientsException{

        List<Ingredient> newIngredients = new ArrayList<>(ingredients);
        List<Ingredient> returnList = new ArrayList<>();

        if (garnish){
            List<Ingredient> fullIngredients = new ArrayList<>(this.recipe);
            fullIngredients.addAll(this.garnish);

            for (Ingredient name : fullIngredients){
                if (newIngredients.contains(name)){
                    returnList.add(name);
                    newIngredients.remove(name);
                }
                else{
                    if (newIngredients.contains(Ingredient.HELPFUL_DUCK)){
                        returnList.add(Ingredient.HELPFUL_DUCK);
                        newIngredients.remove(Ingredient.HELPFUL_DUCK);
                    }
                    else {
                        throw new WrongIngredientsException("Wrong ingredients");
                    }
                }
            }
            if (this.garnish.size() == 0){
                this.status = CustomerOrderStatus.FULFILLED;
            }
            else{
                this.status = CustomerOrderStatus.GARNISHED;
            }
            return returnList;
        }
        else{
            List<Ingredient> fullIngredients = new ArrayList<>(this.recipe);
            
            for (Ingredient name : fullIngredients){
                if (newIngredients.contains(name)){
                    returnList.add(name);
                    newIngredients.remove(name);
                }
                else{
                    if (newIngredients.contains(Ingredient.HELPFUL_DUCK)){
                        returnList.add(Ingredient.HELPFUL_DUCK);
                        newIngredients.remove(Ingredient.HELPFUL_DUCK);
                    }
                    else {
                        return returnList;
                    }
                }
            }
            this.status = CustomerOrderStatus.FULFILLED;
            return returnList;
        }
    }

    /**
     * Returns the list of ingredients used as garnishes for this order
     *
     * @return list of ingredients used as garnishes
     */
    public List<Ingredient> getGarnish(){
        return garnish;
    }

    /**
     * Returns the description of the garnish used
     *
     * @return string description of the garnishes
     */
    public String getGarnishDescription(){
        List<Ingredient> newGarnish = new ArrayList<>(garnish);
        String garnishDescription = garnish.get(0).toString();
        newGarnish.remove(0);
        for (int i = 1; i < garnish.size(); i++){
            garnishDescription += ", ";
            garnishDescription += garnish.get(i).toString();
        }
        return(garnishDescription);
    }

    /**
     * Returns the level of the customer's order
     * @return integer order level
     */
    public int getLevel(){
        return level;
    }

    /**
     * Returns the recipe for the chosen customer's order
     * @return the recipe for this customer's order
     */
    public List<Ingredient> getRecipe(){
        return recipe;
    }

    /**
     * Returns a formatted description of the recipe for the order
     * @return description of the recipe for the order
     */
    public String getRecipeDescription(){
        int length = recipe.size();
        String recipeDiscription = "";
        for (int i = 0; i < length; i++){
            if (i == length-1){
                recipeDiscription += recipe.get(i);
                break;
            }
            recipeDiscription += recipe.get(i) + ", ";
        }
        return(recipeDiscription);
    }

    /**
     * Sets the status of the customer order as the chosen one
     * @param status the status to set as new
     */
    public void setStatus(CustomerOrderStatus status){
        this.status = status;
    }

    /**
     * Returns the status of the customer's order
     * @return status of the customer's order
     */
    public CustomerOrderStatus getStatus(){
        return status;
    }
    
    /**
     * Returns the string version of the customer's order
     * @return string representation of customer's order
     */
    @Override
    public String toString(){
        return("some recipe");
    }
}

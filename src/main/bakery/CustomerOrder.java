package bakery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public CustomerOrder(String name, List<Ingredient> recipe, List<Ingredient> garnish, int level){
        this.garnish = garnish;
        this.level = level;
        this.name = name;
        this.recipe = recipe;
        this.status = CustomerOrderStatus.WAITING;
    }

    public void abandon(){
        this.status = CustomerOrder.CustomerOrderStatus.GIVEN_UP;
    }

    public boolean canFulfill(List<Ingredient> Ingredients){
        if (Ingredients.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean canGarnish(List<Ingredient> Ingredients){
        return true;
    }

    public List<Ingredient> fulfill(List<Ingredient> ingredients, boolean garnish){
        return null;
    }

    public List<Ingredient> getGarnish(){
        return garnish;
    }

    public String getGarnishDescription(){
        int length = garnish.size();
        String garnishDiscription = "";
        for (int i = 0; i < length; i++){
            if (i == length-1){
                garnishDiscription += recipe.get(i);
                break;
            }
            garnishDiscription += garnish.get(i) + ", ";
        }
        return(garnishDiscription);
    }

    public int getLevel(){
        return level;
    }

    public List<Ingredient> getRecipe(){
        return recipe;
    }

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

    public void setStatus(CustomerOrderStatus status){
        this.status = status;
    }

    public CustomerOrderStatus getStatus(){
        return status;
    }
    
    @Override
    public String toString(){
        return(getRecipeDescription());
    }
}

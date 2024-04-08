package bakery;
import java.util.ArrayList;

public class CustomerOrder {
    private ArrayList<Ingredient> garnish;
    private int level;
    private String name;
    private ArrayList<Ingredient> recipe;
    private CustomerOrderStatus status;

    public enum CustomerOrderStatus{
        WAITING, FULFILLED, GARNISHED, IMPATIENT, GIVEN_UP
    }

    public CustomerOrder(String name, ArrayList<Ingredient> recipe, ArrayList<Ingredient> garnish, int level, CustomerOrderStatus status){
        this.garnish = garnish;
        this.level = level;
        this.name = name;
        this.recipe = recipe;
        this.status = status;
    }
    public void abandon(){
        this.status = CustomerOrder.CustomerOrderStatus.GIVEN_UP;
    }
    public ArrayList<Ingredient> getGarnish(){
        return garnish;
    }
    public String getGarnishDescription(){
        int length = garnish.size();
        String garnishDiscription = "";
        for (int i = 0; i < length; i++){
            garnishDiscription += garnish.get(i) + ", ";
        }
        return("Garnish: " + garnishDiscription);
    }
    public int getLevel(){
        return level;
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
    public void setStatus(CustomerOrderStatus status){
        this.status = status;
    }
    public CustomerOrderStatus getStatus(){
        return status;
    }
    @Override
    public String toString(){
        return("Customer order class");
    }
}

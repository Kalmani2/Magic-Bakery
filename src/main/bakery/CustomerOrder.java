package bakery;
import java.util.ArrayList;

public class CustomerOrder {
    private ArrayList<Ingredient> garnish;
    private int level;
    private String name;
    private ArrayList<Ingredient> recipe;

    public CustomerOrder(String name, ArrayList<Ingredient> recipe, ArrayList<Ingredient> garnish, int level){
        this.garnish = garnish;
        this.level = level;
        this.name = name;
        this.recipe = recipe;
    }

    public ArrayList<Ingredient> getGarnish(){
        return garnish;
    }
    public String getGarnishDescription(){
        return("Hello");
    }
    public int getLevel(){
        return level;
    }
    public ArrayList<Ingredient> getRecipe(){
        return recipe;
    }
    public String getRecipeDescription(){
        return("Placeholder");
    }
    @Override
    public String toString(){
        return("Placeholder");
    }
}

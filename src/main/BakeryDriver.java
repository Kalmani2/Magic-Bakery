import java.util.ArrayList;
import bakery.Ingredient;

public class BakeryDriver {

    public BakeryDriver() {
    }

    public static void main(String[] args)  {
        Ingredient Flour = new Ingredient("Flour");
        Ingredient Sugar = new Ingredient("Sugar");
        Ingredient Eggs = new Ingredient("Eggs");
        Ingredient Milk = new Ingredient("Milk");
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        ingredients.add(Flour);
        ingredients.add(Sugar);
        ingredients.add(Eggs);
        ingredients.add(Milk);
    }

}
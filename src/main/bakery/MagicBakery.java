package bakery;
import java.io.IOException;
import java.util.ArrayList;
import util.CardUtils;

public class MagicBakery {
    public MagicBakery(long seed, String ingredientDeckFile, String layerDeckFile){
        CardUtils cardUtils = new CardUtils();
        try {
            ArrayList<Ingredient> ingredients = cardUtils.readIngredientFile(ingredientDeckFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        Ingredient flour = new Ingredient("Flour");
        Ingredient sugar = new Ingredient("Sugar");
        Ingredient eggs = new Ingredient("Eggs");
        Ingredient milk = new Ingredient("Milk");

        System.out.print(flour.toString());
        System.out.print(sugar.toString());
        System.out.print(eggs.toString());
        System.out.print(milk.toString());

        ingredients.add(flour);
        ingredients.add(eggs);
        ingredients.add(milk);
        ingredients.add(sugar);

        Layer layer = new Layer("Layer 1", ingredients);

        System.out.println(layer.getRecipeDescription());

        CustomerOrder order = new CustomerOrder(null, ingredients, ingredients, 0);
    }
}

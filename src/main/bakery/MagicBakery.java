package bakery;
import java.io.IOException;
import java.util.ArrayList;
import util.CardUtils;

public class MagicBakery {
    public MagicBakery(long seed, String ingredientDeckFile, String layerDeckFile){
        ArrayList<Ingredient> ingredientDeck = new ArrayList<Ingredient>();
        ingredientDeck = CardUtils.readIngredientFile(ingredientDeckFile);

    }

    public void startGame(ArrayList<String> playerNames, String customerDeckFile){
        for (String playerName : playerNames) {
            System.out.println(playerName);
        }
    }

    public static void main(String[] args){
        Ingredient flour = new Ingredient("Flour");
        Ingredient sugar = new Ingredient("Sugar");
        Ingredient eggs = new Ingredient("Eggs");
        Ingredient milk = new Ingredient("Milk");
        Ingredient chocolate = new Ingredient("Chocolate");

        System.out.println(flour);
        System.out.println(sugar);
        System.out.println(eggs);
        System.out.println(milk);

        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(flour);
        ingredients.add(sugar);
        ingredients.add(eggs);
        ingredients.add(milk);

        ArrayList<Ingredient> garnishes = new ArrayList<Ingredient>();
        garnishes.add(chocolate);

        Layer layer = new Layer("Layer1", ingredients);
        System.out.println(layer.getRecipeDescription());

        CustomerOrder order1 = new CustomerOrder("Bob", layer.getRecipe(), garnishes, 1, CustomerOrder.CustomerOrderStatus.WAITING);
    }
}

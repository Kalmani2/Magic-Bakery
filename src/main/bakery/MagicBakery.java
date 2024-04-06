package bakery;
import java.util.ArrayList;
import java.util.Collection;
import util.CardUtils;

public class MagicBakery {
    public Collection<Player> players;
    private int[] playerTurnList;
    private int playerTurn;

    public MagicBakery(long seed, String ingredientDeckFile, String layerDeckFile){
        ArrayList<Ingredient> ingredientDeck = new ArrayList<Ingredient>();
        ingredientDeck = CardUtils.readIngredientFile(ingredientDeckFile);
        players = new ArrayList<>();
        playerTurnList = new int[players.size()];
        playerTurn = 1;
    }

    public void startGame(ArrayList<String> playerNames, String customerDeckFile){
        for (String newName : playerNames){
            Player newPlayer = new Player(newName);
            players.add(newPlayer);
        }
        System.out.println(players);
    }

    // public Player getCurrentPlayer(){
    //     Player currentPlayer = players.get(playerTurn);
    // }

    public Collection<Player> getPlayers(){
        return players;
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
        ingredients.add(chocolate);
        ingredients.add(milk);

        ArrayList<Ingredient> garnishes = new ArrayList<Ingredient>();
        garnishes.add(chocolate);

        Layer layer = new Layer("Layer1", ingredients);
        System.out.println(layer.getRecipeDescription());

        CustomerOrder order1 = new CustomerOrder("Bob", layer.getRecipe(), garnishes, 1, CustomerOrder.CustomerOrderStatus.WAITING);

        Player joe = new Player("Joe");
        joe.addToHand(ingredients);
        System.out.println(joe.getHand());
        System.out.println(joe.getHandStr());
    }
}

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import bakery.CustomerOrder;
import bakery.Ingredient;
import bakery.Layer;
import bakery.MagicBakery;
import util.CardUtils;
import util.ConsoleUtils;
import bakery.Player;

public class BakeryDriver {

    private ConsoleUtils console;

    public static void main(String[] args)  {
        ConsoleUtils util2 = new ConsoleUtils();
        // MagicBakery magicBakery = new MagicBakery(1, "../../../io/ingredients.csv", "../../../io/layers.csv");
        // System.out.println(CardUtils.readIngredientFile("../../io/ingredients.csv").size());
        // System.out.println(CardUtils.readLayerFile("../../io/layers.csv"));
        // util2.promptForYesNo("Do you want to continue?");
        // ArrayList<String> playerNames = new ArrayList<>();
        // playerNames = util2.promptForNewPlayers("Add another?");
        // magicBakery.startGame(playerNames, null);

        // Set up players hand
        Ingredient flour = new Ingredient("Flour");
        Ingredient sugar = new Ingredient("Sugar");
        Ingredient eggs = new Ingredient("Eggs");
        Ingredient chocolate = new Ingredient("Chocolate");
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(eggs);
        ingredients.add(sugar);
        ingredients.add(flour);
        ingredients.add(sugar);
        ingredients.add(chocolate);

        
        MagicBakery magicBakery = new MagicBakery(1, "io/ingredients.csv", "io/layers.csv");
        List<String> playerNames = util2.promptForNewPlayers("Add another?");
        magicBakery.startGame(playerNames, "io/customers.csv");
        magicBakery.populatePlayerTurnList();
        System.out.println("Player names are"+ playerNames);
        System.out.println("Start game");
        System.out.println("Players are " + magicBakery.getPlayers());
        System.out.println("Actions permitted: " + magicBakery.getActionsPermitted());
        for (int i = 0; i < 20; i++){
            System.out.println("Actions remaining: " + magicBakery.getActionsRemaining());
            Player currentPlayer = magicBakery.getCurrentPlayer();
            currentPlayer.addToHand(ingredients);
            Player passedPlayer = util2.promptForExistingPlayer("Pass a card ", magicBakery);
            magicBakery.passCard(currentPlayer.getHand().get(i), passedPlayer);
            System.out.println("Current players hand "+currentPlayer.toString()+": ");
            System.out.println(currentPlayer.getHandStr());
            System.out.println("Passed players hand " + passedPlayer.toString() + ": ");
            System.out.println(passedPlayer.getHandStr());

        }
    }

}
import java.io.IOException;
import java.util.ArrayList;
import bakery.CustomerOrder;
import bakery.Ingredient;
import bakery.Layer;
import bakery.MagicBakery;
import util.CardUtils;
import util.ConsoleUtils;

public class BakeryDriver {

    public BakeryDriver() {

    }

    public static void main(String[] args)  {
        ConsoleUtils util2 = new ConsoleUtils();
        MagicBakery magicBakery = new MagicBakery(1, "../../io/ingredients.csv", "../../io/layers.csv");
        // System.out.println(CardUtils.readIngredientFile("../../io/ingredients.csv").size());
        // System.out.println(CardUtils.readLayerFile("../../io/layers.csv"));
        // util2.promptForYesNo("Do you want to continue?");
        ArrayList<String> playerNames = new ArrayList<>();
        playerNames = util2.promptForNewPlayers("Add another?");
        magicBakery.startGame(playerNames, null);
    }

}
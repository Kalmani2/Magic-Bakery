import java.io.IOException;
import java.util.ArrayList;

import bakery.CustomerOrder;
import bakery.Ingredient;
import bakery.Layer;
import bakery.MagicBakery;
import util.CardUtils;

public class BakeryDriver {

    // private ConsoleUtils console;

    public BakeryDriver() {

    }

    public static void main(String[] args)  {
        CardUtils util = new CardUtils();
        MagicBakery magicBakery = new MagicBakery(1, "../../io/ingredients.csv", "../../io/layers.csv");
        System.out.println(CardUtils.readIngredientFile("../../io/ingredients.csv").size());
        System.out.println(CardUtils.readLayerFile("../../io/layers.csv"));

    }

}
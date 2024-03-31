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
    }

}
package util;
import java.io.File;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.HashMap;
import bakery.CustomerOrder;
import bakery.Ingredient;
import bakery.MagicBakery;
import bakery.Player;
import bakery.MagicBakery.ActionType;

/**
 * Class comprised of functions used to take user input from the console
 * @author Muhammad Kalmani
 * @version 1.0
 */
public class ConsoleUtils {
    private Console console;

    /**
     * Constructor for the consoleUtils class creates a console object
     *
     */
    public ConsoleUtils(){
        console = System.console();
    }

    /**
     * Reads what the user writes in the console
     *
     * @return String of what the user entered
     */
    public String readLine(){
        return console.readLine();
    }
    /**
     * Reads a line of text from the console, using the specified format string and arguments.
     *
     * @param fmt a format string used to prompt the user
     * @param args arguments referenced by the format string
     * @return a string of text entered by the user
     */
    public String readLine(String fmt, Object... args){
        return console.readLine(fmt, args);
    }

    /**
     * Prompts the user for an action in the game
     * @param prompt a format string used to prompt the user
     * @param bakery the magicBakery object to be used
     * @return an action type as specified in magic bakery
     */
    public ActionType promptForAction(String prompt, MagicBakery bakery){
        return ActionType.BAKE_LAYER;
    }

    /**
     * Prompts the user to choose a customer whos order will be fulfilled
     *
     * @param prompt a format string used to prompt the user
     * @param customers a collection of customers to choose from
     * @return a customer's order
     */
    public CustomerOrder promptForCustomer(String prompt, Collection<CustomerOrder> customers){
        return null;
    }

    /**
     * Prompts the user to select an existing player currently in the game
     *
     * @param prompt a format string used to prompt the user
     * @param bakery the magicBakery object used
     * @return an existing player
     */
    public Player promptForExistingPlayer(String prompt, MagicBakery bakery){
        Player currentPlayer = bakery.getCurrentPlayer();
        Collection<Player> players = bakery.getPlayers();
        HashMap<Integer, Player> playerTurns = bakery.getPlayerTurnList();

        System.out.println("Our players are: ");
        int value = 1;
        for (Player player : players){
            System.out.print(value + "." + player.toString() + " ");
            value++;
        }
        System.out.println("");
        int playerNumber = Integer.parseInt(readLine("%sSelect a player: ", prompt));

        Player selectedPlayer = playerTurns.get(playerNumber);

        return selectedPlayer;
    }

    /**
     * Prompts the user to select a file
     *
     * @param prompt a format string used to prompt the user
     * @return a selected file
     */
    public File promptForFilePath(String prompt){
        while (true){
            String filePath = readLine("%s File Path? ", prompt);
            File file = new File(filePath);
            return file;
        }
    }

    /**
     * Prompts the user to select an ingredient from the given collection
     *
     * @param prompt a format string used to prompt the user
     * @param ingredients a collection of ingredients to choose from
     * @return an ingredient
     */
    public Ingredient promptForIngredient(String prompt, Collection<Ingredient> ingredients){
        return null;
    }

    /**
     * Prompts the user to give the number of and the names of new players 
     *
     * @param prompt a format string used to prompt the user
     * @return a list string of names of new players
     */
    public List<String> promptForNewPlayers(String prompt){
        List<String> playerNames = new ArrayList<>();
        while (playerNames.size() < 5){
            for (int i = 1; i < 6; i++){
                if (i > 2){
                    String addAnother = readLine("%s [Y]es/[N]o ", prompt).toLowerCase().trim();
                    if (addAnother.equals("n")) {
                        return playerNames;
                    }
                }
                String newPlayerName = readLine("%sPlayer " + i + " name? ", "");
                playerNames.add(newPlayerName);

            }
        }
        return playerNames;
    }

    /**
     * Prompts the user to choose if they would like to start or load a game
     *
     * @param prompt a format string used to prompt the user
     * @return boolean value for start/load
     */
    public boolean promptForStartLoad(String prompt){
        while (true){
            String value = readLine("%s [S]tart/[L]oad ", prompt).toLowerCase().trim();
            if (value.equals("s")){
                return true;
            }
            else if (value.equals("l")) {
                return false;
            }
        }
    }

    /**
     * Prompts the user if they would like to select yes/no for the answer
     *
     * @param prompt a format string used to prompt the user
     * @return boolean representation of yes/no
     */
    public boolean promptForYesNo(String prompt){
        while (true){
            String value = readLine("%s [Y]es/[N]o ", prompt).toLowerCase().trim();
            if (value.equals("y")){
                return true;
            }
            else if (value.equals("n")) {
                return false;
            }
        }
    }

    /**
     * Prompts the user to choose to enumerate the chose collection
     *
     * @param prompt a format string used to prompt the user
     * @param collection the collection of objects to be enumerated
     * @return a new object enumerated
     * @throws java.lang.IllegalArgumentException if error
     */
    private Object promptEnumerateCollection(String prompt, Collection<Object> collection) throws java.lang.IllegalArgumentException {
        if (collection == null || collection.isEmpty()){
            throw new java.lang.IllegalArgumentException("Illegal");
        }
        return null;
    }

}

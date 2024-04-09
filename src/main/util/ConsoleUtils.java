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

public class ConsoleUtils {
    private Console console;

    public ConsoleUtils(){
        console = System.console();
    }

    public String readLine(){
        return console.readLine();
    }
    public String readLine(String fmt, Object... args){
        return console.readLine(fmt, args);
    }

    public MagicBakery.ActionType promptForAction(String prompt, MagicBakery bakery){
        return null;
    }

    public CustomerOrder promptForCustomer(String prompt, Collection<CustomerOrder> customers){
        return null;
    }

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

    public File promptForFilePath(String prompt){
        while (true){
            String filePath = readLine("%s File Path? ", prompt);
            File file = new File(filePath);
            return file;
        }
    }

    public Ingredient promptForIngredient(String prompt, Collection<Ingredient> ingredients){
        return null;
    }

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

    private Object promptEnumerateCollection(String prompt, Collection<Object> collection){
        return null;
    }

}

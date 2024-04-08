package bakery;
import java.util.ArrayList;
import java.util.Collection;
import util.CardUtils;
import java.util.HashMap;

public class MagicBakery {
    public Collection<Player> players;
    private HashMap<Integer, Player> playerTurnList;
    private int playerTurn;
    private int actionsRemaining;

    // ActionType enumeration
    public enum ActionType{
        DRAW_INGREDIENT, PASS_INGREDIENT, BAKE_LAYER, FULFIL_ORDER, REFRESH_PANTRY
    }

    public MagicBakery(long seed, String ingredientDeckFile, String layerDeckFile){
        ArrayList<Ingredient> ingredientDeck = CardUtils.readIngredientFile(ingredientDeckFile);
        this.players = new ArrayList<>();
        this.playerTurnList = new HashMap<Integer, Player>();
        this.playerTurn = 1;
    }

    public boolean endTurn(){
        return true;
    }

    public int getActionsPermitted(){
        int totalActions = 0;
        if (players.size() == 2 || players.size() == 3){
            totalActions = 3;
        }
        else if (players.size() == 4 || players.size() == 5){
            totalActions = 2;
        }
        return totalActions;
    }

    public int getActionsRemaining(){
        return actionsRemaining;
    }

    public void populatePlayerTurnList(){
        int value = 1;
        for (Player nplayer : players){
            playerTurnList.put(value, nplayer);
            value++;
        }
    }

    public HashMap<Integer, Player> getPlayerTurnList(){
        return playerTurnList;
    }

    public Player getCurrentPlayer(){
        Player currentPlayer = playerTurnList.get(playerTurn);
        return currentPlayer;
    }

    public Collection<Player> getPlayers(){
        return players;
    }

    public void passCard(Ingredient ingredient, Player recipient){
        actionsRemaining -= 1;
        if (actionsRemaining <= 0){
            throw new TooManyActionsException("Too many actions have been done");
        }
        Player currentPlayer = getCurrentPlayer();
        currentPlayer.removeFromHand(ingredient);
        recipient.addToHand(ingredient);
    }

    public void startGame(ArrayList<String> playerNames, String customerDeckFile){
        for (String newName : playerNames){
            Player newPlayer = new Player(newName);
            players.add(newPlayer);
        }
        System.out.println(players);
        this.actionsRemaining = getActionsPermitted();
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
        ingredients.add(eggs);
        ingredients.add(sugar);
        ingredients.add(sugar);
        ingredients.add(chocolate);

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

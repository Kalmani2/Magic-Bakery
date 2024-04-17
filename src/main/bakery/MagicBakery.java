package bakery;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import util.CardUtils;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

/**
 * The overall MagicBakery class that dictates the gameplay
 * @author Muhammad Kalmani
 * @version 1.0
 */
public class MagicBakery implements Serializable{
    private Customers customers;
    private Collection<Layer> layers;
    private Collection<Player> players;
    private Collection<Ingredient> pantry;
    private Collection<Ingredient> pantryDeck;
    private Collection<Ingredient> pantryDiscard;
    private Random random;
    private static final long serialVersionUID = 1L;

    // Extra instance variables
    private HashMap<Integer, Player> playerTurnList;
    private int playerTurn;
    private int actionsRemaining;

    // ActionType enumeration
    public enum ActionType{
        DRAW_INGREDIENT, PASS_INGREDIENT, BAKE_LAYER, FULFIL_ORDER, REFRESH_PANTRY
    }

    /**
     * Constructor of the magic bakery class, with various instance variables
     *
     * @param seed seed value for randomisation
     * @param ingredientDeckFile file path of the ingredients deck
     * @param layerDeckFile the file path of the layers deck
     */
    public MagicBakery(long seed, String ingredientDeckFile, String layerDeckFile){
        random = new Random(seed);
        pantryDeck = new Stack<Ingredient>();
        pantryDeck = CardUtils.readIngredientFile(ingredientDeckFile);
        layers = CardUtils.readLayerFile(layerDeckFile);
        pantry = new ArrayList<Ingredient>();
        pantryDiscard = new Stack<Ingredient>();
        players = new ArrayList<>();

        this.playerTurnList = new HashMap<Integer, Player>();
        this.playerTurn = 1;
    }

    /**
     * Bakes the chosen layer
     *
     * @param layer layer to be baked
     */
    public void bakeLayer(Layer layer){

    }

    /**
     * Returns ingredient taken from the magic bakery pantry deck
     *
     * @return ingredient taken from the deck
     */
    private Ingredient drawFromPantryDeck(){
        return null;
    }

    /**
     * takes the chosen ingredient from the pantry to hand
     *
     * @param ingredientName name of ingredient to be taken
     */
    public void drawFromPantry(String ingredientName){

    }

    /**
     * takes the chosen ingredient from the pantry to hand
     *
     * @param ingredient ingredient to be taken
     */
    public void drawFromPantry(Ingredient ingredient){

    }

    /**
     * Function used to choose if the turn should be ended
     *
     * @return boolean value of ending the current turn
     */
    public boolean endTurn(){
        return true;
    }

    /**
     * Fulfills the chosen customer order
     *
     * @param customer the customer order to be fulfilled
     * @param garnish boolean value if the order has garnishes
     * @return list of ingredients left
     */
    public List<Ingredient> fulfillOrder(CustomerOrder customer, boolean garnish){
        return null;
    }

    /**
     * Function used to calculate how many actions are permitted per turn
     *
     * @return integer of how many actions per turn
     */
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

    /**
     * Function used to return how many actions remain for the player for that turn
     *
     * @return integer of how many actions remain
     */
    public int getActionsRemaining(){
        return actionsRemaining;
    }

    /**
     * Givse the user the layers that they can bake
     *
     * @return collection of layers that is bakeable
     */
    public Collection<Layer> getBakeableLayers(){
        return null;
    }

    /**
     * The turn list of players is filled out
     */
    public void populatePlayerTurnList(){
        int value = 1;
        for (Player nplayer : players){
            playerTurnList.put(value, nplayer);
            value++;
        }
    }

    /**
     * Properly numbers the players turn list
     */
    public HashMap<Integer, Player> getPlayerTurnList(){
        return playerTurnList;
    }

    /**
     * The current player whos playing the turn
     *
     * @return the current player
     */
    public Player getCurrentPlayer(){
        Player currentPlayer = playerTurnList.get(playerTurn);
        return currentPlayer;
    }

    /**
     * Gives the customers in the magic bakery game
     *
     * @return customers of the magicBakery game
     */
    public Customers getCustomers(){
        return customers;
    }

    /**
     * Gives the customers who's orders are fulfillable
     *
     * @return collection of customerOrders
     */
    public Collection<CustomerOrder> getFulfilableCustomers(){
        return null;
    }

    /**
     * Gives the customers who's orders are garnishable
     *
     * @return collection of customerOrders
     */
    public Collection<CustomerOrder> getGarnishableCustomers(){
        return null;
    }

    /**
     * Gives the current layers in the Magic Bakery game
     *
     * @return collection of layers
     */
    public Collection<Layer> getLayers(){
        return layers;
    }

    /**
     * Gives the current ingredients in the Magic Bakery game
     *
     * @return collection of ingredients
     */
    public Collection<Ingredient> getPantry(){
        return pantry;
    }

    /**
     * Gives the current players in the Magic Bakery game
     *
     * @return collection of players
     */
    public Collection<Player> getPlayers(){
        return players;
    }

    /**
     * Loads the chosen game file to be played
     *
     * @param file the file to be loaded
     * @return a MagicBakery object to be loaded
     */
    public static MagicBakery loadState(File file){
        return null;
    }

    /**
     * Passes the ingredient from the current player's hand to the chosen player
     *
     * @param ingredient the file to be loaded
     * @param recipient the player who will be passed the ingredient
     */
    public void passCard(Ingredient ingredient, Player recipient){
        actionsRemaining -= 1;
        if (actionsRemaining <= 0){
            throw new TooManyActionsException("Too many actions have been done");
        }
        Player currentPlayer = getCurrentPlayer();
        currentPlayer.removeFromHand(ingredient);
        recipient.addToHand(ingredient);
    }

    /**
     * Prints the customer service record
     */
    public void printCustomerServiceRecord(){

    }

    /**
     * Prints the current state of the Magic Bakery game
     */
    public void printGameState(){

    }

    /**
     * Refreshes the game's pantry
     */
    public void refreshPantry(){
        
    }

    /**
     * Saves the state of the object to the specified file.
     *
     * @param file the file to which the state will be saved
     */
    public void saveState(File file){
        
    }

    /**
     * Starts the game
     *
     * @param playerNames the file to be loaded
     * @param customerDeckFile the file path for the customers
     */
    public void startGame(List<String> playerNames, String customerDeckFile){
        for (String newName : playerNames){
            Player newPlayer = new Player(newName);
            players.add(newPlayer);
        }
        System.out.println(players);
        this.actionsRemaining = getActionsPermitted();
    }
}

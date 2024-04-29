package bakery;
import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    private static final long serialVersionUID = 5L;

    // Extra instance variables
    private HashMap<Integer, Player> playerTurnList;
    private int playerTurn;
    private int actionsRemaining;

    /**
     * Represents different types of actions that can be performed in the game.
     */
    public enum ActionType{
        DRAW_INGREDIENT, PASS_INGREDIENT, BAKE_LAYER, FULFIL_ORDER, REFRESH_PANTRY
    }

    /**
     * Constructor of the magic bakery class, with various instance variables
     *
     * @param seed seed value for randomisation
     * @param ingredientDeckFile file path of the ingredients deck
     * @param layerDeckFile the file path of the layers deck
     * @throws java.io.FileNotFoundException file not found
     * @throws java.lang.IllegalArgumentException too many players or too little players
     */
    public MagicBakery(long seed, String ingredientDeckFile, String layerDeckFile) throws java.io.FileNotFoundException, java.lang.IllegalArgumentException {
        this.random = new Random(seed);
        this.pantryDeck = new Stack<Ingredient>();
        try {
            this.pantryDeck = CardUtils.readIngredientFile(ingredientDeckFile);
            this.layers = CardUtils.readLayerFile(layerDeckFile);
        } catch (Exception e) {
            throw new java.io.FileNotFoundException("File not found");
        }
        this.pantry = new ArrayList<Ingredient>();
        this.pantryDiscard = new Stack<Ingredient>();
        this.players = new ArrayList<>();

        if (players.size() == 1){
            throw new java.lang.IllegalArgumentException("Need more players");
        }
        else if (players.size() == 6){
            throw new java.lang.IllegalArgumentException("Too many players");
        }
    }

    /**
     * Bakes the chosen layer
     *
     * @param layer layer to be baked
     */
    public void bakeLayer(Layer layer){

        if (!layers.contains(layer)) {
            throw new WrongIngredientsException("Layer does not exist");
        }
        

        // remove from this.layers
        for (Layer i : layers){
            if (i.equals(layer)){
                layers.remove(layer);
            }
        }

        // add layer to players hand
        Player currentPlayer = getCurrentPlayer();
        currentPlayer.addToHand(layer);

        List<Ingredient> discardIngredients = new ArrayList<>();

        // remove ingredient from players hand
        for (Ingredient i : layer.getRecipe()){
            for (Ingredient j : currentPlayer.getHand()){
                if (i.equals(j)){
                    discardIngredients.add(i);
                    currentPlayer.removeFromHand(i);
                    break;
                }
            }
        }

        // discard ingredients from hand
        for (Ingredient i : discardIngredients){
            pantryDiscard.add(i);
        }
    }

    /**
     * Returns ingredient taken from the magic bakery pantry deck
     *
     * @return ingredient taken from the deck
     * @throws EmptyPantryException if pantry is empty
     */
    private Ingredient drawFromPantryDeck(){
        return null;
        // if (pantryDeck.size() == 0){
        //     throw new EmptyPantryException("Pantry deck is empty", null);
        // }
        // Ingredient poppedIngredient = ((Stack<Ingredient>) pantryDeck).pop();
        // return poppedIngredient;
    }

    /**
     * takes the chosen ingredient from the pantry to hand
     *
     * @param ingredientName name of ingredient to be taken
     * @throws WrongIngredientsException if ingredient doesn't exist
     * @throws TooManyActionsException if too many actions done
     */
    public void drawFromPantry(String ingredientName) throws WrongIngredientsException, TooManyActionsException{

        if (actionsRemaining == 0){
            throw new TooManyActionsException("Too many actions done");
        }

        Player currentPlayer = getCurrentPlayer();

        Ingredient newIngredient = new Ingredient(ingredientName);
        currentPlayer.addToHand(newIngredient);

        if (!pantry.contains(newIngredient)) {
            throw new WrongIngredientsException("Ingredient does not exist");
        }

        ArrayList<Ingredient> copyPantry = (ArrayList<Ingredient>) pantry;
        for (Ingredient i : copyPantry){
            if (i.toString().equals(ingredientName)){
                copyPantry.remove(i);
                break;
            }
        }
        this.actionsRemaining--;
        copyPantry.add(drawFromPantryDeck());
        pantry = copyPantry;
    }

    /**
     * takes the chosen ingredient from the pantry to hand
     *
     * @param ingredient ingredient to be taken
     */
    public void drawFromPantry(Ingredient ingredient){
        Player currentPlayer = getCurrentPlayer();
        currentPlayer.addToHand(ingredient);

        pantry.remove(ingredient);
        this.actionsRemaining--;
        pantry.add(drawFromPantryDeck());
    }

    /**
     * Function used to choose if the turn should be ended
     *
     * @return boolean value of ending the current turn
     */
    public boolean endTurn(){
        playerTurn++;
        if (players.size() == playerTurn){
            playerTurn = 1;
        }
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
     * Gives the user the layers that they can bake
     *
     * @return collection of layers that is bakeable
     */
    public Collection<Layer> getBakeableLayers(){
        List<Layer> bakeableLayers = new ArrayList<>();
        Player currentPlayer = getCurrentPlayer();
        for (Layer l : layers){
            if (l.canBake(currentPlayer.getHand())){
                if (bakeableLayers.indexOf(l) == -1){
                    bakeableLayers.add(l);
                }
            }
        }

        return bakeableLayers;
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
     * @return the variable playerTurnList
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
        List<Layer> newlayers = new ArrayList<>();
        for (Layer l : layers){
            if (newlayers.indexOf(l) == -1){
                newlayers.add(l);
            }
        }

        return newlayers;
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
     * @throws java.lang.ClassCastException if save state is invalid
     * @throws java.io.FileNotFoundException if file can't be found
     */
    public static MagicBakery loadState(File file) throws java.lang.ClassCastException, java.io.FileNotFoundException{
        MagicBakery loadedBakery = null;

        if (!file.exists()) {
            throw new java.io.FileNotFoundException("File cannot be found");
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Object obj = objectInputStream.readObject();

            if (obj instanceof MagicBakery) {
                loadedBakery = (MagicBakery) obj;
                System.out.println("State loaded successfully.");
            } else {
                throw new java.lang.ClassCastException("Save state is invalid");
            }

        } catch (IOException | ClassNotFoundException e) {
            
        }

        return loadedBakery;
    }

    /**
     * Passes the ingredient from the current player's hand to the chosen player
     *
     * @param ingredient the file to be loaded
     * @param recipient the player who will be passed the ingredient
     */
    public void passCard(Ingredient ingredient, Player recipient){

        if (!pantryDeck.contains(ingredient)) {
            throw new WrongIngredientsException("Ingredient does not exist");
        }
        

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
        pantry.clear();

        Stack<Ingredient> newStack = new Stack<>();
        newStack.addAll(pantryDeck);

        for (int i = 0; i < 5; i++){
            Ingredient ingredient1 = newStack.pop();
            pantry.add(ingredient1);
            pantryDiscard.add(ingredient1);
        }
        pantryDeck = newStack;
        actionsRemaining--;
        if (actionsRemaining == 0){
            endTurn();
        }
    }

    /**
     * Saves the state of the object to the specified file.
     *
     * @param file the file to which the state will be saved
     * @throws java.io.FileNotFoundException if file not found
     */
    public void saveState(File file) throws java.io.FileNotFoundException{
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(this);
            System.out.println("State saved successfully.");

        } catch (IOException e) {
            throw new java.io.FileNotFoundException("File not found");
        }
    }

    /**
     * Starts the game
     *
     * @param playerNames the file to be loaded
     * @param customerDeckFile the file path for the customers
     * @throws java.io.FileNotFoundException if file not found
     */
    public void startGame(List<String> playerNames, String customerDeckFile) throws java.io.FileNotFoundException {
        Stack<Ingredient> newStack = new Stack<>();
        newStack.addAll(pantryDeck);

        for (String newName : playerNames){
            Player newPlayer = new Player(newName);

            // populate player's hands
            for (int i = 0; i < 3; i++){
                newPlayer.addToHand(newStack.pop());
            }

            this.players.add(newPlayer);
        }

        // populate pantry
        for (int i = 0; i < 5; i++){
            pantry.add(newStack.pop());
        }

        this.playerTurnList = new HashMap<Integer, Player>();
        this.playerTurn = 1;
        populatePlayerTurnList();
            
        // fix readCustomerFile first
        // if (CardUtils.readCustomerFile(customerDeckFile, layers) == null){
        //     throw new FileNotFoundException("File not found");
        // }
        customers = new Customers(customerDeckFile, random, layers, players.size());
        Collections.shuffle(new ArrayList<>(newStack), this.random);

        System.out.println(players);
        this.actionsRemaining = getActionsPermitted();

        pantryDeck = newStack;
    }
}

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

public class MagicBakery implements Serializable{
    private Customers customers;
    private Collection<Layer> layers;
    private Collection<Player> players;
    private Collection<Ingredient> pantry;
    private Collection<Ingredient> pantryDeck;
    private Collection<Ingredient> pantryDiscard;
    private Random random;
    private static final long serialVersionUID = 1L;

    private HashMap<Integer, Player> playerTurnList;
    private int playerTurn;
    private int actionsRemaining;

    // ActionType enumeration
    public enum ActionType{
        DRAW_INGREDIENT, PASS_INGREDIENT, BAKE_LAYER, FULFIL_ORDER, REFRESH_PANTRY
    }

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

    public void bakeLayer(Layer layer){

    }

    private Ingredient drawFromPantryDeck(){
        return null;
    }

    public void drawFromPantry(String ingredientName){

    }

    public void drawFromPantry(Ingredient ingredient){

    }

    public boolean endTurn(){
        return true;
    }

    public List<Ingredient> fulfillOrder(CustomerOrder customer, boolean garnish){
        return null;
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

    public Collection<Layer> getBakeableLayers(){
        return null;
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

    public Customers getCustomers(){
        return customers;
    }

    public Collection<CustomerOrder> getFulfilableCustomers(){
        return null;
    }

    public Collection<CustomerOrder> getGarnishableCustomers(){
        return null;
    }

    public Collection<Layer> getLayers(){
        return layers;
    }

    public Collection<Ingredient> getPantry(){
        return pantry;
    }

    public Collection<Player> getPlayers(){
        return players;
    }

    public static MagicBakery loadState(File file){
        return null;
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

    public void printCustomerServiceRecord(){

    }

    public void printGameState(){

    }

    public void refreshPantry(){
        
    }

    public void saveState(File file){
        
    }

    public void startGame(List<String> playerNames, String customerDeckFile){
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

        CustomerOrder order1 = new CustomerOrder("Bob", layer.getRecipe(), garnishes, 1);

        Player joe = new Player("Joe");
        joe.addToHand(ingredients);
        System.out.println(joe.getHand());
        System.out.println(joe.getHandStr());
    }
}

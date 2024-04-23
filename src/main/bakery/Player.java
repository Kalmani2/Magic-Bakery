package bakery;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Player class that outlines what a player has and can do
 * @author Muhammad Kalmani
 * @version 1.0
 */
public class Player implements Serializable{
    private List<Ingredient> hand;
    private String name;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for a new player that gives it the chosen name and an empty hand
     * 
     * @param name string parameter that decides the player's name
     */
    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<>();
    }

    /**
     * Adds a list of ingredients to the player's hand
     * 
     * @param ingredients chosen list of ingredients to be added
     */
    public void addToHand(List<Ingredient> ingredients){
        hand.addAll(ingredients);
    }

    /**
     * Adds the chosen ingredient to the player's hand
     * 
     * @param ingredient chosen ingredient to be added
     */
    public void addToHand(Ingredient ingredient){
        hand.add(ingredient);
    }

    /**
     * Checks to see if the player has the chosen ingredient on hand
     * 
     * @param ingredient chosen ingredient to be added
     * @return boolean value if ingredient is on hand
     */
    public boolean hasIngredient(Ingredient ingredient){
        for (Ingredient str : hand){
            if (str.toString().equals(ingredient.toString())){
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the chosen ingredient from the player's hand
     * 
     * @param ingredient chosen ingredient
     * @throws WrongIngredientsException if ingredient is missing
     */
    public void removeFromHand(Ingredient ingredient) throws WrongIngredientsException{
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).toString().equals(ingredient.toString())) {
                hand.remove(i);
                i--;
                return;
            }
        }
        throw new WrongIngredientsException("Missing ingredient");
    }

    /**
     * Returns the player's sorted current hand of ingredients and layers
     * 
     * @return List of ingredients
     */
    public List<Ingredient> getHand(){
        return hand;
    }

    /**
     * Get the player's current hand in a sorted string format that also counts duplicates
     * 
     * @return String format of player's hand
     */
    public String getHandStr(){

        List<Ingredient> sortedHand = new ArrayList<>(hand); 
        Collections.sort(sortedHand);

        List<Ingredient> newHand = sortedHand;
        ArrayList<String> handIngredients = new ArrayList<>();
        ArrayList<Integer> handIngredientOccurrence = new ArrayList<>();

        for (Ingredient ingredient : newHand) {
            String ingredientName = ingredient.toString();
            int index = -1;
            for (int i = 0; i < handIngredients.size(); i++) {
                if (handIngredients.get(i).equals(ingredientName)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                // Ingredient exists
                int newOccurrence = handIngredientOccurrence.get(index) + 1;
                handIngredientOccurrence.set(index, newOccurrence);
            } else {
                // New ingredient
                handIngredients.add(ingredientName);
                handIngredientOccurrence.add(1);
            }
        }

        // Build string

        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i < handIngredients.size(); i++){
            if (handIngredientOccurrence.get(i) == 1){
                returnString.append(handIngredients.get(i).substring(0, 1).toUpperCase() + handIngredients.get(i).substring(1));
            }
            else{
                returnString.append((handIngredients.get(i).substring(0, 1).toUpperCase() + handIngredients.get(i).substring(1) + " (x" + handIngredientOccurrence.get(i) + ")"));
            }
            if (i != handIngredients.size()-1){
                returnString.append(", ");
            }
        }

        String result = returnString.toString();
        return result;
    }

    /**
     * Returns the name of the player
     * 
     * @return string name of player
     */
    public String toString(){
        return (name);
    }
}

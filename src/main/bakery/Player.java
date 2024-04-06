package bakery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player {
    private ArrayList<Ingredient> hand;
    private String name;

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public void addToHand(ArrayList<Ingredient> ingredients){
        hand.addAll(ingredients);
    }

    public void addToHand(Ingredient ingredient){
        hand.add(ingredient);
    }

    public boolean hasIngredient(Ingredient ingredient){
        for (Ingredient str : hand){
            if (str.toString().equals(ingredient.toString())){
                return true;
            }
        }
        return false;
    }

    public void removeFromHand(Ingredient ingredient){
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).toString().equals(ingredient.toString())) {
                hand.remove(i);
                i--;
            }
        }
    }

    public ArrayList<Ingredient> getHand(){
        List<Ingredient> sortedHand = new ArrayList<>();
        sortedHand = hand;
        // Collections.sort(sortedHand);
        return hand;
    }

    public String getHandStr(){

        // Sort hand
        ArrayList<Ingredient> newHand = getHand();
        Ingredient[] sortedHand = newHand.toArray(new Ingredient[0]);
        Arrays.sort(sortedHand);

        ArrayList<String> handIngredients = new ArrayList<>();
        ArrayList<Integer> handIngredientOccurrence = new ArrayList<>();

        // Set the ingredients and its occurrences in its proper indexes
        // Iterate through sorted hand
        for (Ingredient ingredient : sortedHand) {
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

        

        return "str";
    }

    public String toString(){
        return (name);
    }
}

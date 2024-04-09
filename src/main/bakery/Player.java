package bakery;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{
    private List<Ingredient> hand;
    private String name;
    private static final long serialVersionUID = 1L;

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public void addToHand(List<Ingredient> ingredients){
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

    public List<Ingredient> getHand(){
        // Sort the hand by ascii value
        ArrayList<Ingredient> sortedHand = new ArrayList<>(hand); 
        
        for (int i = 0; i < sortedHand.size(); i++){
            int minimumIndex = i;
            for (int j = i + 1; j < sortedHand.size(); j++){
                int result = sortedHand.get(minimumIndex).toString().compareTo(sortedHand.get(j).toString());
                if (result > 0 ){ 
                    minimumIndex = j;
                }
            }
            Ingredient temp = sortedHand.get(i);
            sortedHand.set(i, sortedHand.get(minimumIndex));
            sortedHand.set(minimumIndex, temp);
        }
    
        return sortedHand;
    }

    public String getHandStr(){
        // Sort hand
        List<Ingredient> newHand = getHand();
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
                returnString.append(handIngredients.get(i));
            }
            else{
                returnString.append((handIngredients.get(i) + " (x" + handIngredientOccurrence.get(i) + ")"));
            }
            if (i != handIngredients.size()-1){
                returnString.append(", ");
            }
        }

        String result = returnString.toString();
        return result;
    }

    public String toString(){
        return (name);
    }
}

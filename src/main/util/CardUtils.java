package util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import bakery.Ingredient;

public class CardUtils {
    // public ArrayList<CustomerOrder> readCustomerFile(String path, ArrayList<Layers> layers){
    //     System.out.println("");
    // }

    public ArrayList<Ingredient> readIngredientFile(String path) throws IOException{
        ArrayList<Ingredient> ingredientsList = new ArrayList<Ingredient>();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            while ((line = br.readLine()) != null){
                String[] parts = line.split(",");
                String ingredientName = parts[0];
                int count = Integer.parseInt(parts[1]);

                ingredientsList.addAll(stringToIngredients(ingredientName));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return ingredientsList;
    }

    private ArrayList<Ingredient> stringToIngredients(String str){
        Ingredient newIngredient = new Ingredient(str);
        ArrayList<Ingredient> returnIngredient = new ArrayList<Ingredient>();
        returnIngredient.add(newIngredient);
        return returnIngredient;
    } 
}

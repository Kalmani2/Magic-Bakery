package util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import bakery.Ingredient;
import bakery.Layer;

public class CardUtils {

    public static ArrayList<Ingredient> readIngredientFile(String path){
        ArrayList<Ingredient> ingredientsList = new ArrayList<Ingredient>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine();
            while ((line = br.readLine()) != null){
                ingredientsList.addAll(stringToIngredients(line));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return ingredientsList;
    }

    private static ArrayList<Ingredient> stringToIngredients(String str){
        String[] parts = str.split(", ");
        String ingredientName = parts[0];
        int count = Integer.parseInt(parts[1]);
        ArrayList<Ingredient> returnIngredient = new ArrayList<Ingredient>();
        for (int i = 0; i < count; i++){
            Ingredient newIngredient = new Ingredient(ingredientName);
            returnIngredient.add(newIngredient);
        }
        return returnIngredient;
    }

    public static ArrayList<Layer> readLayerFile(String path){
        ArrayList<Layer> layerList = new ArrayList<Layer>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine();
            while ((line = br.readLine()) != null){
                layerList.addAll(stringToLayers(line));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return layerList;
    }

    private static ArrayList<Layer> stringToLayers(String str){
        int commaIndex = str.indexOf(",");
        ArrayList<Layer> returnLayer = new ArrayList<Layer>();
        String layerName = str.substring(0, commaIndex);
        String recipeList = str.substring(commaIndex + 1);
        String[] recipeIngredientsString = recipeList.split("; ");
        int ingredientCount = recipeIngredientsString.length;
        ArrayList<Ingredient> recipeIngredients = new ArrayList<Ingredient>();
        for (int i = 0; i < ingredientCount; i++){
            Ingredient newIngredient = new Ingredient(recipeIngredientsString[i]);
            recipeIngredients.add(newIngredient);
        }
        Layer newLayer = new Layer(layerName, recipeIngredients);
        for (int i = 0; i < 4; i++){
            returnLayer.add(newLayer);
        }

        return returnLayer;
    } 

    
}

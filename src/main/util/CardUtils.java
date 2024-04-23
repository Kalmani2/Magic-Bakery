package util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import bakery.CustomerOrder;
import bakery.Ingredient;
import bakery.Layer;

/**
 * Class comprised of functions used to read csv files into lists
 * @author Muhammad Kalmani
 * @version 1.0
 */
public class CardUtils {

    /**
     * private constructor for the CardUtils class
     *
     */
    private CardUtils(){}

    /**
     * Reads a csv file and converts it into a list of ingredients
     *
     * @param path the file path for the ingredient file
     * @return list of ingredients read from the csv file
     * @throws java.io.FileNotFoundException if file to read is not found
     */
    public static List<Ingredient> readIngredientFile(String path) throws java.io.FileNotFoundException {
        List<Ingredient> ingredientsList = new ArrayList<Ingredient>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine();
            while ((line = br.readLine()) != null){
                ingredientsList.addAll(stringToIngredients(line));
            }
        }
        catch (IOException e){
            throw new java.io.FileNotFoundException("File not found: " + path);
        }
        return ingredientsList;
    }

    /**
     * Converts a line of the csv file into a list of ingredients
     * Changes it from a string to an ingredient object
     *
     * @param str the string to be converted
     * @return list of ingredients converted from the string
     */
    private static List<Ingredient> stringToIngredients(String str){
        String[] parts = str.trim().split("\\s*,\\s*");
        String ingredientName = parts[0];
        int count = Integer.parseInt(parts[1].trim());
        List<Ingredient> returnIngredient = new ArrayList<>();
        for (int i = 0; i < count; i++){
            Ingredient newIngredient = new Ingredient(ingredientName);
            returnIngredient.add(newIngredient);
        }
        return returnIngredient;
    }
    
    /**
     * Reads a csv file and converts it into a list of layers
     *
     * @param path the file path for the layer file
     * @return list of layer read from the csv file
     * @throws java.io.FileNotFoundException if file not found
     */
    public static List<Layer> readLayerFile(String path) throws java.io.FileNotFoundException {
        List<Layer> layerList = new ArrayList<Layer>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine();
            while ((line = br.readLine()) != null){
                layerList.addAll(stringToLayers(line));
            }
        }
        catch (IOException e){
            throw new java.io.FileNotFoundException("File not found: " + path);
        }
        return layerList;
    }

    /**
     * Converts a line of the csv file into a list of layers
     * Changes it from a string to an layer object
     *
     * @param str the string to be converted
     * @return list of layer converted from the string
     */
    private static List<Layer> stringToLayers(String str){
        int commaIndex = str.indexOf(",");
        List<Layer> returnLayer = new ArrayList<>();
        String layerName = str.substring(0, commaIndex).trim();
        String recipeList = str.substring(commaIndex + 1);
        String[] recipeIngredientsString = recipeList.split(";\\s*"); // Adjusted split to handle spaces after semicolon
        List<Ingredient> recipeIngredients = new ArrayList<>();
        for (String ingredientString : recipeIngredientsString) {
            Ingredient newIngredient = new Ingredient(ingredientString.trim()); // Trim to remove leading/trailing spaces
            recipeIngredients.add(newIngredient);
        }
        Layer newLayer = new Layer(layerName, recipeIngredients);
        for (int i = 0; i < 4; i++){
            returnLayer.add(newLayer);
        }
        return returnLayer;
    }
    
    /**
     * Reads a csv file and converts it into a list of CustomerOrders
     *
     * @param path the file path for the CustomerOrder file
     * @param layer layers used to check if ingredients are layer
     * @return list of CustomerOrders read from the csv file
     * @throws java.io.FileNotFoundException if file not found
     */
    public static List<CustomerOrder> readCustomerFile(String path, Collection<Layer> layer) throws java.io.FileNotFoundException {
        List<CustomerOrder> orderList = new ArrayList<CustomerOrder>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine();
            while ((line = br.readLine()) != null){
                orderList.add(stringToCustomerOrder(line, layer));
            }
        }
        catch (IOException e){
            throw new java.io.FileNotFoundException("File not found: " + path);
        }
        return orderList;
    }

    /**
     * Converts a line of the csv file into a new CustomerOrder
     * Uses the layer collection to check if ingredient should be added as ingredient or
     *
     * @param str the string to be converted
     * @param layers layers used to check if ingredients are layer
     * @return a new CustomerOrder converted from the csv string
     */
    private static CustomerOrder stringToCustomerOrder(String str, Collection<Layer> layers){

        // splits initial string to 4 parts
        String[] parts = str.split(",\\s*", 4);
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].replaceAll("\\s+", "");
        }
        int level = Integer.parseInt(parts[0]);
        String orderName = parts[1].trim();

        // splits recipe list by ;
        String[] parts2 = parts[2].split(";");
        List<Ingredient> recipeList = new ArrayList<>();
        for (String part : parts2) {
            recipeList.add(new Ingredient(part.trim()));
        }

        // splits garnish list by ;
        List<Ingredient> garnishList = new ArrayList<>();
        if (parts.length == 4) { 
            String[] parts3 = parts[3].split(";");
            for (String part : parts3) {
                garnishList.add(new Ingredient(part.trim()));
            }
        }

        List<Ingredient> newRecipeList = new ArrayList<>();
        List<Ingredient> newGarnishList = new ArrayList<>();

        // recipe list
        for (Ingredient i : recipeList){
            boolean found = false;
            for (Layer j : layers){
                if (i.toString().equals(j.toString())){
                    found = true;
                    Layer newLayer = new Layer(j.toString(), j.getRecipe());
                    newRecipeList.add(newLayer);
                    break;
                }
            }
            if (!found){
                newRecipeList.add(i);
            }
        }

        // garnish list
        for (Ingredient i : garnishList){
            boolean found = false;
            for (Layer j : layers){
                if (i.toString().equals(j.toString())){
                    found = true;
                    Layer newLayer = new Layer(j.toString(), j.getRecipe());
                    newGarnishList.add(newLayer);
                    break;
                }
            }
            if (!found){
                newGarnishList.add(i);
            }
        }

        CustomerOrder order = new CustomerOrder(orderName, newRecipeList, newGarnishList, level);

        return order;
    }

    
}

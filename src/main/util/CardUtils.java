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

public class CardUtils {

    private CardUtils(){}

    public static List<Ingredient> readIngredientFile(String path){
        List<Ingredient> ingredientsList = new ArrayList<Ingredient>();
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

    private static List<Ingredient> stringToIngredients(String str){
        String[] parts = str.split(", ");
        String ingredientName = parts[0];
        int count = Integer.parseInt(parts[1]);
        List<Ingredient> returnIngredient = new ArrayList<Ingredient>();
        for (int i = 0; i < count; i++){
            Ingredient newIngredient = new Ingredient(ingredientName);
            returnIngredient.add(newIngredient);
        }
        return returnIngredient;
    }

    public static List<Layer> readLayerFile(String path){
        List<Layer> layerList = new ArrayList<Layer>();
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

    private static List<Layer> stringToLayers(String str){
        int commaIndex = str.indexOf(",");
        List<Layer> returnLayer = new ArrayList<Layer>();
        String layerName = str.substring(0, commaIndex);
        String recipeList = str.substring(commaIndex + 1);
        String[] recipeIngredientsString = recipeList.split("; ");
        int ingredientCount = recipeIngredientsString.length;
        List<Ingredient> recipeIngredients = new ArrayList<Ingredient>();
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

    public static List<CustomerOrder> readCustomerFile(String path, Collection<Layer> layer){
        List<CustomerOrder> orderList = new ArrayList<CustomerOrder>();
        String line;
        List<Layer> layerList = new ArrayList<Layer>();
        layerList = readLayerFile("../../io/layers.csv");
        List<Layer> uniqueLayerList = new ArrayList<>();
        
        // Finds the unique layers in the list
        for (Layer layers : layerList) {
            boolean isUnique = true;
            for (Layer uniqueLayer : uniqueLayerList) {
                if (uniqueLayer.getName().equals(layers.getName())) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                uniqueLayerList.add(layers);
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine();
            while ((line = br.readLine()) != null){
                orderList.add(stringToCustomerOrder(line,layerList));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return orderList;
    }

    private static CustomerOrder stringToCustomerOrder(String str, Collection<Layer> layers){
        List<CustomerOrder> returnOrder = new ArrayList<CustomerOrder>();
        String[] parts = str.split(", ");
        int level = Integer.parseInt(parts[0]);
        String orderName = parts[1];
        List<Ingredient> recipeList = new ArrayList<>();
        List<Ingredient> garnishList = new ArrayList<>();
        recipeList = stringToIngredients(parts[2]);
        garnishList = stringToIngredients(parts[3]);
        CustomerOrder order = new CustomerOrder(orderName, recipeList, garnishList, level);
        returnOrder.add(order);

        // Placeholder
        CustomerOrder customerOrder = new CustomerOrder(orderName, recipeList, garnishList, level);

        return customerOrder;
    }

    
}

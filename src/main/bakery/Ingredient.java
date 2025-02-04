package bakery;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class for the ingredients used in the magicBakery game
 * @author Muhammad Kalmani
 * @version 1.0
 */
public class Ingredient implements Comparable<Ingredient>, Serializable{
    private String name;

    /**
     * Helpful duck ingredient
     */
    public static final Ingredient HELPFUL_DUCK = new Ingredient("Helpful duck 𓅭");
    private static final long serialVersionUID = 3L;

    /**
     * Instantiate the ingredient
     *
     * @param name String name of the ingredient
     */
    public Ingredient(String name){
        this.name = name;
    }

    /**
     * Checks if ingredient o is equal to the ingredient
     *
     * @param o object to be compared
     * @return {@code true} if the objects are equal, {@code false} otherwise
     */
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name);
    }

    /**
     * Return the hashcode of the ingredient name
     *
     * @return integer value of the hashed name
     */
    public int hashCode(){
        return Objects.hash(name);
    }

    /**
     * Returns string representation of the ingredient
     *
     * @return String value of ingredient
     */
    public String toString(){
        return (name);
    }

    /**
     * Compares the ascii value of the current ingredient and ingredient o
     *
     * @param ingredient an ingredient
     * @return integer value of ingredient o compared to the ingredient name
     */
    public int compareTo(Ingredient ingredient) {
        return this.name.compareTo(ingredient.name);
    }
}
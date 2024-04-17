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
    public static final Ingredient HELPFUL_DUCK = new Ingredient("Helpful duck 𓅭");
    private static final long serialVersionUID = 1L;

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
     * @return boolean value 
     */
    @Override
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
    @Override
    public String toString(){
        return (name);
    }

    /**
     * Compares the ascii value of the current ingredient and ingredient o
     *
     * @param o an ingredient
     * @return integer value of ingredient o compared to the ingredient name
     */
    @Override
    public int compareTo(Ingredient o) {
        return this.name.compareTo(o.name);
    }
}
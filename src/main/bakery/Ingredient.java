package bakery;

import java.io.Serializable;
import java.util.Objects;

public class Ingredient implements Comparable<Ingredient>, Serializable{
    private String name;
    public static final Ingredient HELPFUL_DUCK = new Ingredient("Helpful duck 𓅭");
    private static final long serialVersionUID = 1L;

    public Ingredient(String name){
        this.name = name;
    }

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

    public int hashCode(){
        return Objects.hash(name);
    }

    @Override
    public String toString(){
        return (name);
    }

    @Override
    public int compareTo(Ingredient o) {
        return this.name.compareTo(o.name);
    }
}
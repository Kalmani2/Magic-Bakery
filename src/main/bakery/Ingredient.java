package bakery;

import java.io.Serializable;

public class Ingredient implements Comparable<Ingredient>, Serializable{
    private String name;
    public static final Ingredient HELPFUL_DUCK = new Ingredient("HELPFUL_DUCK");
    private static final long serialVersionUID = 1L;

    public Ingredient(String name){
        this.name = name;
    }

    public boolean equals(Object o){
        return true;
    }

    public int hashCode(){
        return 1;
    }

    @Override
    public String toString(){
        return (name);
    }

    @Override
    public int compareTo(Ingredient o) {
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}
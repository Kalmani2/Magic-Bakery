package bakery;

public class Ingredient {
    private String name;
    public static Ingredient HELPFUL_DUCK = new Ingredient("HELPFUL_DUCK");

    public Ingredient(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return (name);
    }
}
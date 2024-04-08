package bakery;

public class WrongIngredientsException extends IllegalArgumentException{
    public WrongIngredientsException(){
        super();
    }

    public WrongIngredientsException(String message){
        super(message);
    }
}

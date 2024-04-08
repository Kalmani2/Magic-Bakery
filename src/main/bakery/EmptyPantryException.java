package bakery;

public class EmptyPantryException extends RuntimeException{
    public EmptyPantryException(){
        super();
    }

    public EmptyPantryException(String message){
        super(message);
    }
}

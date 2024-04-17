package bakery;

/**
 * Exception class for when WrongIngredientsEXception occurs
 * @author Muhammad Kalmani
 * @version 1.0
 */
public class WrongIngredientsException extends IllegalArgumentException{

    /**
     * Constructor when exception occurs with no specified message
     */
    public WrongIngredientsException(){
        super();
    }

    /**
     * Constructor for when exception occurs with specified message
     * 
     * @param message message to be displayed
     */
    public WrongIngredientsException(String message){
        super(message);
    }
}

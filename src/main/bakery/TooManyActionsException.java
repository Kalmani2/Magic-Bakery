package bakery;

/**
 * Exception class for when too many actions occurs
 * @author Muhammad Kalmani
 * @version 1.0
 */
public class TooManyActionsException extends IllegalStateException{

    /**
     * Constructor when exception occurs with no specified message
     */
    public TooManyActionsException(){
        super();
    }

    /**
     * Constructor for when exception occurs with specified message
     * 
     * @param message message to be displayed
     */
    public TooManyActionsException(String message){
        super(message);
    }
}

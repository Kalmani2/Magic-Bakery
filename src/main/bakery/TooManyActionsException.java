package bakery;

public class TooManyActionsException extends IllegalStateException{
    public TooManyActionsException(){
        super();
    }

    public TooManyActionsException(String message){
        super(message);
    }
}

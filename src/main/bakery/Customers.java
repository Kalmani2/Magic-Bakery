package bakery;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import bakery.CustomerOrder.CustomerOrderStatus;

public class Customers implements Serializable{
    private Collection<CustomerOrder> activeCustomers;
    private Collection<CustomerOrder> customerDeck;
    private List<CustomerOrder> inactiveCustomers;
    private Random random;
    private static final long serialVersionUID = 1L;

    public Customers(String deckFile, Random random, Collection<Layer> layers, int numPlayers){

    }

    public CustomerOrder addCustomerOrder(){
        return null;
    }

    public boolean customerWillLeaveSoon(){
        return true;
    }

    public CustomerOrder drawCustomer(){
        return null;
    }

    public Collection<CustomerOrder> getActiveCustomers(){
        return null;
    }

    public Collection<CustomerOrder> getCustomerDeck(){
        return null;
    }

    public Collection<CustomerOrder> getFulfillable(List<Ingredient> hand){
        return null;
    }

    public Collection<CustomerOrder> getInactiveCustomersWithStatus(CustomerOrderStatus status){
        return null;
    }

    private void initialiseCustomerDeck(String deckfile, Collection<Layer> layers, int numPlayers){

    }

    public boolean isEmpty(){
        return true;
    }

    public CustomerOrder peek(){
        return null;
    }

    public void remove(CustomerOrder customer){

    }

    public int size(){
        return 1;
    }

    public CustomerOrder timePasses(){
        return null;
    }
}

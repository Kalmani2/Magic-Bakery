package bakery;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import bakery.CustomerOrder.CustomerOrderStatus;
import util.CardUtils;

/**
 * Customers class that represents the current customers in the game
 * @author Muhammad Kalmani
 * @version 1.0
 */
public class Customers implements Serializable{
    private Collection<CustomerOrder> activeCustomers;
    private Collection<CustomerOrder> customerDeck;
    private List<CustomerOrder> inactiveCustomers;
    private Random random;
    private static final long serialVersionUID = 2L;

    /**
     * Instantiates the customer with the given values
     *
     * @param deckFile string value for the customers
     * @param random random value 
     * @param layers the layers used for the customers
     * @param numPlayers the number of players for the customers
     * @throws java.io.FileNotFoundException if customer file not found
     */
    public Customers(String deckFile, Random random, Collection<Layer> layers, int numPlayers) throws java.io.FileNotFoundException {
        try {
            customerDeck = CardUtils.readCustomerFile(deckFile, layers);
        } catch (Exception e) {
            throw new java.io.FileNotFoundException("File not found");
        }
    }

    /**
     * adds a customer order to current
     *
     * @return new customer order
     */
    public CustomerOrder addCustomerOrder(){
        return null;
    }

    /**
     * Function used to see if the customers will leave soon
     *
     * @return boolean value if customers will leave soon
     */
    public boolean customerWillLeaveSoon(){
        return true;
    }

    /**
     * Takes a customer order from the deck for the current player
     *
     * @return take a customer order
     */
    public CustomerOrder drawCustomer(){
        return null;
    }

    /**
     * Returns a collection of which customers have active orders
     *
     * @return collection of active customers
     */
    public Collection<CustomerOrder> getActiveCustomers(){
        return activeCustomers;
    }

    /**
     * Return the deck of customers on hand
     *
     * @return collection of customer orders
     */
    public Collection<CustomerOrder> getCustomerDeck(){
        return customerDeck;
    }

    /**
     * Can the customer order be fulfilled with the player's current hand
     *
     * @param hand list of ingredients on the players hand
     * @return collection of customer orders
     */
    public Collection<CustomerOrder> getFulfillable(List<Ingredient> hand){
        return null;
    }

    /**
     * Returns a collection of customer orders matching the given order status.
     *
     * @param status the status of the customer orders to match
     * @return a collection of customer orders matching the given status
     */
    public Collection<CustomerOrder> getInactiveCustomersWithStatus(CustomerOrderStatus status){
        List<CustomerOrder> inactiveCustomers = new ArrayList<>();
        for (CustomerOrder i : inactiveCustomers){
            if (i.getStatus() == status){
                inactiveCustomers.add(i);
            }
        }

        return inactiveCustomers;
    }

    private void initialiseCustomerDeck(String deckfile, Collection<Layer> layers, int numPlayers){
        this.customerDeck = new Stack<>();
        try {
            customerDeck.addAll(CardUtils.readCustomerFile(deckfile, layers));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Collections.shuffle((Stack<CustomerOrder>) customerDeck, random);
    }

    /**
     * Function used to see if there are no customers left
     *
     * @return boolean value
     */
    public boolean isEmpty(){
        return true;
    }

    /**
     * Take a look at the current customer order on hand
     *
     * @return look at customer order
     */
    public CustomerOrder peek(){
        return null;
    }

    /**
     * Removes the specified customer order from the list of customer orders.
     *
     * @param customer the customer order to be removed
     */
    public void remove(CustomerOrder customer){

    }

    /**
     * Returns the size of the customers object 
     *
     * @return the integer size of the customers object
     */
    public int size(){
        return 1;
    }

    /**
     * Passes time and shows what happens to the customer order
     *
     * @return CustomerOrder after time passes
     */
    public CustomerOrder timePasses(){
        return null;
    }
}

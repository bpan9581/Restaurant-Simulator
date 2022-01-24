/*Brian Pan 112856231 RECITATION 1*/
import java.util.ArrayList;

/**
 * A Restaurant object that is able to hold Customer objects in an array
 */
public class Restaurant extends ArrayList<Customer> {
    /**
     * No args constructor
     */
    public Restaurant(){}

    /**
     * Enqueues a Customer object into the Restaurant array
     * @param c
     * An instance of a Customer
     */
    public void enqueue(Customer c){
        add(c);
    }

    /**
     * Dequeues a customer object from the Restaurant array when customer has finished eating
     * @return
     * Returns a Customer object if they have finished eating
     */
    public Customer dequeue(){
        for(int i = 0; i < size(); i++){
            if(this.get(i).getTimeToServe() == 0)
                return remove(i);
        }
        return null;
    }

    /**
     *
     * @return
     * Returns the top customer in the restaurant
     */
    public Customer peek(){
        return get(0);
    }

    /**
     *
     * @return
     * Returns size of array
     */
    public int size(){
        return super.size();
    }

    /**
     * Reduces the time customer needs to spend in restaurant
     */
    public void reduce(){
        if(isEmpty())
            return;
        for (Customer customer : this) {
            if (customer.getTimeToServe() != 0)
                customer.passTime();
        }
    }

    /**
     *
     * @return
     * Returns whether the Restaurant array is empty
     */
    public boolean isEmpty(){
        return size() == 0;
    }

    /**
     *
     * @return
     * Returns string representation of the Array that holds Customer objects
     */
    public String toString(){
        String restaurant = "";
        for (Customer customer : this) {
            restaurant += customer;
        }
        return restaurant;
    }
}

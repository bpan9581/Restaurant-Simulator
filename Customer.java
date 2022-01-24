/*Brian Pan 112856231 RECITATION 1*/

/**
 * Class that creates a Consumer Object that contains their order, price, and time they spent eating
 */
public class Customer {
    private static int totalCustomers = 0;
    private int orderNumber = 0, priceOfFood = 0, timeArrived = 0, timeToServe = 0, timeSpent = 0;
    private String food = "";

    /**
     *
     * @return
     * Returns the time the costumer spent eating
     */
    public int getTimeSpent() {
        return timeSpent;
    }

    /**
     *
     * @param timeSpent
     * Time customer spent eating
     */
    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

    /**
     *
     * @return
     * returns the total customers who are in the restaurants
     */
    public static int getTotalCustomers() {
        return totalCustomers;
    }

    /**
     *
     * @param totalCustomers
     * the total amount of people who entered
     */
    public static void setTotalCustomers(int totalCustomers) {
        Customer.totalCustomers = totalCustomers;
    }

    /**
     *
     * @return
     * The order number of Customer
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     *
     * @param orderNumber
     * Order number of Customer in queue
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     *
     * @return
     * Returns the cost of food
     */
    public int getPriceOfFood() {
        return priceOfFood;
    }

    /**
     *
     * @param priceOfFood
     * The cost or price of food
     */
    public void setPriceOfFood(int priceOfFood) {
        this.priceOfFood = priceOfFood;
    }

    /**
     *
     * @return
     * Time the customer arrived
     */
    public int getTimeArrived() {
        return timeArrived;
    }

    /**
     *
     * @param timeArrived
     * Time the customer arrived
     */
    public void setTimeArrived(int timeArrived) {
        this.timeArrived = timeArrived;
    }

    /**
     *
     * @return
     * Returns the time it takes to serve customer
     */
    public int getTimeToServe() {
        return timeToServe;
    }

    /**
     *
     * @param timeToServe
     * Time it takes to serve customer
     */
    public void setTimeToServe(int timeToServe) {
        this.timeToServe = timeToServe;
    }

    /**
     *
     * @return
     * Returns the food the customer ordered
     */
    public String getFood() {
        return food;
    }

    /**
     * The food the customer ordered
     * @param food
     */
    public void setFood(String food) {
        this.food = food;
    }

    /**
     * Decrements the time the customer needs to spend in the restaurant
     */
    public void passTime(){
        timeToServe -= 5;
    }

    /**
     * No args constructor for Customer object
     */
    public Customer(){
        totalCustomers++;
    }

    /**
     * Args construtor for Customer class
     * @param orderNumber
     * Order number of customer
     * @param priceOfFood
     * Price of food customer ordered
     * @param timeToServe
     * Time needed to spent in the restaurant
     * @param food
     * Food the customer ordered
     */
    public Customer(int orderNumber, int priceOfFood, int timeToServe, String food){
        this.orderNumber = orderNumber;
        this.priceOfFood = priceOfFood;
        this.timeToServe = timeToServe;
        this.timeSpent = timeToServe;
        this.food = food;
    }

    /**
     * Returns the Customer object in the form of a String
     * @return
     */
    public String toString(){
        String foodCap = "";
        for(int i = 0; i < food.length(); i++)
            if(Character.isUpperCase(food.charAt(i)))
                foodCap += food.charAt(i);
        return "[#" + orderNumber + ", " + foodCap + ", " + (timeToServe) + " min]";
    }
}

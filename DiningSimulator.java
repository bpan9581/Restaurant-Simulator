/*Brian Pan 112856231 RECITATION 1*/
import java.util.*;

/**
 * Class that simulates a restaurant serving customers at different intervals of time
 */
public class DiningSimulator extends ArrayList<Restaurant> {
    public static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

    private static int chefs, duration, maxCustomerSize, maxRestaurants, customersLost,
            totalServiceTime, customersServed, profit;
    private static double arrivalProb;

    /**
     * Returns the restaurants in diner
     * @return
     */
    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    /**
     * An arraylist representing the restaurant
     * @param restaurants
     */
    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    /**
     * Returns the number of chefs
     * @return
     */
    public int getChefs() {
        return chefs;
    }

    /**
     * The number of chefs working in each restaurant
     * @param chefs
     */
    public void setChefs(int chefs) {
        this.chefs = chefs;
    }

    /**
     * Returns the number of simulation units
     * @return
     */
    public int getDuration() {
        return duration;
    }

    /**
     * The number of simulation units
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Returns the maximum number of customers a restaurant can serve
     * @return
     */
    public int getMaxCustomerSize() {
        return maxCustomerSize;
    }

    /**
     * The number of customers a restaurant can serve
     * @param maxCustomerSize
     */
    public void setMaxCustomerSize(int maxCustomerSize) {
        this.maxCustomerSize = maxCustomerSize;
    }

    /**
     * Returns the total number of restaurants in the simulation
     * @return
     */
    public int getMaxRestaurants() {
        return maxRestaurants;
    }

    /**
     * The number of restaurants in the simualtion
     * @param maxRestaurants
     */
    public void setMaxRestaurants(int maxRestaurants) {
        this.maxRestaurants = maxRestaurants;
    }

    /**
     * Returns the number of customers lost due to overflow
     * @return
     */
    public int getCustomersLost() {
        return customersLost;
    }

    /**
     * The number of customers lost due to not being seated
     * @param customersLost
     */
    public void setCustomersLost(int customersLost) {
        this.customersLost = customersLost;
    }

    /**
     * Returns the total time customers have been served in the restaurant
     * @return
     */
    public int getTotalServiceTime() {
        return totalServiceTime;
    }

    /**
     * The total time customers have been served
     * @param totalServiceTime
     */
    public void setTotalServiceTime(int totalServiceTime) {
        this.totalServiceTime = totalServiceTime;
    }

    /**
     * Returns the number of customers that have finished their meal
     * @return
     */
    public int getCustomersServed() {
        return customersServed;
    }

    /**
     * The number of customers that finished their meal
     * @param customersServed
     */
    public void setCustomersServed(int customersServed) {
        this.customersServed = customersServed;
    }

    /**
     * Returns the profit made in the simulation
     * @return
     */
    public int getProfit() {
        return profit;
    }

    /**
     * The total amount of money gained
     * @param profit
     */
    public void setProfit(int profit) {
        this.profit = profit;
    }

    /**
     * Returns the chance of a customer entering the restaurant
     * @return
     */
    public double getArrivalProb() {
        return arrivalProb;
    }

    /**
     * The chance of a customer entering the restaurant
     * @param arrivalProb
     */
    public void setArrivalProb(double arrivalProb) {
        this.arrivalProb = arrivalProb;
    }

    /**
     * The simulation of a diner
     * @param maxRestaurants
     * Number of restaurants
     * @param maxCustomerSize
     * Max number of people a restaurant can serve
     * @param arrivalProb
     * Probabilty of a customer entering
     * @param chefs
     * Number of chefs per restaurant
     * @param duration
     * Total simulation time
     * @return
     * Returns the average time a customer has spent in the diner
     */
    public double stimulate(int maxRestaurants, int maxCustomerSize, double arrivalProb,
                            int chefs, int duration) {
        customersLost = 0;
        totalServiceTime = 0;
        customersServed = 0;
        profit = 0;
        int  totalCustomers = 0;
        for (int i = 0; i < maxRestaurants; i++) {
            restaurants.add(new Restaurant());
        }
        String paid = "";
        for (int i = 0; i < duration; i++) {
            System.out.println("Time : " + (i+1));
            if(paid.length() != 0)
                System.out.println(paid);
            String arrival = "", action = "";
            paid = "";
            for (int j = 0; j < maxRestaurants; j++) {
                restaurants.get(j).reduce();
                Customer finishedEating = new Customer();
                while (finishedEating != null) {
                    finishedEating = restaurants.get(j).dequeue();
                    if (finishedEating != null) {
                        paid += "Customer #" + finishedEating.getOrderNumber() + " has enjoyed their food! $" +
                                finishedEating.getPriceOfFood() + " profit" + '\n';
                        profit += finishedEating.getPriceOfFood();
                        customersServed++;
                        totalServiceTime += finishedEating.getTimeSpent();
                    }
                }
            }
            boolean flag = false;
            while (!flag) {
                if (Math.random() < arrivalProb) {
                    totalCustomers++;
                    int restaurantNumber = randInt(1, maxRestaurants);
                    arrival += "Customer #" + totalCustomers + " has entered Restaurant #" + (restaurantNumber) + '\n';
                    if (restaurants.get(restaurantNumber - 1).size() == maxCustomerSize) {
                        action += "Customer #" + totalCustomers + " cannot be seated!" +
                                "They have left the restaurant" + '\n';
                        customersLost++;
                    } else {
                        int orderType = randInt(1, 5);
                        String food = "";
                        int priceOfFood = 0, timeToServe = 0;
                        switch (orderType) {
                            case 1:
                                food = "Steak";
                                action += ("Customer #" + totalCustomers + " has been seated with order " + '"'
                                        + food + '"' + "\n");
                                priceOfFood = 25;
                                if (chefs > 5)
                                    timeToServe = 20;
                                else
                                    timeToServe = 30 - ((chefs - 3) * 5);
                                break;
                            case 2:
                                food = "Chicken Wings";
                                action += "Customer #" + totalCustomers + " has been seated with order " + '"'
                                        + food + '"' + "\n";
                                priceOfFood = 20;
                                if (chefs > 5)
                                    timeToServe = 20;
                                else
                                    timeToServe = 30 - ((chefs - 3) * 5);
                                break;
                            case 3:
                                food = "Cheese Burger";
                                action += "Customer #" + totalCustomers + " has been seated with order " + '"'
                                        + food + '"' + "\n";
                                priceOfFood = 15;
                                if (chefs > 5)
                                    timeToServe = 15;
                                else
                                    timeToServe = 25 - ((chefs - 3) * 5);
                                break;
                            case 4:
                                food = "Chicken Tenders";
                                action += "Customer #" + totalCustomers + " has been seated with order " + '"'
                                        + food + '"' + "\n";
                                priceOfFood = 10;
                                if (chefs > 5)
                                    timeToServe = 15;
                                else
                                    timeToServe = 25 - ((chefs - 3) * 5);
                                break;
                            case 5:
                                food = "Grilled Cheese";
                                action += "Customer #" + totalCustomers + " has been seated with order " + '"'
                                        + food + '"' + "\n";
                                priceOfFood = 10;
                                if (chefs > 5)
                                    timeToServe = 5;
                                else
                                    timeToServe = 15 - ((chefs - 3) * 5);
                                break;
                        }
                        Customer newCostumer = new Customer(totalCustomers, priceOfFood, timeToServe, food);
                        restaurants.get(restaurantNumber - 1).enqueue(newCostumer);
                    }
                }else
                    flag = true;
            }
            if(arrival.length() != 0)
                System.out.println(arrival);
            else
                System.out.println("No customer has entered. :( (sad face)");
            if(action.length() != 0)
                System.out.println(action);
            for(int x = 0; x < restaurants.size(); x++){
                System.out.println("R" + (x+1) + "{" + restaurants.get(x) + "}");
            }
            System.out.println();
        }
        if(customersServed == 0)
            return 0;
        return totalServiceTime / customersServed;
    }

    /**
     * Creates a random value between two parameters
     * @param minVal
     * Minimum value
     * @param maxVal
     * Maximun value
     * @return
     * Returns a random value
     */
    public int randInt(int minVal, int maxVal) {
        return (int) (Math.random() * maxVal + minVal);
    }

    public static void main(String[] args){
        DiningSimulator dining = new DiningSimulator();
        Scanner stdin = new Scanner(System.in);
        boolean quit = false;
        do {
            maxCustomerSize = 0;
            maxRestaurants = 0;
            arrivalProb = 0;
            chefs = 0;
            duration = 0;
            restaurants = new ArrayList<Restaurant>();
            System.out.println("Starting the stimulator...");
            try {
                System.out.println("Enter the number of restaurants: ");
                maxRestaurants = stdin.nextInt();
                stdin.nextLine();
                if(maxRestaurants < 1)
                    throw new InvalidNumberOfRestaurantsException();
                System.out.println("Enter the max number of customers a restaurant can serve: ");
                maxCustomerSize = stdin.nextInt();
                if(maxCustomerSize < 1)
                    throw new InvalidNumberOfCustomersException(){};
                stdin.nextLine();
                System.out.println("Enter the arrival probability of a customer: ");
                arrivalProb = stdin.nextDouble();
                stdin.nextLine();
                if(arrivalProb < 0 || arrivalProb >= 1)
                    throw new InvalidArrivalProbabilityException();
                System.out.println("Enter the number of chefs: ");
                chefs = stdin.nextInt();
                stdin.nextLine();
                if(chefs < 1)
                    throw new InvalidNumberOfChefException();
                System.out.println("Enter the number of stimulation units: ");
                duration = stdin.nextInt();
                stdin.nextLine();
                double averageTime = dining.stimulate(maxRestaurants, maxCustomerSize, arrivalProb, chefs, duration);
                System.out.println("\nStimulation ending...");
                System.out.println("Total Customer Time: " + totalServiceTime + " minutes");
                System.out.println("Total Customers Served: " + customersServed);
                System.out.println("Average Customer Time Lapse: " + averageTime + " minutes");
                System.out.println("Total Profit: $" + profit);
                System.out.println("Customers that left: " + customersLost + '\n');
                String another = "";
                while(!another.equals("Y") || !another.equals("N")) {
                    System.out.println("Do you want to try another simulation? (y/n): ");
                    another = stdin.nextLine().toUpperCase();
                    if (another.equals("Y")){
                        break;
                    }
                    else if(another.equals("N")){
                        quit = true;
                        break;
                    }
                    else
                        System.out.println("Invalid input");
                }

            }catch (InvalidArrivalProbabilityException ex) {
                System.out.println("Please enter a value between 0 and 1 (excluding 0 and 1)");
            }catch (InvalidNumberOfChefException ex) {
                System.out.println("There must be at least 1 chef");
            }catch (InvalidNumberOfRestaurantsException ex){
                System.out.println("There must be at least 1 restaurant");
            }catch (InvalidNumberOfCustomersException ex) {
                System.out.println("The restaurant should be able to serve at least 1 customer");
            }catch (InputMismatchException ex){
                System.out.println("Invalid input");
                stdin.nextLine();
            }
        }while(!quit);
        System.out.println("Program terminating normally...");
    }
}

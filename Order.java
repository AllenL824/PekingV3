package pekingv3;
import java.util.HashMap;
import java.util.Map;
/**
 * Write a description of class Order here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Order
{
    private Map<MenuItem, Integer> items; // creates a dictionary that matches specific dish with amount of orders of that specific dish
    private double total;
    private double tax;

    //constructor or order class
    public Order()
    {
        //initiate items
        items = new HashMap<>();
        total = 0;
    }

    //method to add item to order
    public void addItem(MenuItem item, int quanity)
    {
        //if order already contains item, add new quantity to existing
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + quanity);
        }
        //else, set as new key and value
        else {
            items.put(item, quanity);
        }
        total += item.getPrice() * quanity;
    }
    
    //method to remove item from order
    //remove section if doesn't function
    public void removeItem(MenuItem item, int quantity)
    {
        //if order already contains item, remove the item if the given quantity is greater than the original quantity
        if (items.containsKey(item)) {
            if (quantity > items.get(item)) {
            total -= item.getPrice() * items.get(item);
            items.remove(item);
            }
        //else, set as new key and value
        else {
            items.put(item, items.get(item) - quantity);
            total -= item.getPrice() * quantity;
        }
        }
        else {
        	System.out.println("Item is not in order.");
        }
    }
    
    public void displayOrder ()
    {
    	for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            MenuItem item = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%s x %d = $%.2f%n", item.getItemName(), quantity, item.getPrice() * quantity);
        }
        
        tax = total * (0.06);
        System.out.printf("Tax = $%.2f%n", tax);
        System.out.printf("Total = $%.2f%n", (total + tax));
    }
    
    public double getTotal()
    {
        return total;
    }
}

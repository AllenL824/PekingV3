package pekingv3;


/**
 * Write a description of class MenuItem here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MenuItem
{
    private String itemNumber;
    private String itemName;
    private double price;

    /**
     * Constructor for objects of class MenuItem
     */
    public MenuItem(String itemNumber, String itemName, double price)
    {
        this.itemNumber = itemNumber;
        this.itemName = itemName;  
        this.price = price;
    }

    public String getItemNumber ()
    {
        return itemNumber;
    }
    
    public String getItemName ()
    {
        return itemName;
    }
    
    public double getPrice ()
    {
        return price;
    }
    
    public String toString ()
    {
        return itemNumber + " - " + itemName + " - $" + price;
    }
    }
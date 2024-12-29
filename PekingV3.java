package pekingv3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * order processing application for my family's business
 *
 * @Allen Lin
 * @version 1
 */
public class PekingV3
{
	public static ArrayList<MenuItem> convertFileToArrayList(String fileName) throws FileNotFoundException{
		//read from Menu file and Initialize items in ArrayList
        ArrayList<MenuItem> menu = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
        String item = scanner.nextLine().trim();
        String[] aspects = item.split(",\\s*");
        
        if (aspects.length == 3) {
        	String itemNumber = aspects[0].trim();
            String itemName = aspects[1].trim();
            double price = Double.parseDouble(aspects[2].trim());
        menu.add(new MenuItem(itemNumber, itemName, price));
        }
        }
        scanner.close();
        return menu;
	}
	
	public static void printArrayList(ArrayList<MenuItem> menu) {
		for (MenuItem item : menu) {
			System.out.printf("%s - %s - %.2f %n", item.getItemNumber(), item.getItemName(), item.getPrice());
		}
		
	}
	
    public static void main(String[] args) throws FileNotFoundException{
        //Display the menu
    	ArrayList<MenuItem> menu = convertFileToArrayList("src/Menu");
    	Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, welcome to Peking restaurant!");
        System.out.println("Would you like to view our menu? Enter yes or no.");
        String viewMenu = scanner.nextLine();
        if (viewMenu.toLowerCase().contains("yes")) {
        System.out.println("Here is our menu:");
        printArrayList(menu);
        }
        
        //process order
        System.out.println("Would you like to place an order? Enter yes or no.");
        String answer = scanner.nextLine();
        Order order = new Order();
        
        //only when the customer answers yes
        while (answer.toLowerCase().contains("yes")) {
            System.out.println("What would you like? Enter the item number or name. Enter \"done\" to finish order. Enter \"remove\" to remove an order.");
            String enteredItem = scanner.nextLine();
            if (enteredItem.toLowerCase().contains("done")) {
                break;
            }
            //remove this section if doesnt function
            if (enteredItem.toLowerCase().contains("remove")) {
            	while (true) {
            	System.out.println("What would you like to remove? Enter \"done\" to finish removing items.");
            	String removedItem = scanner.nextLine();
            	if (removedItem.toLowerCase().contains("done")) {
                    break;
                }
            	else {
            	System.out.println("How many would you like to remove?");
            	int removedQuantity = scanner.nextInt();
            	scanner.nextLine();
            	MenuItem deletedItem = null;
            	for (MenuItem item : menu) {
            	if (removedItem.toLowerCase().equals(item.getItemNumber().toLowerCase())) {
            	deletedItem = item;
            	order.removeItem(deletedItem, removedQuantity);
            	break;
            	}
            	if (removedItem.replaceAll("\\s", "").toLowerCase().equals(item.getItemName().replaceAll("\\s", "").toLowerCase())) {
                deletedItem = item;
                order.removeItem(deletedItem, removedQuantity);
                break;
                }
            	}
            	}
            	}
            	continue;
            	}
            
            	/*
            	 
            	for (MenuItem item : order.getItems()) {
            	if (!(removedItem.toLowerCase().equals(item.getItemNumber().toLowerCase())) || (enteredItem.replaceAll("\\s", "").toLowerCase().equals(item.getItemName().replaceAll("\\s", "").toLowerCase()))) {
            		System.out.println("This item is not in your order.");
            		break;
            		}
            	*/
         
            MenuItem selectedItem = null;
            for (MenuItem item : menu) {
            	//takes item number or name
            	if (enteredItem.toLowerCase().equals(item.getItemNumber().toLowerCase())) {
                    selectedItem = item;
                    break;
                }
                if (enteredItem.replaceAll("\\s", "").toLowerCase().equals(item.getItemName().replaceAll("\\s", "").toLowerCase())) {
                    selectedItem = item;
                    break;
                }                 
            }
            
            if (selectedItem != null) {
                System.out.println("Enter quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                if (quantity > 0) {
                order.addItem(selectedItem, quantity);
                System.out.println(quantity + " x " + selectedItem.getItemName() + " added to your order.");
                } else  {
                   System.out.println("Invalid quantity."); 
                }
            } else {
                System.out.println("Invalid menu item.");
            }		
        }
        System.out.println("Receipt:");
        order.displayOrder();
        System.out.println("Thank you!");
        scanner.close();
                  
    }
}



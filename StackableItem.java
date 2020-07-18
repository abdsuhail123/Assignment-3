import java.util.ArrayList; // Importing ArrayList class
public class StackableItem extends Item { 
    private String name ;
    private int currentItems ;
    private int maxItems;

    // Does not specify name
    // Setting default values
    public StackableItem(){
        currentItems = 0;
        maxItems = 5;
    }

    // Specifies name
    // Setting default values 
    public StackableItem(String newName){
        name = newName;
        currentItems = 0;
        maxItems = 5;
    }

    //Specifices name and values
    public StackableItem(String newName, int itemCount, int maxItemCount){
        name = newName;
        currentItems = itemCount;
        maxItems = maxItemCount;
    }

    //toString method
    @Override
    public String toString() // overrides toString in Item
    {
        return name + " " + "[" + currentItems + "/" + maxItems + "]";
    }

    // Accessor method for current item number
    public int getCount(){
        return currentItems;
    }

    // Accessor method for max item number
    public int getMaxCount(){
        return maxItems;
    }

    // remainingSpace() method calculates number of items that can be added
    public int remainingSpace(){
        int remaining = maxItems - currentItems;
        return remaining;
    }

    public void setItems(int num){
        currentItems += num ;
    }

    // Add number of current items
    public boolean addItems(int numToAdd){
        int remainingSpace = remainingSpace(); // Checking space remaining
        if(numToAdd <= remainingSpace && numToAdd >= 0){ // Making sure number not invalid
            currentItems = currentItems + numToAdd; // Add number to item number
            return true; // Return True
        }
        else{
            return false; // False if number to add invalid
        }
    }

    // Remove number of current items
    public boolean removeItems(int numToRemove){
        if(numToRemove <= currentItems && numToRemove >= 0){ // Making sure number is valid
            currentItems = currentItems - numToRemove; // Remove number from item number
            return true; // Return true
        }
        else{
            return false; // False if number to remove invalid
        }
    }
}

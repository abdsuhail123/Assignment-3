import java.util.ArrayList; // Importing ArrayList class

// Dylan Fries 2020
// ItemList is a partially full array of Item Objects. 
public class ItemList{

    private ArrayList<Item> items; // array of items
    private int currentIndex = 0; // current index of items
    private int maxSize = 100; // max number of Item object in items

    private boolean DEBUG_MODE = false;

    // Default constructor
    // set max array size, create array
    public ItemList(){
        items = new ArrayList<Item>();
        currentIndex = 0;
    }
    
    // Count no. of items added
    public int itemCount(){
        return currentIndex;
    }

    // Add the next item to the array
    public void addItem(Item newItem){

        if(DEBUG_MODE)
            System.out.println("DEBUG ItemList: Adding new item " + newItem.toString() + " to the item list at index " + currentIndex);

        items.add(newItem);
        currentIndex ++; // add to array then update

    }

    // Print the whole item list as a String
    // formatting and returning all the strings
    public String toString(){

        String list = "[";		

        if(currentIndex == 0){
            return "[EMPTY]";
        }

        // Note you are using current Index not max here. 
        for(int i = 0; i < currentIndex-1; i++){
            list += items.get(i).toString() + ", ";
        }

        // handle the end of the list
        list+= items.get(currentIndex-1).toString() + "]";

        return list;
    }

    // containsItem() method checks if specified item exists in ArrayList
    public boolean containsItem(Item doesContain){
        boolean returnValue = false; // Initializing our return value

        if(items.contains(doesContain)){ // If list contains item
            returnValue = true; // Return value is true
            //System.out.println("true");
        }
        //else{
        //System.out.println("false");
        //}

        return returnValue;
    }
    
    // findItem() method finds specified item in the ArrayList
    public Item findItem(Item toFind){
        int index = items.indexOf(toFind); // Searching for index of item
        if(index != -1){ // Making sure item found (indexOf didn't return -1) 
            return items.get(index); // Returning reference to item in list
        }
        else{
            return null; // Return Null if item not found
        }
    }
    
    
    // removeItem() method removes specified item from ArrayList if found
    public Item removeItem(Item toRemove){ // 
    int index = items.indexOf(toRemove); // Searching for index of item
        if(index != -1){ // Making sure item found (indexOf didn't return -1)
            items.remove(toRemove); // Removing item from ArrayList
            return toRemove; // Returning Removed Item
        }
        else{
            return null; // If item not found, return Null
        }
    }
}
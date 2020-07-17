import java.util.ArrayList;
// Dylan Fries 2019
// The Item Class represents a single Item with a randomly generated name
public abstract class Item{
	
	private String itemName;

	public Item(){
		itemName = RandomGenerator.getRandomItemName();
	}

	// An overloaded constructor that accepts a custom item name. 
	// This may be useful in debugging to ensure the ItemList is working as expected.
	public Item(String newName) {
		itemName = newName;
	}

	// returns the itemName. Don’t add additional formatting here.
	public String toString() {
		return itemName;
	}
	
	public boolean equals(Object itemO){
	    boolean returnValue = false;
	    
	   if(itemO instanceof Item){
	   Item newItem = (Item) itemO ;
	   if(newItem.itemName.equals(this.itemName)){
	       returnValue = true;
	   }
	   }
	   return returnValue;
	   }
}
// An example of how to test the StackableItem. 
public class TestItems{
	
	public static void main(String[] args){
		
/*
		// This one is given as an example. 
		runItemTests1();

		// you finish these: 
		runItemTests2();
		runItemTests3();

		testItemList();

		testStackableItemBasic();
		testItemListStackables(); // Testing the StackableItem mechanics. 
*/
		// To see what happens when you call an abstract object, uncomment the code in this method. 
		isItemAbstract();

	}

	// Demonstrating what happens if you make an Object abstract
	public static void isItemAbstract(){
		
		// TestItems.java:8: error: Item is abstract; cannot be instantiated
        Item i = new Item(); // This should throw an error when compiling
 		System.out.println("This should Not be reached if Item is abstract!");                        
	}

		/*
	public static void runItemTests1(){

		System.out.println("\n --- Start Item tests1 --- \n");


		StackableItem defaultConstItem = new StackableItem();

		System.out.println("Test Sequence: defaultConstItem  Item Created: " + defaultConstItem.toString());

		System.out.println(defaultConstItem.toString() + " should include the count [0/10]");

		if(defaultConstItem.getCount() == 0 && defaultConstItem.getMaxCount() == 10 && defaultConstItem.remainingSpace() == 10){
			System.out.println("Item defaultConstItem test #1: Item counts are Correct after constructor. ");
		}



		// -- test methods that should fail -- 
		// Edit the item and then test the counts again. I always like to add, remove, then add again. 
		// Lots of times the index / count gets off when removing but doesn't show up until you start changing it again. 
		if(defaultConstItem.removeItems(1)){
			System.out.println("Error: Item defaultConstItem Test #2 - this should be false (there is 0 and we are removing 1)");
		}
		System.out.println(defaultConstItem.toString() + " Should be [0/10]");

		if(defaultConstItem.addItems(11)){
			System.out.println("Error: Item defaultConstItem Test #3 - this should be false (there is 0, max 10 and we are adding 11)");	
		}
		System.out.println(defaultConstItem.toString() + " Should be [0/10]");
		// === test methods that should work ===
		// -- Adding values --
		// Add 10 to the stack
		if(!defaultConstItem.addItems(10) ){
			System.out.println("Error: Item defaultConstItem Test #4 - Problems adding to the list (or with the current count)");	
		}
		System.out.println(defaultConstItem.toString() + " Should be [10/10]");

		if(defaultConstItem.getCount() != 10){
			System.out.println("Error: Item defaultConstItem Test #5 - Count should be 10");	
		}
		System.out.println(defaultConstItem.toString() + " Should be [10/10]");
		
		// -- removing values --
		if(!defaultConstItem.removeItems(10)){
			System.out.println("Error: Item defaultConstItem Test #6 - Error removing items");	
		}
		System.out.println(defaultConstItem.toString() + " Should be [0/10]");

		if(defaultConstItem.getCount() != 0){
			System.out.println("Error: Item defaultConstItem Test #7 - Count should be 0");	
		}

		// Just adding some different values to make sure it is working.
		defaultConstItem.addItems(1);
		System.out.println(defaultConstItem.toString() + " Should be [1/10]");
		defaultConstItem.addItems(3);
		System.out.println(defaultConstItem.toString() + " Should be [4/10]");
		defaultConstItem.addItems(6);
		System.out.println(defaultConstItem.toString() + " Should be [10/10]");
		defaultConstItem.removeItems(7);
		System.out.println(defaultConstItem.toString() + " Should be [3/10]");
		
		// Testing Negative Values. 
		if( defaultConstItem.addItems(-1)){
			System.out.println("Error: Add Should not work for negative values.");
		}
		System.out.println(defaultConstItem.toString() + " Should be [3/10]");
		if( defaultConstItem.removeItems(-1)){
			System.out.println("Error: Add Should not work for negative values.");
		}

		System.out.println(defaultConstItem.toString() + " Should be [3/10]");

		System.out.println("\n --- Testing Complete for : " + defaultConstItem.toString() + " --- \n");

	}

// [ ] Do tests for these Constructors. 
	public static void runItemTests2(){

		
		StackableItem givenNameItem = new StackableItem("Health Potions");	
	}

	public static void runItemTests3(){
		StackableItem setNumberItem = new StackableItem("Mana Potions", 3, 7);
	}

	public static void testItemList(){
		
		System.out.println("\n --- Start ItemList tests1 --- \n");

		ItemList items = new ItemList();
		EquipableItem item1 = new EquipableItem("Super Guitar", "mental",1);
		StackableItem item2 = new StackableItem("Mega Drum Kit",5,7);
		EquipableItem item3 = new EquipableItem("Gravity Bass", "physical",2);
		EquipableItem item4 = new EquipableItem("Super Guitar", "mental",1);

		items.addItem(item1);
		items.addItem(item2);
		items.addItem(item3);

		System.out.println(items.toString());

		System.out.println("Finding Test 1 - Should be TRUE");
		System.out.println(items.findItem(item1));

		System.out.println("Finding Test 2 - Should be TRUE");
		System.out.println(items.findItem(item4));

		System.out.println("\n --- End ItemList tests1 --- \n");


	}

	public static void testStackableItemBasic(){
		StackableItem stack1 = new StackableItem("Red Potion", 3,8);
		StackableItem stack2 = new StackableItem("Red Potion", 2,8); // added 8/8
		StackableItem blueStack = new StackableItem("Blue Potion", 1,16); // added 10/8, shoudl add a new Item. 

		System.out.println("Should be TRUE: stack1.equals(stack1)");
		System.out.println(stack1.equals(stack1));

		System.out.println("Should be TRUE: stack1.equals(stack2)");
		System.out.println(stack1.equals(stack2));

		System.out.println("Should be FALSE: stack1.equals(blueStack)");
		System.out.println(stack1.equals(blueStack));


	}

	public static void testItemListStackables(){

		System.out.println("\n --- Starting Stackable ArrayList Item Test --- \n");

		ItemList items = new ItemList();
		StackableItem stack1 = new StackableItem("Red Potion", 3,8);
		StackableItem stack2 = new StackableItem("Red Potion", 3,8); // added
		StackableItem stack3 = new StackableItem("Red Potion", 2,8); // added 8/8
		StackableItem stack4 = new StackableItem("Red Potion", 2,8); // added 8/8
		StackableItem blueStack = new StackableItem("Blue Potion", 1,16); // added 10/8, shoudl add a new Item. 

		System.out.println("Initial ArrayList: " + items.toString());

		items.addItem(stack1);
		System.out.println("Test stack1: Output Should be \"Red Potion: [3/8]\"");
		System.out.println(items.toString());

		items.addItem(stack2);
		System.out.println("Test stack2: Output Should be \"Red Potion: [6/8]\"");
		System.out.println(items.toString());

		// This should still fit. It is good to check the edges (such as 0/8 or 8/8) as that is where most common errors occur. 
		items.addItem(stack3);
		System.out.println("Test stack3: Output Should be \"Red Potion: [8/8]\"");
		System.out.println(items.toString());

		// This one will be forced into a new Stack
		items.addItem(stack4);
		System.out.println("Test stack4: Output Should be \"Red Potion: [8/8], Red Potion: [2/8]\"");
		System.out.println(items.toString());

		// Add a different Item
		items.addItem(blueStack);
		System.out.println("Test stack5: Output Should be \"Red Potion: [8/8], Red Potion: [2/8], Blue Potion: [1/16]\"");
		System.out.println(items.toString());

		System.out.println(" --- Ending Stackable ArrayList Item Test --- ");

	}
*/

}
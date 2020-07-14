import java.awt.event.*; // required for input KeyCode

/*
This class will be our main gameplay loop (for now). 
It will later include a turn counter (turn based) and listen for input from the player. 
Think of simulation type games such as the Sims. This is a very simple version. 
We will increase the complexity later.
*/
public class GameController{

	// Its a party, only simulated. Generate some guests, assign them to mingle, then print them out in the end (including who they talked to). 
	public static void main(String[] args){

		// P3
		// What kind of game? 
		// What is the point? 
		// what are the rules? 

		// [ ] Load these from files. 
		
		Hero theHero = FileLoader.loadHero("heroFile.txt");

		/*
		Hero theHero = new Hero("Dylan", "The Instructor", 0,12);
		theHero.setSkills(3,5,4);
		*/
		System.out.println("New Hero Created!");
		System.out.println(theHero.fullToString());
		
		// Array of encounters
		// I just left this here as demonstration of how much code we replaced with one call to the FileLoader class. 
		/*
		Encounter[] events = new Encounter[3];
		events[0] = new Encounter("mental",7,new Item(), 2);
		events[0].setDescriptions("Chess","A chess game."," takes the opponents King.", " loses terribly");

		events[1] = new Encounter("physical",5, new Item(), 3);
		events[1].setDescriptions("Space Jam", "Aliens challenge you to a game of Basketball."," dunks on the aliens to win and save the galaxy.", " don't Quit your day job.");

		events[2] = new Encounter("social",3, new Item(), 3);
		events[2].setDescriptions("A Dinner Party","You have been invited to a fancy dinner party"," tells many funny jokes and people have a good time.", " spills dessert on the hostesses new rug.");
		*/
		Encounter[] encounters = FileLoader.loadEncounters("encountersFile.txt");

        Map mapTest = FileLoader.loadMap("mapFile.txt");
        
        mapTest.initHeroLocation(); // set the map to track the hero position

        System.out.println(mapTest.toString());

		int encountersFound = 0;

		while(theHero.checkStatus() && encountersFound < encounters.length){
			// wait for input
			// Up Arrow
			int input = getInput(); // get input. 
			if(input >= 0){ // valid input to move. 

				// Takes the input code and if it is possible apply the character move
				// After the move, get the code of the tile you moved onto. 
				char tileCode = mapTest.processInput( input); // map to direction
				
				// check if it is a valid encounter. 
				if(Character.isDigit(tileCode)){
					int encounterId = Character.getNumericValue(tileCode);
					
					if( encounterId >= 0 && encounterId < encounters.length){
						Encounter e = encounters[encounterId];

						nextEncounter(theHero, e);
						encountersFound++;
					}else{
						System.out.println("Empty Tile found");
					}
				}

				// How do we track the Hero location? 
				// In this case I stored it in Map so the Map can properly output it, but it is awkward there. 
				
				// Print the updated map
				System.out.println(mapTest.toString());
			}

			StdDraw.show(50);
		}

		endGame(theHero);
		System.exit(0); // shut java down. 
	}

	// Provided:
	// Listens for input from the player and returns an input code. 
	private static int getInput(){
		if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
			return 0;
		}else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
			return 1;
		}else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
			return 2;
		}else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
			return 3;
		}
		return -1; // default, no input. 
	}

	// True means the player won. Apply the damage or reward within this method as well. 
	private static void nextEncounter(Hero hero, Encounter event){
		System.out.println("--------------------------------------");
		System.out.println(event.getDescription());

		boolean heroPassed = hero.skillCheck(event.getStatToCheck(), event.getDifficulty());
	
		if(heroPassed){

			System.out.println(hero.getName() + event.getWinning());

			// Hero Succeeds
			Item temp = event.removeReward();
			System.out.println(hero.getName() + " found a " + temp.toString() + " in the " + event.getName());
			hero.addItem(temp);
			

		}else{

			System.out.println(hero.getName() + event.getLosing());
			// Hero fails. 
			hero.addStress(event.getRisk());
			System.out.println("Our Hero " + hero.getName() + " gained " + event.getRisk() + " stress from their loss at " + event.getName());

		}

		//return heroPassed;
	}

	private static void endGame(Hero finalHero){
		System.out.println("--------------------------------------");
		if(finalHero.checkStatus()){
			System.out.println(finalHero.getName() + " was successful!");
		}else{
			System.out.println(finalHero.getName() + " passed out from stress!");
		}

		System.out.println(" === Final Output === ");
		System.out.println(finalHero.fullToString());
		System.out.println("Thank you for playing. The End");
	}
}
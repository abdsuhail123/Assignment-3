import java.util.Scanner;
import java.io.*; // IO and Exceptions

public class FileLoader_Alternative{

    private static boolean DEBUG_MODE = true;

    // Test methods
    public static void main(String[] args){

        // Handling all Exceptions at the top level and just making
        // the methods "throw" exceptions is a good strategy for organizing your code
        // Note the methods are all simpler and the top level logic is clear enough
        // That is doesn't really matter that we also have the try/catch block. 
        try{

            // -- Hero Loading --
            // Load a hero from file. 
            Hero testHero = loadHero("heroFile.txt");
            // Print the hero
            System.out.println(testHero.fullToString() + '\n');

            // -- Map Loading from file --
            // Load the map from file. 
            Map mapTest = loadMap("mapFile.txt");
            // Output for testing. 
            System.out.println(mapTest.toString());

            // -- Encounters --
            // Create the Encounter array variable and load the Encounters array from file. 
            // Note loadEncounters returns a completed array of Encounters. 
            Encounter[] encounters = loadEncounters("encountersFile.txt");            
            // Iterate all Encounters and output them. 
            for(int i = 0; i < encounters.length; i++){
                System.out.println(encounters[i].fullOutput());
            }

        //Plus, EVERY Exception will now be caught here by one of these catch blocks. 
        }catch(IOException ioe){
            System.out.println(ioe.toString());
            ioe.printStackTrace();
        }catch(Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }

        
    }
    
    // Load all people contained in the Hero file as a Hero Object. 
    public static Hero loadHero(String heroFile) throws Exception{
        Hero ourHero = null;
      
        BufferedReader buffStream = new BufferedReader(new FileReader(heroFile));
        String line = buffStream.readLine();
        Scanner scan = new Scanner(line);

        // -- Name --
        String name = scan.next();
        
        // -- Description --
        line = buffStream.readLine();
        String desc = line;

        // -- Stress -- 
        line = buffStream.readLine();
        scan = new Scanner(line);
        // Parse stress variables. 
        int stress = scan.nextInt();
        int max = scan.nextInt();

        // -- Skills --
        line = buffStream.readLine();
        scan = new Scanner(line);
        // Parse the skill values. 
        int skillPhys = scan.nextInt();
        int skillMental = scan.nextInt();
        int skillSocial = scan.nextInt();

        // -- Hero Object--
        ourHero = new Hero(name, desc, stress, max);
        ourHero.setSkills(skillPhys, skillMental, skillSocial);

        return ourHero;
    }

    // Load a give map from a file. It should have two ints on the first line, a 
    // blank line, then a map comprised of chars. 
    // . -> empty tile
    // X -> wall/full tile
    // 1/2/3/# -> encounter
    // H -> Hero
    public static Map loadMap(String fileName) throws Exception{
        Map newMap = null;
       
        BufferedReader buffStream = new BufferedReader(new FileReader(fileName));
        Scanner scan = new Scanner (buffStream.readLine());
        int rows = scan.nextInt();
        int cols = scan.nextInt();

        newMap = new Map(rows, cols);

        // Skip blank line
        buffStream.readLine();

        char temp = '.';

        for(int r = 0; r < rows; r++){
            String line = buffStream.readLine();

            for(int c = 0; c < cols; c++){
                temp = line.charAt(c);
                newMap.setTile(r,c, temp);
            }
        }
       
        return newMap;
    }

    // Load all Encounters defined in the given file. 
    public static Encounter[] loadEncounters(String fileName) throws Exception{
        
        Encounter[] encounters = new Encounter[0];
        // Note: Hack version with no error checking
        
        BufferedReader buffStream = new BufferedReader(new FileReader(fileName));
        String line = buffStream.readLine();

        // First line tells us how many Encounters there are in the file. 
        Scanner scan = new Scanner(line);
        int count = scan.nextInt();

        // Which determines the size of the array 
        encounters = new Encounter[count];

        // load
        // Skip blank line
        buffStream.readLine();
        
        // --- One encounter. --- 
        // first line of the encounter. 
        for(int i = 0; i < count; i++){
            encounters[i] = loadOneEncounter(buffStream);
        }

        // grab the line 
        
        return encounters;
    }

    // I did this one by returning the Exception from the method for demonstration. 
    // Note in this case, loadOneEncounter will return the Exception to the loadEncounters method, 
    // Which will in turn return it to the main method. 
    private static Encounter loadOneEncounter(BufferedReader buffer) throws Exception{
        // first line
        String line = buffer.readLine();
        Scanner scan = new Scanner(line);
        // will be the id
        int id = scan.nextInt();
        // second line = name
        scan = new Scanner(buffer.readLine());
        String name = scan.next();

        // difficulty and risk
        scan = new Scanner(buffer.readLine());
        int diff = scan.nextInt();
        int risk = scan.nextInt();
        // Skills
        scan = new Scanner(buffer.readLine());
        String skillToUse = scan.next();

        // Item
        scan = new Scanner(buffer.readLine());
        String itemName = scan.next();
        Item reward = new Item(itemName);

        Encounter newEncounter = new Encounter(skillToUse, diff, reward, risk);
        newEncounter.setId(id);

        String desc = buffer.readLine();
        String winMsg = buffer.readLine();
        String loseMsg = buffer.readLine();

        newEncounter.setDescriptions(name, desc, winMsg, loseMsg);

        // Skip past the blank line at the end. 
        buffer.readLine();
        
        return newEncounter;
    }
    
    // Then establish file connection to load People

}
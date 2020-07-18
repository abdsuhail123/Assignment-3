import java.util.Scanner;
import java.io.*; // IO and Exceptions

public class FileLoader{

    private static boolean DEBUG_MODE = true;

    // Test methods
    public static void main(String[] args){

        Hero testHero = loadHero("heroFile.txt");
        System.out.println(testHero.fullToString() + '\n');

        Map mapTest = loadMap("mapFile.txt");
        System.out.println(mapTest.toString());

        Encounter[] encounters = loadEncounters("encountersFile.txt");

        for(int i = 0; i < encounters.length; i++){
            System.out.println(encounters[i].fullOutput());
        }
    }

    // Load all people contained in the Hero file as a Hero Object. 
    public static Hero loadHero(String heroFile){
        Hero ourHero = null;
        try{
            BufferedReader buffStream = new BufferedReader(new FileReader(heroFile));
            String line = buffStream.readLine();
            Scanner scan = new Scanner(line);

            // Name
            String name = scan.next();

            // Description
            line = buffStream.readLine();
            String desc = line;

            // Stress
            line = buffStream.readLine();
            scan = new Scanner(line);
            int stress = scan.nextInt();
            int max = scan.nextInt();

            // Skills
            line = buffStream.readLine();
            scan = new Scanner(line);
            int skillPhys = scan.nextInt();
            int skillMental = scan.nextInt();
            int skillSocial = scan.nextInt();

            ourHero = new Hero(name, desc, stress, max);
            ourHero.setSkills(skillPhys, skillMental, skillSocial);

            // Catch any kind of exception
        }catch(Exception e){
            System.out.println("Failed Reading from file" + e.toString());
            System.out.println("Filename Given: " + heroFile);
            e.printStackTrace();
        }

        if(ourHero == null){
            ourHero = new Hero();
            System.out.println("Hero didn't load so create a default Hero");
        }

        return ourHero;
    }

    // Load a give map from a file. It should have two ints on the first line, a 
    // blank line, then a map comprised of chars. 
    // . -> empty tile
    // X -> wall/full tile
    // 1/2/3/# -> encounter
    // H -> Hero
    public static Map loadMap(String fileName){
        Map newMap = null;
        try{
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
        }catch(Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return newMap;
    }

    // Load all Encounters defined in the given file. 
    public static Encounter[] loadEncounters(String fileName){

        Encounter[] encounters = new Encounter[0];
        // Note: Hack version with no error checking
        try{
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
        }catch(Exception e){
            System.out.println("Failed Reading from file" + e.toString());
            System.out.println("Filename Given: " + fileName);
            e.printStackTrace();
        }

        return encounters;
    }

    // I did this one by returning the Exception from the method for demonstration. 
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
        EquipableItem reward = new EquipableItem(itemName,RandomGenerator.getStatToCheck(),RandomGenerator.randomRoll(1,6));

        // *** I am skipping a line here so that it works with the UPDATED A3 file provided
        // Your A2 version should not have this
        // Also, HINT: A3

        buffer.readLine();

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
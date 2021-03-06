import java.util.ArrayList; // Importing ArrayList class
import java.util.ArrayList; // Importing ArrayList class
import java.util.ArrayList; // Importing ArrayList class

// A Hero represents (as you might expect) a single Hero. There should 
// only ever be one instance of each Hero at a time. 
public class Hero{

    // Hero Stats
    private String name; // Name of the Hero
    private String description; // Description of the Hero. 
    // Hero State
    private int currentStress;
    private int stressLimit; // reach this and you are done. Pass out. 
    private boolean isAwake = true; // if hp = 0, pass out. 

    // Stats - min 1, max 5
    // make up your own skills if you want
    private int physicalSkill; // strength, agility, constitution
    private int mentalSkill; // intelligence, wisdom, mental sharpness
    private int socialSkill; // charisma, empathy and other group skills

    //	private String[] traits; // Like tags, can have other abilities. 

    private ItemList inventory; // All Items collected so far

    // ******* Initialization *******
    public Hero(){
        name = "Default";
        description = "None Given";
        currentStress = 0;
        stressLimit = 10;
        inventory = new ItemList();

    }
    // basic constructor.
    public Hero(String newName, String newDescription, int currentStress, int stressLimit){
        name = newName;
        description = newDescription;

        // stress
        this.currentStress = currentStress;
        this.stressLimit = stressLimit;

        // set up items. 
        inventory = new ItemList();
    }

    // Option B1:
    // Could also be done in the constructor if you wanted. 
    public void setSkills(int newPhysical, int newMental, int newSocial){
        physicalSkill = newPhysical; // strength, agility, constitution
        mentalSkill = newMental; // intelligence, wisdom, mental sharpness
        socialSkill = newSocial;
    }

    // Return only the name for now. 
    public String toString(){
        return name;
    }

    // Return the name
    public String getName(){
        return name;
    }

    // "Accessor" method for description
    public String getDescription(){
        return description;
    }

    // include description and skills as well. 
    public String profileToString(){
        return name + "\n" + description 
        + "\n - Stress: " + currentStress + "/" + stressLimit;
    }
    // Return the skills only. 
    public String skillsToString(){
        return " - physical: " + physicalSkill
        + "\n - mental:" + mentalSkill
        + "\n - social:" + socialSkill;
    }

    // Items
    public void addItem(Item newItem){
        inventory.addItem(newItem);
    }

    public String allItems(){
        return inventory.toString();
    }

    // Name, Description, all Skills and All Items. 
    public String fullToString(){
        return profileToString() + "\n" + skillsToString() + "\n" + allItems();
    }

    // Alternate way of assigning variables, using "this" keyword
    // "Mutator" method for description
    // For now I am overwriting the old description. This could be improved
    // by allowing for appending. 
    public void setDescription(String description){
        this.description = description;
    }

    // Checks a skill against the stat of the Hero.
    // If the value is less then the given skill plus a random value between 1-4. return true 
    // Use strings for now to check type
    // "mental", "physical", "social"
    // True means you passed. 
    
    // Integrated Method
    // To add boost points for each skill in Hero's stats
    // Skills only in EquipableItems
    public boolean skillCheck(String skillType, int value){
        boolean outcome = false;
        int baseRoll = RandomGenerator.randomRoll(1,4);
        // System.out.println(" - DEBUG: RANDOM ROLL: "+ baseRoll);
        ArrayList<EquipableItem> list = new ArrayList<EquipableItem>();
        list = inventory.getEquipableItem();
        for(int i = 0; i < list.size(); i++){
            EquipableItem newItem = list.get(i);
            if(newItem.getSkill().equals("mental")){
                if(value <= mentalSkill + baseRoll){
                    outcome = true;
                    mentalSkill += newItem.getBoost();
                }
            }else if(newItem.getSkill().equals("physical")){
                if(value <= physicalSkill + baseRoll){
                    //System.out.println(" - DEBUG: physicalSkill + baseRoll " + physicalSkill + ' ' + baseRoll);
                    outcome = true;
                    physicalSkill += newItem.getBoost();
                }
            }else if(newItem.getSkill().equals("social")){
                if(value <= socialSkill + baseRoll){
                    outcome = true;
                    socialSkill += newItem.getBoost();
                }
            }else{
                // a character with no skill should check with level 0. 
                // helps with bugs, should not be called. 
                System.out.println("Warning: Unknown skill found: " + skillType);
            }
        }
        return outcome;
    }

    // add negative to remove it
    // this is kind of problematic. 
    public void addStress(int amount){		
        currentStress = currentStress + amount; 
    }

    // Status of true means things are ok to go on
    // status of false means they have passed out. 
    public boolean checkStatus(){
        boolean returnVal = false;
        if(isAwake){

            if(currentStress < stressLimit){
                // Things are OK 
                returnVal = true;
            }else{
                // trigger the pass out. 
                isAwake = false;
            }

        }else{
            // no way to wake up yet

        }
        return returnVal;
    }
}
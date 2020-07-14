public class EquipableItem extends Item{

    private String name;
    private String skillType;
    private int boostPoints;

    // Constructor with no parameters
    public EquipableItem(){
        name = RandomGenerator.getRandomItemName(); // Generate Random name for item
        skillType = RandomGenerator.getStatToCheck(); // Randomly get skill to boost
        boostPoints = RandomGenerator.randomRoll(1,6); //Skill Boost ranging from 1-5
    }

    // Constructor with parameters
    public EquipableItem(String newName, String newType, int newBoost){
        name = newName; // Setting item name as parameter
        skillType = newType; // Setting skill type as parameter
        boostPoints = newBoost; // Setting boost points as parameter
    }

    public String toString() // overrides toString in Item
    {
        return name + " " + "[" + skillType + " " + "+" + boostPoints + "]";
    }
}
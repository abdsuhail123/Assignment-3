
public class ItemListTest
{
    public static void main(String[] args){
    
       ItemList testList = new ItemList();
       EquipableItem I1 = new EquipableItem();
       EquipableItem I2 = new EquipableItem();
       EquipableItem I3 = new EquipableItem();
       EquipableItem I4 = new EquipableItem();
       testList.addItem(I1);
       testList.addItem(I2);
       testList.addItem(I3);
       
       testList.containsItem(I3);
       //System.out.println(testList.toString()) ;
       
       StackableItem S1 = new StackableItem("Gandu",2,7);
       System.out.println(S1.toString());
    }
}

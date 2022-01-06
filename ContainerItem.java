import java.util.ArrayList;

public class ContainerItem extends Item {
    private ArrayList<Item> items;    

    public ContainerItem(String name, String type, String description) {
        super(name, type, description);
        items =  new ArrayList<Item>();
    }

    public void addItem(Item itemAdded){
        items.add(itemAdded);
    }

    public boolean hasItem(String itemName){
        itemName = itemName.toLowerCase();
        for(int i =0 ;i<items.size();i++){
            if(items.get(i).getName().toLowerCase().equals(itemName)){
                return true;
            }
        }
        return false;
    }

    public Item removeItem(String itemName){
        itemName = itemName.toLowerCase();
        for(int i =0 ;i<items.size();i++){
            if(items.get(i).getName().toLowerCase().equals(itemName)){
                Item result = items.get(i);
                items.remove(i);
                return result;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        String temp = "";
        for(int i =0;i<items.size();i++){
            temp += "+ " +items.get(i).getName()+"\n";
        }
        return super.toString() +"\n"+temp;
    }
        
    
}

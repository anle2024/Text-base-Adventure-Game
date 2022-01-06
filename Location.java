import java.util.ArrayList;
import java.util.HashMap;

public class Location{
    private String name;
    private String description;
    private ArrayList<Item> items;
    private HashMap<String, Location> connections;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<Item>();
        connections = new HashMap<String, Location>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void addItem(Item newItem){
        items.add(newItem);
    }

    public boolean hasItem(String itemName){
        itemName = itemName.toLowerCase();
        for(int i =0; i < items.size();i++){
            if(items.get(i).getName().toLowerCase().equals(itemName)){
                return true;
            }
        }
        return false;
    }

    public Item getItem(String itemName){
        itemName = itemName.toLowerCase();
        for(int i =0; i < items.size();i++){
            if(items.get(i).getName().toLowerCase().equals(itemName)){
                return items.get(i);
            }
        }
        return null;
    }

    public Item getItem(int index){
        if(index >= items.size() && index < 0){
            return null;
        }
        return items.get(index);
    }

    public int numItems(){
        return items.size();
    }

    public Item removeItem(String itemName){
        itemName = itemName.toLowerCase();
        for(int i =0; i < items.size();i++){
            if(items.get(i).getName().toLowerCase().equals(itemName)){
                Item temp = items.get(i);
                items.remove(i);
                return temp;
            }
        }
        return null;
    }

    public void connect(String directionName, Location locationConnect){
        directionName = directionName.toLowerCase();
        connections.put(directionName, locationConnect);
    }

    public boolean canMove(String directionName){
        directionName = directionName.toLowerCase();
        return connections.containsKey(directionName);
    }

    public Location getLocation(String directionName){
        directionName = directionName.toLowerCase();
        if(canMove(directionName)){
            return connections.get(directionName);
        }

        return null;
    }
}

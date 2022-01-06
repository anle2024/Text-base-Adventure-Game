import java.util.Scanner;

public class Driver{

    private static Location currLocation;
    private static ContainerItem myInventory;

    public static void createWorld(){
        Location kitchen = new Location("Kitchen", "Where the body is found. The kitchen is to the south of the house.");
        Location hallway =  new Location("Hallway", "There is a long hallway with many scary pictures.");
        Location bedroom = new Location("Bedroom", "With a large and messed bed. There is a photo of his two sons.");
        Location cinema = new Location("Cinema", "Large Cinema for his family.");

        // creating a graph for the house
        currLocation = kitchen;
        hallway.connect("South", kitchen);
        kitchen.connect("North", hallway);
        hallway.connect("West", bedroom);
        bedroom.connect("East", hallway);
        hallway.connect("East", cinema);
        cinema.connect("West", hallway);

        // add items for each location
        hallway.addItem(new Item("Picture", "Object", "Show that the man is very rich."));
        bedroom.addItem(new Item("Footprint", "Evidence", "Belong to the man from 18 to 25."));
        bedroom.addItem(new Item("Shoes", "Object", "A shoes is belong to the big son of the man."));
        cinema.addItem(new Item("Money", "Evidence", "Someone drop his money"));
        kitchen.addItem(new  Item("Body", "Trait", "The dead body of the man"));
        kitchen.addItem(new Item("Blood","Evidence", "To find the killer"));
        kitchen.addItem(new Item("Knife","Weaspon", "To kill someone"));

        // create my personal bag and what I have initially
        myInventory = new ContainerItem("Inventory", "Container", "Your inventory");
        myInventory.addItem(new Item("Water", "Food", "To drink"));
        myInventory.addItem(new Item("Sandwitch", "Food", "To eat"));
        myInventory.addItem(new Item("Glasses", "Object", "To investigate"));

        // check point 3 starts here
        // create container item for some locations
        ContainerItem chest = new ContainerItem("Chest", "Container", "Store treasures and precious antiques");
        chest.addItem(new Item("Bracelet", "Jewelry", "The gold classis bracelet for queens"));
        chest.addItem(new Item("Diamond Sword", "Treasure", "The sharpest sword ever exists"));
        bedroom.addItem(chest);
        ContainerItem desk = new ContainerItem("Desk", "Container", "Where to look for necessary tools and equipment");
        desk.addItem(new Item("Scissor", "Tool", "a tool cutting everything"));
        desk.addItem(new Item("Pen", "Writing Tool", "a pen made from a rare bird feather that never runs out of ink"));
        cinema.addItem(desk);
    }

    public static void main(String[] args) {

        createWorld();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Enter Command");
            String inputsFromUser = sc.nextLine();
            String[] arraysOfInputs = inputsFromUser.split(" ");

            String typeOfCommand = arraysOfInputs[0].toLowerCase();
    
            String parameter = "";
            if(arraysOfInputs.length > 1){
                parameter = arraysOfInputs[1].toLowerCase();
            }

            for (int i = 0; i < arraysOfInputs.length; i++){
                arraysOfInputs[i] = arraysOfInputs[i].toLowerCase();
            }
            
            switch(typeOfCommand){
                case "quit": return;
                case "look":
                    System.out.println(currLocation.getName()+" - "+ currLocation.getDescription()+ " has the following items:");
                    for(int i =0 ; i<currLocation.numItems();i++){
                        System.out.println("+ "+currLocation.getItem(i).getName());
                    }
                    break;
                
                case "examine":
                    if(!parameter.equals("") && currLocation.hasItem(parameter)){
                        Item tempItem = currLocation.getItem(parameter);
                        System.out.println(tempItem.toString());

                        if (tempItem.getDescription().equals("Container")){
                            tempItem = (ContainerItem) tempItem;
                            System.out.println(tempItem.toString());
                        }
                    }
                    else if(!parameter.equals("")){
                        System.out.println("Cannot find that item");
                    }else if (parameter.equals("")){
                        System.out.println("You forgot to provide us the item's name.");
                    }
                    break;
                
                case  "go":
                    if(!parameter.equals("") && currLocation.canMove(parameter)){
                        System.out.println("You are moving to the "+ parameter);
                        currLocation = currLocation.getLocation(parameter);
                    }
                    else if(!parameter.equals("")){
                        System.out.println("Cannot go to this direction");
                    }else if (parameter.equals("")){
                        System.out.println("You forgot to provide us the direction.");
                    }
                    break;

                case "inventory":
                    String result = myInventory.toString();
                    System.out.println(result);
                    break;
                
                case "take":
                    if (arraysOfInputs.length >= 4 && arraysOfInputs[2].equals("from")){
                        // System.out.println("success");
                        String containerName = arraysOfInputs[3];
                        String targetItem = arraysOfInputs[1];

                        if (currLocation.hasItem(containerName)){
                            if (!currLocation.getItem(containerName).getType().equals("Container")){
                                System.out.println("The container name you provided is not a container. It is a mere item");
                                break;
                            }

                            ContainerItem tempContainerItem = (ContainerItem) currLocation.getItem(containerName);

                            if (tempContainerItem.hasItem(targetItem)){
                                Item tempTargetItem = tempContainerItem.removeItem(targetItem);
                                myInventory.addItem(tempTargetItem);
                                System.out.println("The item has been added to your inventory");
                            }else{
                                System.out.println("The item is not at this container");
                            }
                        }else{
                            System.out.println("The container is not in this location or not real");
                        }
                    }
                    else if(!parameter.equals("") && currLocation.hasItem(parameter)){
                        System.out.println("You picked up "+parameter);
                        myInventory.addItem(currLocation.getItem(parameter));
                        currLocation.removeItem(parameter);
                    }
                    else if(!parameter.equals("")){
                        System.out.println("Cannot find that item here");
                    }else if (parameter.equals("")){
                        System.out.println("What item you will take?");
                    }
                    break;
                
                case "drop":
                    if(!parameter.equals("") && myInventory.hasItem(parameter)){
                        System.out.println("You dropped "+parameter);
                        Item drop = myInventory.removeItem(parameter);
                        currLocation.addItem(drop);
                    }
                    else if(!parameter.equals("")){
                        System.out.println("Cannot find that item in your inventory.");
                    }else if (parameter.equals("")){
                        System.out.println("What item you will drop?");
                    }
                    break;
                
                case "help":
                    System.out.println("All the commands.");
                    System.out.println("look: to look into a place and see all the objects.");
                    System.out.println("examine: to examine an object at a place. \n \t\t\t examine _(Object)_");
                    System.out.println("go: to go to another place. \n \t\t\t examine _(Direction- South, North, East, West)_");
                    System.out.println("take: to put an object in backbag from a place. \n \t\t\t take _(Object)_");
                    System.out.println("drop: to drop an object in backbag. \n \t\t\t take _(Object)_");
                    break;
                
                case "put":
                    if (arraysOfInputs.length >= 4 && arraysOfInputs[2].equals("in")){                        
                        if (!myInventory.hasItem(arraysOfInputs[1])){
                            System.out.println("You are currently not having this item. Try again");
                            break;
                        }
                        Item targetItem = myInventory.removeItem(arraysOfInputs[1]);
                        
                        if (currLocation.hasItem(arraysOfInputs[3])){

                            if (!currLocation.getItem(arraysOfInputs[3]).getType().equals("Container")){
                                System.out.println("The container name you provided is actually not a container");
                                break;
                            }

                            ContainerItem containerItemName = (ContainerItem) currLocation.getItem(arraysOfInputs[3]);
                            containerItemName.addItem(targetItem);

                            System.out.println("The item has been added to the container successfully");
                        }else{
                            System.out.println("The container you are looking for isn't at this location or not real");
                        }
                    }else{
                        System.out.println("Your order statement PUT-IN is not a valid statement");
                    }
                    break;
                
                default:
                    System.out.println("I don't know how to do that.");
            }
        }
    }
}
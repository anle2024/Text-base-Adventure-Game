public class Item{
    protected String name;
    protected String type;
    protected String description;

    public Item(String name, String type, String description){
        this.name = name.toLowerCase();
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return this.name + "["+ this.type+  "]: " + this.description;
    }


}
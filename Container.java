package Projekt2;
public class Container extends GameObject{

    private Inventory inventory;
    private boolean locked;

    public Container(String name, boolean moveable, boolean locked, Gui gui){
        super(name,moveable);
        this.inventory = new Inventory(3,gui);
        this.locked = locked;
    }

    public Inventory getInventory(){
        return this.inventory;
    }
    public boolean isLocked(){
        return this.locked;
    }



}
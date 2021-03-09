package Projekt2;

public class Player {

    private String name;
    private Inventory inventory;
    Gui gui;

    public Player(String name, int startRoom, Gui g) {

        this.gui = g;
        this.name = name;
        this.inventory = new Inventory(6,g);

    }
    // rummets inventory
    public Inventory getInventory(){
        return this.inventory;
    }
    public String toString (){
        return this.name + " is carrying " +this.inventory;
    }

}
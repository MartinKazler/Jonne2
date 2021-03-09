package Projekt2;

public class Player {

    private String name;
    private Projekt2.Inventory inventory;
    Projekt2.Gui gui;

    public Player(String name, int startRoom, Projekt2.Gui g) {

        this.gui = g;
        this.name = name;
        this.inventory = new Projekt2.Inventory(6,g);

    }
    // rummets inventory
    public Projekt2.Inventory getInventory(){
        return this.inventory;
    }
    public String toString (){
        return this.name + " is carrying " +this.inventory;
    }

}

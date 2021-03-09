package Projekt2;


public abstract class Npc {
    String name;
    Inventory inventory;
    Gui gui;
    public Npc(String name,Gui g){
        this.gui = g;
        this.name = name;
        this.inventory = new Inventory(1,gui);
    }

    public Inventory getInventory(){
        return this.inventory;
    }
    public String toString (){
        return this.name + " is carrying " +this.inventory;
    }
}
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
    public Npc(int capacity) {
		
	}
	public String getName(){
        return this.name;
    }

    public int getCurrent_room() {
        return getCurrent_room();
    }
    public void fillInventory(){
        this.inventory.setInventory(GameObject.getRandomItems(1));
    }
    public Inventory getInventory(){
        return this.inventory;
    }
    public void switchRoom(int i){
        if(i < 1
                || i > 4
                || Math.abs(getCurrent_room() - i) > 1
                || getCurrent_room() == i){
            return;
        }
    }
    public void forceRoom(int i){
    }
    protected void setName(String name){
        this.name = name;
    }
    public String toString (){
        return this.name + " is carrying " +this.inventory;
    }
}
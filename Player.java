package Projekt2;

public class Player {

    private String name;
    private String thePlayer;
    private Inventory inventory;
    private int position;

    Gui gui;
	private int current_room;

    public Player(String name, int startRoom, Gui g) {

        this.gui = g;
        this.name = name;
        this.thePlayer = name;
        this.position = startRoom;
        this.inventory = new Inventory(6,g);

    }
    public void switchRoom(int i){
        int current = current_room;

        if(i < 1
                || i > 4
                || Math.abs(current - i) > 1
                || current_room == i){
            return;
        }
        this.current_room = i;
    }
    // rummets inventory
    public Inventory getInventory(){
        return this.inventory;
    }
    public int getCurrent_room() {
        return current_room;
    }
    public String toString (){
        return this.name + " is carrying " +this.inventory;
    }

}
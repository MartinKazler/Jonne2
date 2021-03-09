package Projekt2;


public class Person extends Projekt2.Npc implements Runnable{

    private int position;
    public Person(String name, int startRoom, Projekt2.Gui m) {
        super(name, m);
        this.position = startRoom;
    }
    public synchronized void move(){
        int slump = (int) (Math.random() * 4 + 1);
        this.position = slump;
        System.out.println("Moving " + this.name + " to Room " + (this.position) );
    }

    public void takeRandomItemFromRoom(GameObject object) {
        this.getInventory().addObject(object);
        System.out.println(this.name+" took "+object.getName());
    }

    public void dropItemInRoom(GameObject object) {
        getInventory().removeObject(object);
        System.out.println(this.name+" dropped "+object.getName());

    }


    
    public int getPosition() {
        return position;
    }
    
    
    @Override
   public void run(){
   }
}

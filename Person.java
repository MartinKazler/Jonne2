package Projekt2;

public class Person extends Npc implements Runnable{

    private static final int CAPACITY = 5;
	private int position;
    public Person(String name, int startRoom, Gui m) {
        super(name, m);
        this.position = startRoom;
    }
    public synchronized void move(){
        int slump = (int) (Math.random() * 4);
        this.position = slump;
        System.out.println("Moving " + this.name + " to Room " + (this.position + 1) );
    }
    
    
    public int getPosition() {
        return position;
    }
    
    public Person(String npc_name) {
        super(CAPACITY);
        this.setName(npc_name);
    }
    @Override
   public void run(){
        move();
   }
}
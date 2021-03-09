package Projekt2;

import java.util.Arrays;

public class Person extends Npc implements Runnable{

    private int position;
    public Person(String name, int startRoom, Gui m) {
        super(name, m);
        this.position = startRoom;
    }
    public synchronized void move(){
        int slump = (int) (Math.random() * 4) + 1;
        this.position = slump;
        System.out.println("Moving " + this.name + " to Room " + (this.position) );
    }
  
    
    public int getPosition() {
        return position;
    }
    
    @Override
    public void run() {
        while(Game.gameIsOn){
            // Do an npc action for each
            Arrays.stream()
                .forEach(person -> {
                    int action = (int) (Math.random() * 5) + 1;

                    switch(action){
                       
                        case 1:
                            // Pick up some random shit
                            person.map[rumIndex].getInventory().removeObject(gameObject);
                            break;
                        case 2:
                            // Drop some random shit
                        	person.map[rumIndex].getInventory().addObject(gameObject);
                            break;
                            move();
                    }
                });

       
            
        }
    }
}
package Projekt2;

import static java.lang.Thread.sleep;

import java.awt.Color;
import java.util.Arrays;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game {
	private Projekt2.Gui gui;
    private Projekt2.Room room1, room2, room3, room4;
    private Projekt2.Room map[];
    private Projekt2.Person[] persons;

    public Game() {

        room1 = new Projekt2.Room("\nStarting Room",
            " \nLooks kinda dull, gray walls and just a chair on the right corner and a", gui);
        room2 = new Projekt2.Room("\nBlue Room",
            "\nAs the rooms says everything is blue, there is some Smurf dummies spread across the room", gui);
        room3 = new Projekt2.Room("\nYellow Room",
            "\nWell would you look at that everything is Yellow now, Hmmm why do i smell bananas in here?",
            gui);
        room4 = new Projekt2.Room("\nPink Room",
            "\nHmm Pink, i hate that color, Looks really girly in here with all the cotton candy and ...is that ponnies? ",
            gui);

        map = new Projekt2.Room[4];
        map[0] = room1;
        map[1] = room2;
        map[2] = room3;
        map[3] = room4;

        Projekt2.GameObject smurf = new Projekt2.GameObject("smurf", true);
        Projekt2.GameObject lampa = new Projekt2.GameObject("taklampa", false);
        Projekt2.GameObject banana = new Projekt2.GameObject("banana", true);
        Projekt2.GameObject ponny = new Projekt2.GameObject("ponny", true);

        Container box = new Container("BLUE BOX", false, true, gui);

        room1.addObject(null);
        room1.addObject(lampa);
        room1.addObject(box);
        room2.addObject(null);
        room2.addObject(smurf);
        room2.addObject(box);
        room3.addObject(null);
        room3.addObject(banana);
        room3.addObject(box);
        room4.addObject(null);
        room4.addObject(ponny);
        room4.addObject(box);

        Projekt2.Person newPlayer1 = new Projekt2.Person("Ola", 0, gui);
        room1.addNpc(newPlayer1);
        Projekt2.Person jakob = new Projekt2.Person("Jakob", 4, gui);
        room2.addNpc(jakob);

        Projekt2.Person jonte = new Projekt2.Person("Jonte", 1, gui);
        room3.addNpc(jonte);

        Projekt2.Person simon = new Projekt2.Person("Simon", 2, gui);
        room4.addNpc(simon);

        persons = new Projekt2.Person[3];
        persons[0] = jakob;
        persons[1] = jonte;
        persons[2] = simon;

        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(10);
        pool.scheduleAtFixedRate(jakob, 20, 20, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(jonte, 15, 25, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(simon, 10, 30, TimeUnit.SECONDS);

        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Projekt2.Inventory inventory = newPlayer1.getInventory();

        this.gui = new Projekt2.Gui();

        boolean gameon = true;
        int rumIndex = 0;

        while (gameon) {
            try {
                int npcId = (int) (Math.random() * 3);
                performNpcMove(npcId);
            } catch (InterruptedException e){

            }
            String command = gui.getCommand();
            if (!command.equals("-1")) {

                if (command.equals("1")) {
                    if (rumIndex == 1) {
                        rumIndex = 0;
                        gui.panel.setBackground(Color.GRAY);
                    }
                }

                if (command.equals("2")) {
                    if (rumIndex == 0 || rumIndex == 2) {
                        rumIndex = 1;
                        gui.panel.setBackground(Color.BLUE);

                    }

                }
                if (command.equals("3")) {
                    if (rumIndex == 1 || rumIndex == 3) {
                        rumIndex = 2;
                        gui.panel.setBackground(Color.YELLOW);

                    }

                }
                if (command.equals("4")) {
                    if (rumIndex == 2) {
                        rumIndex = 3;
                        gui.panel.setBackground(Color.PINK);
                    }
                }

                if (command.startsWith("take")) {
                    String objectName = command.substring(5);
                    System.out.println(objectName);

                    if (map[rumIndex].getInventory().contains(objectName)) {
                        Projekt2.GameObject gameObject =
                            map[rumIndex].getInventory().returnGameObject(objectName);
                        if (gameObject.isMoveable()) {
                            System.out.println(gameObject.isMoveable());
                            boolean succeses = newPlayer1.getInventory().addObject(gameObject);
                            if (succeses) {
                                map[rumIndex].getInventory().removeObject(gameObject);
                            }
                        }
                    }

                }
                if (command.startsWith("drop")) {

                    if (command.contains("banana")) {
                        map[rumIndex].addObject(banana);
                        newPlayer1.getInventory().removeObject(banana);
                    }
                    if (command.contains("ponny")) {
                        map[rumIndex].addObject(ponny);
                        newPlayer1.getInventory().removeObject(ponny);
                    }
                    if (command.contains("lampa")) {
                        map[rumIndex].addObject(lampa);
                        newPlayer1.getInventory().removeObject(lampa);
                    }
                    if (command.contains("smurf")) {
                        map[rumIndex].addObject(smurf);
                        newPlayer1.getInventory().removeObject(smurf);
                    }
                }

                if (command.equals("slut")) {

                    gui.setMessage("Game over");
                    gameon = false;

                }

            }

            gui.setShowPlayer(newPlayer1, map[rumIndex], rumIndex);
            gui.setShowInventory(inventory);

            gui.setShowRoom1("\n " + map[0]);
            gui.setShowPlayer(newPlayer1, map[rumIndex], rumIndex);

            gui.setShowRoom2("\n " + map[1]);
            gui.setShowPlayer(newPlayer1, map[rumIndex], rumIndex);

            gui.setShowRoom3("\n " + map[2]);
            gui.setShowPlayer(newPlayer1, map[rumIndex], rumIndex);

            gui.setShowRoom4("\n " + map[3]);
            gui.setShowPlayer(newPlayer1, map[rumIndex], rumIndex);

            gui.setMessage("Game over");

        }
    }

    private void performNpcMove(int npcId) throws InterruptedException {
        Person selectedNpc = persons[npcId];
        int action = (int) (Math.random() * 3 + 1);
        sleep(1000);
        switch(action) {
        case 1:
            // Switch room
            selectedNpc.move();
            break;
        case 2:
            // Pick up some random shit
            if(selectedNpc.getInventory().getFirstObject() == null && map[selectedNpc.getPosition()-1].getInventory().getFirstObject() != null) {
                GameObject objectToGet = map[selectedNpc.getPosition()-1].getInventory().getFirstObject();
                selectedNpc.takeRandomItemFromRoom(objectToGet);
                map[selectedNpc.getPosition()-1].getInventory().removeObject(objectToGet);
            }
            break;
        case 3:
            // Drop some random shit
            if(selectedNpc.getInventory().getFirstObject() != null && map[selectedNpc.getPosition()-1].getInventory().getFirstEmptyIndex() != -1) {
                GameObject objectToDrop = selectedNpc.getInventory().getFirstObject();
                selectedNpc.dropItemInRoom(objectToDrop);
                map[selectedNpc.getPosition()-1].getInventory().addObject(objectToDrop);
                break;
            }
//        case 4:
//            // Say special phrase
//            System.out.println("Other thing");
//            break;
        }
    }
   
    public Person[] getPersonsInRoom( int index){
        Person[] inRoom = Arrays.stream(persons).filter(person -> {
            if (person.getPosition() == index) {
                return true;
            }
            return false;
        }).toArray(Projekt2.Person[]::new);
        return inRoom;
    }

}

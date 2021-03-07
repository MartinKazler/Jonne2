package Projekt2;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Room {
    private String name;
    private String description;
    private Inventory inventory;
    private Person[] person;
    private Gui gui;
    public Room(String roomName, String roomDescription, Gui g){
        this.gui=g;
        this.person = new Person[5];
        this.name = roomName;
        this.description= roomDescription;
        this.inventory = new Inventory(3,gui);

    }
    public void addNpc(Person person) {

        this.person = Stream.concat(Stream.of(this.person),Stream.of(person)).toArray(Person[]::new); //TODO Fixa fler personer
    }
    public Person[] getPersons(){
        return this.person;
    }
    public void addObject(GameObject go){
        this.inventory.addObject(go);
    }
    public void setPerson(Person[] person) {
        this.person = person;
    }
    public Inventory getInventory(){
        return this.inventory;
    }

    public String toString(){
        return name+" : "+description +"\n" +inventory;
    }

}
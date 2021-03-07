package Projekt2;

public class GameObject {
    private static int length;
	private String name;
    boolean moveable;

    public GameObject(String name ,boolean moveable){
        this.name = name;
        this.moveable = moveable;
    }
    public boolean isMoveable(){
        return this.moveable;
    }
    public String getName(){
        return this.name;
    }
    public String toString(){

        return this.name;
    }
    public static GameObject[] getRandomItems(int total){
        if(total > GameObject.length)
            return null;

        // Add some null values
        GameObject[] nullitems = new GameObject[GameObject.length*2];
        for(int i = 0; i < GameObject.length; i++){
            nullitems[i] = GameObjects[i];
        }
}}
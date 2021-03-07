package Projekt2;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Inventory {

	private GameObject[] list;
	private Gui gui;
	 private int capacity;
	private GameObject[] objects;
	public Inventory(int size, Gui g) {
		this.gui = g;
		list = new GameObject[size];
	}
	public void addItem(GameObject obj){
        if(size() < capacity){
            // Put null last
            GameObject[] newObjects = Arrays.stream(objects)
                                        .sorted(Comparator.nullsLast(Comparator.comparing(GameObject::getName)))
                                        .toArray(GameObject[]::new);

            // Add last
            newObjects[size()] = obj;
            this.objects = newObjects;
        }
    }
	public GameObject returnGameObject(String objectName) {
		for (GameObject g : list) {
			if (g.getName().equals(objectName)) {
				return g;
			}
		}
		return null;
	}
	  public int size(){
	        for(int i = 0; i < capacity; i++){
	            if(objects[i] == null)
	                return i;
	        }
	        return objects.length;
	  }
	public boolean addObject(GameObject go) {
		int index = getFirstEmptyIndex();
		if (index == -1) {
			System.out.println("Inventory är fullt");
			return false;
		}
		this.list[index] = go;
		return true;
	}

	public String toString() {
		return Arrays.toString(this.list);
	}
	
	public void removeItem(GameObject obj){
        this.objects = Arrays.stream(objects)
                        .filter(obj2 -> obj2 != obj)
                        .toArray(GameObject[]::new);
    }
	
	 public void dropRandom(Inventory inv) {
	        List<GameObject> items = Arrays.stream(objects)
	                .filter(Objects::nonNull)
	                .collect(Collectors.toList());

	        Collections.shuffle(items);

	        // Only take if it has items
	        if(items.size() > 0){
	            GameObject item = items.get(0);
	            inv.addItem(item);
	            this.removeItem(item);
	        }
	 }

	public void removeObject(GameObject gameObject) {
		for (int index = 0; index < list.length; index++) {
			if (this.list[index] != null && this.list[index].equals(gameObject)) {
				this.list[index] = null;
				return;
			}
		}
	}
	
	public void takeRandom(Inventory inv) {
        List<GameObject> GameObject = Arrays.stream(inv.objects)
                                .filter(Objects::nonNull)
                                .filter(x -> !(x instanceof Container)) // Prevent picking up container
                                .collect(Collectors.toList());
	}
	public void setInventory(GameObject[] objs){
        objects = objs;
    }
	public boolean contains(String objectName) {
		for (GameObject g : this.list) {
			if (g != null) {

				if (g.getName().equals(objectName)) {
					return true;
				}
			}

		}
		return false;
	}

	private int getFirstEmptyIndex() {

		for (int i = 0; i < this.list.length; i++) {
			if (this.list[i] == null) {
				return i;
			}
		}
		return -1;
	}
}

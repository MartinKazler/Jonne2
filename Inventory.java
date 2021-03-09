package Projekt2;

import java.util.Arrays;

public class Inventory {

	private GameObject[] list;
	public Inventory(int size, Gui g) {
		list = new GameObject[size];
	}

	public GameObject returnGameObject(String objectName) {
		for (GameObject g : list) {
			if (g.getName().equals(objectName)) {
				return g;
			}
		}
		return null;
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

	public void removeObject(GameObject gameObject) {
		for (int index = 0; index < list.length; index++) {
			if (this.list[index] != null && this.list[index].equals(gameObject)) {
				this.list[index] = null;
				return;
			}
		}
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

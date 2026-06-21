package org.uob.a1;

public class Inventory {
	/*
	The Inventory class stores the player’s inventory, and is essentially a wrapper around an
	array. It includes the maximum items you can store, the current number of items stored
	and an array to store the items in. The required methods are:
		• public Inventory()
		• public void addItem(String item) Adds an item to the array if there is
			space.
		• public int hasItem(String item) Returns the position of the item in the
			array if it is in the array. Otherwise it returns -1
		• public void removeItem(String item) Removes a specified item while
			ensuring there are no empty elements in the array.
		• public String displayInventory() Returns a String of all items sepa-
			rated by spaces (note that there is a space after the last item as well).
	Declare the maximum size as follows: final int MAX_ITEMS = 10;
	*/

	//attributes
	final int MAX_ITEMS = 10;
	private int currentItemNum;
	private String[] inventory;

	//constructor
    public Inventory() {
        this.inventory = new String[MAX_ITEMS];
		this.currentItemNum = 0;
    }

	//methods
	public void addItem(String item) {
		// append item to array if not full and display error if inventory is full
		boolean added = false;
	    for(int i = 0; i < MAX_ITEMS; i++) {
	        if(inventory[i] == null) {
	            inventory[i] = item;
	            added = true; 
				currentItemNum += 1;
	            break;        
	        }
	    }

	    if (!added) {
	        System.out.println("Inventory is Full!");
	    }
	}

	public int hasItem(String item) {
		// return position of item in the inventory
		int index;
	    for(int i = 0; i < MAX_ITEMS; i++) {
	        if(inventory[i] == item) {
	            index = i;
	            return index;       
	        }
	    }
		
		// return -1 if not found
        return -1;
	}

	public void removeItem(String item) {
		// remove a specified item
		boolean removed = false;
		if(!isFull()) {
			for(int i = 0; i < MAX_ITEMS; i++) {
		        if(inventory[i] == item) {
		            inventory[i] = null; 
					removed = true;
					System.out.println("Item removed.");
		        }
		    }
		}
		if(!removed) {
			System.out.println("Enter a valid item to remove.");
		}
	}

	public String displayInventory() {
		// returns a String of all items separated by spaces (note that there is a space after the last item as well).
		String invContents = "";
		if(!isEmpty()) {
			for(int i = 0; i < currentItemNum; i++) {
		        invContents += this.inventory[i] + " ";
		    }
			return invContents;
		}
        System.out.println("Inventory is empty!");
		invContents = "";
        return invContents;
	}

	public boolean isFull() {
		// check if array is full
		for(int i = 0; i < MAX_ITEMS; i++) {
	        if(inventory[i] == null) {
	            return false;     
	        }
	    }
		return true;
	}

	public boolean isEmpty() {
		return currentItemNum == 0;
	}
}
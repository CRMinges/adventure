package adventure;

import java.util.*;

/**
 * A class that will represent each room featured in the game. Will include getter
 * methods, as well setters to change the items in the room at any given time.
 * 
 * @author charlie.minges
 *
 */
public class Room {
	private String name_; //name of room
	private String[] neighbors_; //names of neighboring rooms
	private String description_; //short description
	private HashMap<String, Item> items_ = new HashMap<String, Item>(); //inventory of item in room

	/**
	 * Constructor that initialized information about the room and puts items
	 * put items that start in the room in it's inventory.
	 * 
	 * @param name
	 * 			represents name of room
	 * @param description
	 * 			represents short description of room
	 * @param neighbors
	 * 			represents the rooms neighbors
	 * @param items
	 * 			represents collection of items in world
	 */
	public Room(String name, String description, String[] neighbors, HashMap<String, Item> items) {
		name_ = name;
		description_ = description;
		neighbors_ = neighbors;
		
		setItems(items);
	}

	/**
	 * Method that puts items into the room that their starting location 
	 * specifies. Must use HashMap of that represents all of the items in
	 * the world.
	 * 
	 * @param items
	 * 			represents collection of items in world
	 */
	public void setItems(HashMap<String, Item> items) {
		for (Map.Entry<String, Item> elt : items.entrySet()) { //iterate through collection
			//if items location matches rooms name...
			if (elt.getValue().getLocation().equals(name_)) {
				//...put item in room
				items_.put(elt.getKey(), elt.getValue());
			}
		}
	}

	/**
	 * Method that is meant to print out a the rooms short description, its neighbors,
	 * and any items in the room at the time.
	 */
	public void look() {
		System.out.println(description_);

		System.out.println("Can exit to the ");
		// traverse neighbors array
		if (!neighbors_[0].equals("-")) { // if there is spot to north
			System.out.print("NORTH, ");
		}

		if (!neighbors_[1].equals("-")) { // if there is spot to south
			System.out.print("SOUTH, ");
		}

		if (!neighbors_[2].equals("-")) { // if there is spot to east
			System.out.print("EAST, ");
		}

		if (!neighbors_[3].equals("-")) { // if there is spot to west
			System.out.print("WEST, ");
		}

		System.out.println();
		System.out.println();

		if (items_.isEmpty()) { // if collection of items in room is empty
			System.out.println("No items in room, sorry.");
		} else { // else, items in room, print them out
			for (Map.Entry<String, Item> elt : items_.entrySet()) { // iterate										
				System.out.print(elt.getKey() + ", ");
			}
			System.out.println(" are in the room.");
		}

	}

	/**
	 * Getter method that returns an array of string representing the rooms neighbors.
	 * 
	 * @return 
	 * 		Array of names of the rooms that connect to the current room
	 */
	public String[] getNeighbors() {
		return neighbors_;
	}

	/**
	 * Getter method that returns rooms name.
	 * @return
	 * 		String represnting rooms name
	 */
	public String getName() {
		return name_;
	}

	/**
	 * Getter method that returns collection of items in room.
	 * @return
	 * 		HashMap representing items in room
	 */
	public HashMap<String, Item> getInventory() {
		return items_;
	}

	/**
	 * Getter method that returns rooms description.
	 * @return
	 * 		String representation of rooms description
	 */
	public String getDescription() {
		return description_;
	}
		
}

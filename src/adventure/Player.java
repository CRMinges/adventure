package adventure;

import java.util.*;

/**
 * Class made to represent a player in the game "adventure." Will Contain
 * information about the players location, as well as what they have in their
 * inventory. Methods include move, to move the player throughout he world, as
 * well as getters for instance variables.
 * 
 * @author charlie.minges
 *
 */
public class Player {

	private static String location_; //name of room player is in
	private static Map<String, Item> inventory_ = new HashMap<String, Item>(); //inventory of items
	private static int score_ = 0; //overall score

	/**
	 * Constructor that initializes the player location to the first room
	 */
	public Player() {
		location_ = "Bright orange hallway"; // set to first room, however we
												// address it
	}

	/**
	 * Move is a method that will be responsible for moving the player from the
	 * room they are currently in, to any of the possible adjacent rooms. The direction
	 * must be north, south, east, or west.
	 * 
	 * @param direction
	 *            string to represent direction player wishes to travel
	 * @param rooms
	 *            list that contains all the room objects created
	 */
	public void move(String direction, HashMap<String, Room> rooms) {
		direction = direction.toLowerCase();
		Room current = rooms.get(location_);

		String[] temp = current.getNeighbors();

		if (direction.equals("north")) { //if direction is north...
			if (!temp[0].equals("-")) {//if there is a room in said direction
				location_ = temp[0];
			} else {
				System.out.println("Sorry, cannot go this way, try again!");
			}
		} else if (direction.equals("south")) {//if direction is south...
			if (!temp[1].equals("-")) {//if there is a room in said direction
				location_ = temp[1];
			} else {
				System.out.println("Sorry, cannot go this way, try again!");
			}
		} else if (direction.equals("east")) {//if direction is east...
			if (!temp[2].equals("-")) { //if there is a room in said direction
				location_ = temp[2];
			} else {
				System.out.println("Sorry, cannot go this way, try again!");
			}
		} else if (direction.equals("west")) {//if direction is west...
			if (!temp[3].equals("-")) {//if there is a room in said direction
				location_ = temp[3];
			} else {
				System.out.println("Sorry, cannot go this way, try again!");
			}
		} else { //else
			System.out.println("Sorry, not valid direction, try again!");
		}

	}

	/**
	 * Method that will be used to add an object from the room you are in to
	 * your inventory. Object must be in room you are in.
	 * 
	 * @param item
	 *            represents item you wish to add to your inventory
	 */
	public void add(String item, HashMap<String, Room> rooms) {
		// check whether object is in room you are in
		Room current = rooms.get(location_);

		Item temp = null;

		if (current.getInventory().containsKey(item)) { // item is in room
			temp = current.getInventory().get(item);

			current.getInventory().remove(item);
			inventory_.put(temp.getName(), temp);
			System.out.println(item + " was successfully added");
		} else { //else
			System.out.println("Sorry, " + item + " is not in the room.");
		}
	}

	/**
	 * Method that will be responsible for dropping an item from you inventory
	 * to the room you are in. Must already have object in your inventory.
	 * 
	 * @param item
	 *            represents name of item you wish to drop
	 */
	public void drop(String item, HashMap<String, Room> rooms) {
		Item temp = null;

		if (inventory_.containsKey(item)) { //if item is in inventory
			temp = inventory_.get(item);
			inventory_.remove(item);

			Room current = rooms.get(location_);

			current.getInventory().put(temp.getName(), temp);
			System.out.println(item + " was successfully dropped");
		} else { // else
			System.out.println("Sorry, " + item + " is not in your inventory.");
		}
	}

	/**
	 * Getter method that returns the players location.
	 * 
	 * @return 
	 * 		string representing name of room player is in
	 */
	public String getLocation() {
		return location_;
	}

	/**
	 * Getter method that prints out the objects in the players inventory.
	 */
	public void getInventory() {
		if (inventory_.isEmpty()) { //if inventory is empty
			System.out.println("Inventory is empty");
		} else { //else
			for (Map.Entry<String, Item> elt : inventory_.entrySet()) {
				System.out.println(elt.getKey());
			}
		}
	}

	/**
	 * Method that gets an items description if it is in your inventory.
	 * @param item
	 * 		name of item
	 */
	public void look(String item) {
		System.out.println(inventory_.get(item).getDescription());
	}

	/**
	 * Method that gets description of room currently in.
	 * @param rooms
	 * 			collection of rooms in world
	 */
	public void look(HashMap<String, Room> rooms) {
		rooms.get(location_).look();
	}

	/**
	 * Getter method that returns players score.
	 * @return
	 * 		int representing score
	 */
	public int getScore() {
		return score_;
	}
	
	/**
	 * Method used to check whether a given action from player warrants giving them
	 * points.
	 * 
	 * @param scores
	 * 			collection of Score objects
	 * @param ref
	 * 			represents string used for finding particular key value
	 * 			
	 */
	public void checkScores(HashMap<String, Score> scores, String ref) {
		if (scores.containsKey(ref)) { //if scores contains key
			score_ = score_ + scores.get(ref).getScore();
			scores.remove(ref);
		}
	}
	
}

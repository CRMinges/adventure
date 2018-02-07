package adventure;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * A class with the purpose of initializing the rooms and items and scoring
 * guidelines that are to be used to play the adventure game. The class reads in
 * from text files and extracts information from the files to use in the
 * initialization process.
 * 
 * @author charlie.minges
 *
 */
public class ReadIn {

	/**
	 * Method that is used to create and initialize a collection of rooms that
	 * are to be navigated by the player. Reads in from predetermined text file.
	 * A HashMap of items to be stored in the rooms must be created first, as it
	 * will be passed through as a parameter to put object in the correct rooms.
	 * 
	 * @param items
	 *            represents the HashMap that represents collection of items in
	 *            world
	 * @return a new HashMap representing the collection of rooms
	 */
	public static HashMap<String, Room> createRooms(HashMap<String, Item> items) {
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader("Rooms.txt"));
			String line = reader.readLine();
			HashMap<String, Room> rooms = new HashMap<String, Room>();

			while (line != null) { // while we can still read from file
				String name = line;

				line = reader.readLine();

				String[] neighbors = line.split(",");
				for (int i = 0; i < neighbors.length; i++) { // travers
																// neighbors
					// trim each name
					neighbors[i] = neighbors[i].trim();
				}

				line = reader.readLine();
				String description = "";

				while (!line.equals("END")) { // while reader has not hit "END"
					// add each line to overall string for description
					description = description + line + '\n';
					line = reader.readLine();
				}

				// put new room object and rooms name in HashMap
				rooms.put(name, new Room(name, description, neighbors, items));

				line = reader.readLine(); // move line to beginning of next
											// section of text
			}

			return rooms;
		} catch (IOException e) {
			System.out.println("File could not be accessed, try again!");
		}
		return null;
	}

	/**
	 * Method that will create and initialize a collection of the items that
	 * will be placed in the game. Will read from predetermined text file. File
	 * must exist to be read.
	 * 
	 * @return returns a HashMap representing collection of items
	 */
	public static HashMap<String, Item> createItems() {
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader("Items.txt"));
			String line = reader.readLine();
			HashMap<String, Item> items = new HashMap<String, Item>();

			while (line != null) { // while more lines to read
				String name = line;
				name = name.toLowerCase();

				line = reader.readLine();

				String location = line.trim();

				line = reader.readLine();

				String description = "";

				while (!line.equals("END")) { // while reader has not hit "END"
					description = description + line + '\n';
					line = reader.readLine();
				}

				// put new items object and items name into HashMap
				items.put(name, new Item(name, description, location));

				line = reader.readLine(); // move line to beginning of next
											// section of text
			}

			return items;
		} catch (IOException e) {
			System.out.println("File could not be accessed, please try again!");
		}
		return null;
	}

	/**
	 * Method that will create a collection of Score items, which hold
	 * requirements that must be met in order for the player to earn points.
	 * Reads in from a predetermined text file.
	 * 
	 * @return represents collection of Score items
	 */
	public static HashMap<String, Score> createScore() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Score.txt"));
			String line = reader.readLine();
			HashMap<String, Score> scores = new HashMap<String, Score>();

			while (line != null) { // while more lines to read
				String[] temp = line.split(",");

				for (int i = 0; i < temp.length; i++) { // traverse array
					// trim the strings
					temp[i] = temp[i].trim();
				}

				String ref = "";
				if (temp[0].equals("VISIT")) { // if actions is to visit a room
					// ref will be "VISIT" and room name
					ref = temp[0] + " " + temp[2];
				} else { // else
					// will be action plus object name
					ref = temp[0] + " " + temp[1];
				}
				ref = ref.toLowerCase();

				// put new Score object and ref to HashMap
				scores.put(ref, new Score(temp[0], temp[1], temp[2], Integer.parseInt(temp[3])));

				line = reader.readLine();
			}
			return scores;
		} catch (IOException e) {
			System.out.println("File could not be accessed, please try again!");
		}
		return null;
	}

}

package adventure;

import java.util.*;

/**
 * Main method that runs the game. Repeatedly allows for user input, and prints
 * out proper information due to any given input. Stops when player has won or
 * decides to quit.
 * 
 * @author charlie.minges
 *
 */
public class Main {

	/**
	 * Method to represent player interaction with game.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		Player player = new Player();

		HashMap<String, Item> items = ReadIn.createItems();
		HashMap<String, Room> rooms = ReadIn.createRooms(items);
		HashMap<String, Score> scores = ReadIn.createScore();
		Scanner inp = new Scanner(System.in);
		System.out.println(rooms.get(player.getLocation()).getDescription());

		while (true) { // allows for player to repeatedly enter input

			// scanner set up to read user input
			String input = inp.nextLine();
			input = input.toLowerCase();
			String[] command = input.split(" ");

			// if command is to take
			if (command[0].equals("take") || command[0].equals("get")) {
				// making sure input has command and item
				if (command.length >= 2) {
					String temp = "";
					// create string of object name
					for (int i = 1; i < command.length; i++) {
						temp = temp + command[i] + " ";
					}
					temp = temp.trim();

					player.add(temp, rooms);

					// check if we meet goal for taking object
					player.checkScores(scores, "take " + temp);

				} else { // else
					System.out.println(command[1] + " not found/doesn't exist, please try again!");
				}

			} else if (command[0].equals("drop")) { // player wants to drop
				// making sure input has command and item
				if (command.length >= 2) {
					String temp = "";
					// create string of object name
					for (int i = 1; i < command.length; i++) {
						temp = temp + command[i] + " ";
					}
					temp = temp.trim();
					player.drop(temp, rooms);

					// check if we meet goal for dropping object
					//first check if we would be dropping in correct room...
					if (scores.get("drop " + temp).getPlace().equals(player.getLocation())) {
						//if so...
						player.checkScores(scores, "drop " + temp);
					}
					

				} else { // else
					System.out.println(command[1] + "not found/doesn't exist, please try again!");
				}

			} else if (command[0].equals("look")) { // if player wants to look
				// if player wants to look at object
				if (command.length >= 2) {
					String temp = "";
					// create string of object name
					for (int i = 1; i < command.length; i++) {
						temp = temp + command[i] + " ";
					}
					temp = temp.trim();
					player.look(temp);
				} else if (command.length == 1) { // else want to look at room
					player.look(rooms);
				} else { // else
					System.out.println(command[1] + "not found/doesn't exist, please try again!");
				}

			} else if (command[0].equals("move") || command[0].equals("go")) { // else
																				// wants
																				// to
																				// move
				player.move(command[1], rooms);
				System.out.println(rooms.get(player.getLocation()).getDescription());
			} else if (command[0].equals("north") || command[0].equals("south") || command[0].equals("east")
					|| command[0].equals("west")) { // wants to move, only by
													// giving direction
				player.move(command[0], rooms);
				System.out.println(rooms.get(player.getLocation()).getDescription());
			} else if (command.length == 1) { // else
				if (command[0].equals("score")) {// if want to check score
					System.out.println(player.getScore());
				} else if (command[0].equals("inventory")) { // if want to check
																// inventory
					player.getInventory();
				} else if (command[0].equals("quit")) { // if want to quit
					System.out.println("Thank you for playing!");
					System.exit(0);
				}
			} else { // else no such command
				System.out.println("No such command, please try again");
			}

			// check if we have met goal for visiting room
			player.checkScores(scores, "visit " + player.getLocation().toLowerCase());

			// if we have completed all goals and are in final destination
			if (scores.isEmpty() && player.getLocation().equals("Long hallway")) {
				System.out.println("Congratulation. You are able to open the door and get out into the " + '\n'
						+ "jungle. You see a river not far away, which may have people on it. You take" + '\n'
						+ "off in that direction.");
				System.out.println("You finished with a score of " + player.getScore());
				System.exit(0);
			} else if (scores.isEmpty() && !player.getLocation().equals("Long hallway")) {
				// else if we have completed goals but are not at final
				// destination
				System.out.println("You have completed your goals. Try to get to an exit point...");
			}
		}
	}
}

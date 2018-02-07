package adventure;


/**
 * A class that represents the requirements of goals for player to reach in
 * order to earn points.
 * 
 * @author charlie.minges
 *
 */
public class Score {
	private String action_; //type of action (drop, take, visit)
	private String item_; //name of item involved (if there is one)
	private String place_; //name of room action takes place in
	private int score_; //score from completing goal

	/**
	 * Constructors to initialize information about goal.
	 * 
	 * @param action
	 *            represents type of command requires
	 * @param item
	 *            represents item it involves, if there is one
	 * @param place
	 *            represents where action is to take place
	 * @param score
	 *            represents points to be awarded
	 */
	public Score(String action, String item, String place, int score) {
		action_ = action;
		item_ = item;
		place_ = place;
		score_ = score;
	}

	/**
	 * Getter method that returns points of goal.
	 * 
	 * @return int number represents points to be awarded
	 */
	public int getScore() {
		return score_;
	}

	/**
	 * Getter method that returns the type of action required.
	 * 
	 * @return String representation of command required from player
	 */
	public String getAction() {
		return action_;
	}

	/**
	 * Getter method the returns item involved, if there is one.
	 * 
	 * @return name of item
	 */
	public String getItem() {
		return item_;
	}

	/**
	 * Getter method the returns where action is to take place.
	 * 
	 * @return name of room
	 */
	public String getPlace() {
		return place_;
	}
}

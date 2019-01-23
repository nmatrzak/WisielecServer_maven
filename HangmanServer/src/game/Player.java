
package game;

import java.time.LocalDateTime;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Player.
 */
public class Player {
	
	/** The Constant MAX_ECHO_SECONDS. */
	private static final int MAX_ECHO_SECONDS = 10;
	
	/** The player id. */
	private long playerId = 0;
	
	/** The seq player id. */
	private static long seqPlayerId = 0;	
	
	/** The name. */
	private String name;
	
	/** The points. */
	private long points = 0;
	
	/** The count wins. */
	private long countWins = 0;
	
	/** The count losts. */
	private long countLosts = 0;	
	
	/** The status. */
	private PlayerStatus status = PlayerStatus.CREATED;
	
	/** The is computer. */
	private boolean isComputer = false;
	
	/** The last activity. */
	private LocalDateTime lastActivity;
	
	
	/**
	 * Instantiates a new player.
	 *
	 * @param name the name
	 */
	public Player(String name) {
	  playerId = ++seqPlayerId;
      System.out.println("Player '"+name+"' created with id="+playerId);
      this.name = name;
      updateLastActivity();
	}
	
	/**
	 * Update last activity.
	 */
	public void updateLastActivity() {
//		System.out.println("Player with id="+playerId+" updateLastActivity");
		lastActivity = LocalDateTime.now();
	}
	
	/**
	 * None feed back.
	 *
	 * @return true, if successful
	 */
	public boolean noneFeedBack() {
		return LocalDateTime.now().minusSeconds(MAX_ECHO_SECONDS).isAfter(lastActivity);
	}
		
	/**
	 * Gets the player id.
	 *
	 * @return the player id
	 */
	public long getPlayerId() {
		return playerId;
	}

	/**
	 * Creates the random name.
	 *
	 * @return the string
	 */
	public static String createRandomName() {
		return "VirtualPlayer"+(10000000+(new Random()).nextInt(10000000));
	}
	
	/**
	 * Creates the computer player.
	 *
	 * @return the player
	 */
	public static Player createComputerPlayer() {
		Player player = new Player(createRandomName());
		player.isComputer = true;
		return player;
	}
	
	/**
	 * Adds the points.
	 *
	 * @param countUniqueLetters the count unique letters
	 * @return the long
	 */
	public long addPoints(long countUniqueLetters) {
		this.points += countUniqueLetters;
		return countUniqueLetters;
	}
	
	/**
	 * Inc win.
	 *
	 * @return the long
	 */
	public long incWin() {		
		return ++countWins;
	}
	
	/**
	 * Inc lost.
	 *
	 * @return the long
	 */
	public long incLost() {
		
		return ++countLosts;
	}
	
	/**
	 * End game.
	 */
	public void endGame() {
		status = PlayerStatus.CREATED;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public long getPoints() {
		return points;
	}

	/**
	 * Gets the count wins.
	 *
	 * @return the count wins
	 */
	public long getCountWins() {
		return countWins;
	}

	/**
	 * Gets the count losts.
	 *
	 * @return the count losts
	 */
	public long getCountLosts() {
		return countLosts;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public PlayerStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(PlayerStatus status) {
		this.status = status;
	}	
	
	/**
	 * Checks if is computer.
	 *
	 * @return true, if is computer
	 */
	public boolean isComputer() {
		return isComputer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Player: %s, points: %d, wins: %d, losts: %d, is_computer: %d",name,points,countWins,countLosts, isComputer?1:0 );
	}
}

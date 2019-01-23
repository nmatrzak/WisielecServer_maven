
package dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The object of PlayerDto class carries data between processes (client and server)
 * The class implements a tag interface (does not contain any method) Serializable.
 * @author Norbert Matrzak
 * @version 1.0
 * @since 2019-01-01
 */
@XmlRootElement
public class PlayerDto implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7526471155622776147L;
	
	/** The player name. */
	private String name;
	
	/** The No of points. */
	private long points = 0;
	
	/** The No of wins. */
	private long countWins = 0;
	
	/** The No of losts. */
	private long countLosts = 0;
	
	/** The game status. */
	private String status;
	
	/** The player id. */
	private long playerId = 0;
	
	/**
	 * Instantiates a new playerDto.
	 */
	public PlayerDto() { }
	
	/**
	 * Instantiates a new playerDto.
	 *
	 * @param playerId the player id
	 * @param name the player name
	 * @param points the No of points
	 * @param countWins the No of wins
	 * @param countLosts the No of losts
	 * @param status the game status
	 */
	public PlayerDto(long playerId, String name, long points, long countWins, long countLosts,  String status) {
		this.playerId = playerId;
		this.name=name;
		this.points=points;
		this.countWins=countWins;
		this.countLosts=countLosts;
		this.status = status;
	}
	
	/**
	 * Sets the player id.
	 *
	 * @param playerId the new player id
	 */
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
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
	 * Gets the player name.
	 *
	 * @return the player name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the player name.
	 *
	 * @param name the new player name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the No of points.
	 *
	 * @return the No of points
	 */
	public long getPoints() {
		return points;
	}
	
	/**
	 * Sets the No of points.
	 *
	 * @param points the new No of points
	 */
	public void setPoints(long points) {
		this.points = points;
	}
	
	/**
	 * Gets the No of wins.
	 *
	 * @return the No of wins
	 */
	public long getCountWins() {
		return countWins;
	}
	
	/**
	 * Sets the No of wins.
	 *
	 * @param countWins the new No of wins
	 */
	public void setCountWins(long countWins) {
		this.countWins = countWins;
	}
	
	/**
	 * Gets the No of losts.
	 *
	 * @return the No of losts
	 */
	public long getCountLosts() {
		return countLosts;
	}
	
	/**
	 * Sets the No of losts.
	 *
	 * @param countLosts the new No of losts
	 */
	public void setCountLosts(long countLosts) {
		this.countLosts = countLosts;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/* 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player: Name="+name;
	}
}

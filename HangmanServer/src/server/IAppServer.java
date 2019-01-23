
package server;

import game.Player;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAppServer.
 */
public interface IAppServer {

	/**
	 * Removes the player by id.
	 *
	 * @param playerId the player id
	 */
	void removePlayerById(long playerId);	
	
	/**
	 * Send message player disconnected.
	 *
	 * @param opponent the opponent
	 */
	void sendMessagePlayerDisconnected(Player opponent);
	
	/**
	 * Send message opponent end game.
	 *
	 * @param opponent the opponent
	 */
	void sendMessageOpponentEndGame(Player opponent);
	
	/**
	 * Send go to page.
	 *
	 * @param opponent the opponent
	 * @param page the page
	 */
	void sendGoToPage(Player opponent, String page);
	
	/**
	 * Word updated.
	 *
	 * @param playerName the player name
	 */
	void wordUpdated(Player playerName);	
	
	/**
	 * Send letter.
	 *
	 * @param playerNamer the player namer
	 * @param letter the letter
	 */
	void sendLetter(Player playerNamer, String letter);
	
	/**
	 * Send refresh list players to all.
	 */
	void sendRefreshListPlayersToAll();	
	
}

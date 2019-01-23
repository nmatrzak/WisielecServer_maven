
package server;

import java.util.List;

import game.Game;
import game.Player;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGameServer.
 */
public interface IGameServer {

	/**
	 * Player disconnected.
	 *
	 * @param player the player
	 */
	void playerDisconnected(Player player);
	
	/**
	 * Adds the player.
	 *
	 * @param player the player
	 */
	void addPlayer(Player player);	
	
	/**
	 * Removes the player.
	 *
	 * @param player the player
	 */
	void removePlayer(Player player);
	
	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	List<Player> getPlayers();
	
	/**
	 * Find player by name.
	 *
	 * @param playerName the player name
	 * @return the player
	 */
	Player findPlayerByName(String playerName);
	
	/**
	 * Find player by id.
	 *
	 * @param palyerId the palyer id
	 * @return the player
	 */
	Player findPlayerById(long palyerId);
	
	/**
	 * Creates the game.
	 *
	 * @param player the player
	 * @param opponent the opponent
	 * @return the game
	 */
	Game createGame(Player player, Player opponent);
	
	/**
	 * Creates the game.
	 *
	 * @param player the player
	 * @return the game
	 */
	Game createGame(Player player);
	
	/**
	 * Update gapped word letter.
	 *
	 * @param player the player
	 * @param letter the letter
	 * @return the game
	 */
	Game updateGappedWordLetter(Player player, String letter);
	
	/**
	 * Gets the game by player name.
	 *
	 * @param playerName the player name
	 * @return the game by player name
	 */
	Game getGameByPlayerName(String playerName);
	
	/**
	 * Find game by player.
	 *
	 * @param player the player
	 * @return the game
	 */
	Game findGameByPlayer(Player player);
	
	/**
	 * Update word.
	 *
	 * @param player the player
	 * @param word the word
	 * @return the game
	 */
	Game updateWord(Player player, String word);
	
	/**
	 * Gets the list of games.
	 *
	 * @return the list of games
	 */
	List<Game> getListOfGames();
	
}


package service;

import java.util.List;

import dto.GameDto;
import dto.PlayerDto;
import game.Game;

// TODO: Auto-generated Javadoc
/**
 * The Interface IPlayerService.
 */
public interface IPlayerService {
	
	/**
	 * Gets the players dto.
	 *
	 * @return the players dto
	 */
	List<PlayerDto> getPlayersDto();
	
	/**
	 * Gets the player.
	 *
	 * @param userName the user name
	 * @return the player
	 */
	PlayerDto getPlayer(String userName);
	
	/**
	 * Gets the player.
	 *
	 * @param id the id
	 * @return the player
	 */
	PlayerDto getPlayer(long id);
	
	/**
	 * Creates the player.
	 *
	 * @param userName the user name
	 * @return the player dto
	 */
	PlayerDto createPlayer(String userName);
	
	/**
	 * Removes the player.
	 *
	 * @param id the id
	 * @return the player dto
	 */
	PlayerDto removePlayer(long id);
	
	/**
	 * Creates the game.
	 *
	 * @param playerId the player id
	 * @param opponentId the opponent id
	 * @return the game
	 */
	Game createGame(long playerId, long opponentId);
	
	/**
	 * Update gapped word letter.
	 *
	 * @param playerId the player id
	 * @param letter the letter
	 * @return the game dto
	 */
	GameDto updateGappedWordLetter(long playerId, String letter);
	
	/**
	 * Update word.
	 *
	 * @param playerId the player id
	 * @param word the word
	 * @return the game dto
	 */
	GameDto updateWord(long playerId, String word);
	
	/**
	 * Gets the game.
	 *
	 * @param playerId the player id
	 * @return the game
	 */
	GameDto getGame(long playerId);
	
    /**
     * Player alive.
     *
     * @param playerId the player id
     */
    void playerAlive(long playerId);


}

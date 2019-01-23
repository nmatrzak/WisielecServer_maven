package dto;

import java.io.Serializable;

import game.Game;
import utils.WordCodeDecode;

/**
 * The object of GameDto class carries data between processes (client and server)
 * The class implements a tag interface (does not contain any method) Serializable.
 * @author Norbert Matrzak
 * @version 1.0
 * @since 2019-01-01
 */
public class GameDto implements Serializable {

	/** The constant serialVersionUID. */
	private static final long serialVersionUID = 75264711556227767L;

	/** The word */
	private String theWord;
	
	/** The gapped word. */
	private String gappedWord;
	
	/** The count missed. */
	private int countMissed = 0;
	
	/** The used letters. */
	private String usedLetters;
	
	/** The game status. */
	private String gameStatus;
	
	/** The winner. */
	private String winner;
	
	/** The player word name. */
	private String playerWordName;
	
	/** The player guess name.*/
	private String playerGuessName;

	/**
	 * Instantiates a new GameDto.
	 *
	 * @param theWord the the word
	 * @param gappedWord the gap in word
	 * @param countMissed the count missed
	 * @param usedLetters the used letters
	 * @param gameStatus the game status
	 * @param winner the winner
	 * @param playerWordName the player word name
	 * @param playerGuessName the player guess name
	 */
	public GameDto(String theWord, String gappedWord, int countMissed, String usedLetters, String gameStatus,
			String winner, String playerWordName, String playerGuessName) {
		this.theWord = theWord;
		this.gappedWord = gappedWord;
		this.countMissed = countMissed;
		this.usedLetters = usedLetters;
		this.gameStatus = gameStatus;
		this.winner = winner;
		this.playerWordName = playerWordName;
		this.playerGuessName = playerGuessName;
	}

	/**
	 * Gets the the word.
	 *
	 * @return the the word
	 */
	public String getTheWord() {
		return theWord;
	}

	/**
	 * Sets the the word.
	 *
	 * @param theWord the new the word
	 */
	public void setTheWord(String theWord) {
		this.theWord = theWord;
	}

	/**
	 * Gets the gap in word.
	 *
	 * @return the gap in word
	 */
	public String getGappedWord() {
		return gappedWord;
	}

	/**
	 * Sets the gap in word.
	 *
	 * @param gappedWord the new gap in word
	 */
	public void setGappedWord(String gappedWord) {
		this.gappedWord = gappedWord;
	}

	/**
	 * Gets the count missed.
	 *
	 * @return the count missed
	 */
	public int getCountMissed() {
		return countMissed;
	}

	/**
	 * Sets the count missed.
	 *
	 * @param countMissed the new count missed
	 */
	public void setCountMissed(int countMissed) {
		this.countMissed = countMissed;
	}

	/**
	 * Gets the used letters.
	 *
	 * @return the used letters
	 */
	public String getUsedLetters() {
		return usedLetters;
	}

	/**
	 * Sets the used letters.
	 *
	 * @param usedLetters the new used letters
	 */
	public void setUsedLetters(String usedLetters) {
		this.usedLetters = usedLetters;
	}

	/**
	 * Gets the game status.
	 *
	 * @return the game status
	 */
	public String getGameStatus() {
		return gameStatus;
	}

	/**
	 * Sets the game status.
	 *
	 * @param gameStatus the new game status
	 */
	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	/**
	 * Gets the winner.
	 *
	 * @return the winner
	 */
	public String getWinner() {
		return winner;
	}

	/**
	 * Sets the winner.
	 *
	 * @param winner the new winner
	 */
	public void setWinner(String winner) {
		this.winner = winner;
	}

	/**
	 * Gets the player one name.
	 *
	 * @return the player one name
	 */
	public String getPlayer1Name() {
		return playerWordName;
	}

	/**
	 * Sets the player one name.
	 *
	 * @param player1Name the new player one name
	 */
	public void setPlayer1Name(String player1Name) {
		this.playerWordName = player1Name;
	}

	/**
	 * Gets the player two name.
	 *
	 * @return the player two name
	 */
	public String getPlayer2Name() {
		return playerGuessName;
	}

	/**
	 * Sets the player two name.
	 *
	 * @param player2Name the new player two name
	 */
	public void setPlayer2Name(String player2Name) {
		this.playerGuessName = player2Name;
	}

	/**
	 * This static method is used to check status of parameter game and 
	 * get instantiates of GameDto or null.
	 * @param game the game
	 * @return GameDto
	 */
	public static GameDto of(Game game) {
		if (game != null) {
			return new GameDto(WordCodeDecode.codePolishWordToWordWithSpecs(game.getTheWord()),
					WordCodeDecode.codePolishWordToWordWithSpecs(game.getGappedWord()), game.getCountMissed(),
					game.getUsedLetters(), game.getGameStatus().name(),
					WordCodeDecode.codePolishWordToWordWithSpecs(game.getWinnerName()),
					WordCodeDecode.codePolishWordToWordWithSpecs(game.getWordPlayer().getName()),
					WordCodeDecode.codePolishWordToWordWithSpecs(game.getGuessPlayer().getName()));
		} else
			return null;

	}
	
	/* (
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("player word: %s, player guess, %s, state: %s, word: %s",playerWordName, playerGuessName, gameStatus, theWord);
	}

}

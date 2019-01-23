
package game;

import java.time.LocalDateTime;
import java.util.Optional;

import utils.WordGenerator;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
public class Game {

	/** The Constant MAX_MISSED_LETTERS. */
	private static final int MAX_MISSED_LETTERS = 8;
	
	/** The Constant wordGenerator. */
	private static final WordGenerator wordGenerator = new WordGenerator();
	
	/** The game id. */
	private long gameId = 0;
	
	/** The seq game id. */
	private static long seqGameId = 0;

	/** The players. */
	private Player[] players = new Player[2];
	
	/** The winner. */
	private Player theWinner = null;
	
	/** The word player. */
	private int wordPlayer = 0;
	
	/** The word. */
	private String theWord;
	
	/** The count unique letters. */
	private long countUniqueLetters = 0;
	
	/** The count missed. */
	private int countMissed = 0;
	
	/** The used letters. */
	private String usedLetters;
	
	/** The max missed letters. */
	private int maxMissedLetters;
	
	/** The game status. */
	private GameStatus gameStatus = GameStatus.CREATED;
	
	/** The last activity. */
	private LocalDateTime lastActivity;

	/**
	 * Instantiates a new game.
	 */
	public Game() {
		gameId = ++seqGameId;
		updateLastActivity();
	}

	/**
	 * Update last activity.
	 */
	private void updateLastActivity() {
		lastActivity = LocalDateTime.now();
		if (players[0] != null) {
			players[0].updateLastActivity();
		}
		if (players[1] != null) {
			players[1].updateLastActivity();
		}
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
	 * Gets the count missed.
	 *
	 * @return the count missed
	 */
	public int getCountMissed() {
		return countMissed;
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
	 * Gets the game status.
	 *
	 * @return the game status
	 */
	public GameStatus getGameStatus() {
		return gameStatus;
	}

	/**
	 * Sets the word player.
	 *
	 * @param player the new word player
	 */
	public void setWordPlayer(Player player) {
		players[wordPlayer] = player;
	}

	/**
	 * Sets the guess player.
	 *
	 * @param player the new guess player
	 */
	public void setGuessPlayer(Player player) {
		players[1 - wordPlayer] = player;
	}

	/**
	 * Gets the word player.
	 *
	 * @return the word player
	 */
	public Player getWordPlayer() {
		return players[wordPlayer];
	}

	/**
	 * Gets the guess player.
	 *
	 * @return the guess player
	 */
	public Player getGuessPlayer() {
		return players[1 - wordPlayer];
	}

	/**
	 * Gets the winner.
	 *
	 * @return the winner
	 */
	public Player getWinner() {
		return theWinner;
	}

	/**
	 * Gets the winner name.
	 *
	 * @return the winner name
	 */
	public String getWinnerName() {
		return Optional.ofNullable(theWinner).orElse(new Player("")).getName();
	}

	/**
	 * Gets the game id.
	 *
	 * @return the game id
	 */
	public long getGameId() {
		return gameId;
	}

	/**
	 * Gets the last activity.
	 *
	 * @return the last activity
	 */
	public LocalDateTime getLastActivity() {
		return lastActivity;
	}

	/**
	 * Inits the.
	 */
	public void init() {
		System.out.println("Server.Game.init: ");
		theWord = "";
		theWinner = null;
		countMissed = 0;
		usedLetters = "";
		if (!getWordPlayer().isComputer()) {
			gameStatus = GameStatus.WAIT_FOR_WORD;
		} else {
			updateWord(wordGenerator.getNewWord());
		}
		
		getWordPlayer().setStatus(PlayerStatus.PLAYING);
		getGuessPlayer().setStatus(PlayerStatus.PLAYING);
		updateLastActivity();
	}

	/**
	 * Update word.
	 *
	 * @param theWord the the word
	 */
	public void updateWord(String theWord) {
		System.out.println("Server.Game.updateWord: " + theWord);
		this.theWord = theWord.toUpperCase();
		this.gameStatus = GameStatus.PLAY;
		initCounters();
		updateLastActivity();
	}

	/**
	 * Inits the counters.
	 */
	private void initCounters() {
		maxMissedLetters = MAX_MISSED_LETTERS;
		countUniqueLetters = countUniqueCharacters(theWord);
	}

	/**
	 * Count unique characters.
	 *
	 * @param input the input
	 * @return the long
	 */
	public long countUniqueCharacters(String input) {
		return input.chars().distinct().count();
	}

	/**
	 * Letter hit.
	 *
	 * @param letter the letter
	 * @return true, if successful
	 */
	public boolean letterHit(char letter) {
		return theWord.chars().anyMatch(c -> letter == c);
	}

	/**
	 * Gets the gapped word.
	 *
	 * @return the gapped word
	 */
	public String getGappedWord() {
		if (theWord == null || theWord.isEmpty()) {
			return "";
		}
		StringBuilder gappedWord = new StringBuilder();
		for (int i = 0; i < theWord.length(); i++) {
			char wordChar = theWord.charAt(i);
			if (letterHasBeenUsed(wordChar)) {
				gappedWord.append(String.valueOf(wordChar));
			} else {
				gappedWord.append('_');
			}
		}
		return gappedWord.toString();
	}

	/**
	 * Word guessed.
	 *
	 * @return true, if successful
	 */
	public boolean wordGuessed() {
		return getGappedWord().equals(theWord);
	}

	/**
	 * Misses reach maximum.
	 *
	 * @return true, if successful
	 */
	public boolean missesReachMaximum() {
		return countMissed == maxMissedLetters;
	}

	/**
	 * Letter has been used.
	 *
	 * @param letter the letter
	 * @return true, if successful
	 */
	public boolean letterHasBeenUsed(char letter) {
		return usedLetters.chars().anyMatch(c -> c == letter);
	}

	/**
	 * Try letter.
	 *
	 * @param letter the letter
	 */
	public void tryLetter(char letter) {
		if (!letterHasBeenUsed(letter)) {
			usedLetters = new StringBuilder().append(usedLetters).append(letter).toString();
			if (letterHit(letter)) {
				if (wordGuessed()) {
					Player wordPlayer = getWordPlayer();
					Player guessPlayer = getGuessPlayer();
					guessPlayer.addPoints(countUniqueLetters);
					guessPlayer.incWin();
					getWordPlayer().incLost();
					theWinner = guessPlayer;
					guessPlayer.endGame();
					wordPlayer.endGame();
					gameStatus = GameStatus.END;
				}
			} else {
				countMissed++;
				if (missesReachMaximum()) {
					Player wordPlayer = getWordPlayer();
					Player guessPlayer = getGuessPlayer();
					wordPlayer.addPoints(countUniqueLetters);
					getGuessPlayer().incLost();
					wordPlayer.incWin();
					theWinner = wordPlayer;
					wordPlayer.endGame();
					guessPlayer.endGame();
					gameStatus = GameStatus.END;
				}
			}
		}
	}

	/**
	 * End game before becouse of player.
	 *
	 * @param player the player
	 */
	public void endGameBeforeBecouseOfPlayer(Player player) {
		Player guessPlayer = getGuessPlayer();
		Player wordPlayer = getWordPlayer();
		if (gameStatus == GameStatus.PLAY) {
			if (player == guessPlayer) {
				wordPlayer.addPoints(1);
			} else {
				guessPlayer.addPoints(countUniqueLetters);
			}
		}
		guessPlayer.endGame();
		wordPlayer.endGame();
		gameStatus = GameStatus.END;
	}

	/**
	 * Guess letter.
	 *
	 * @param letter the letter
	 * @return the game
	 */
	public Game guessLetter(String letter) {
		updateLastActivity();
		if (!letter.isEmpty()) {
			tryLetter(letter.charAt(0));
		}
		return this;
	}

	/**
	 * Player in.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean playerIn(Player player) {
		return players[0] == player || players[1] == player;
	}

	/**
	 * Gets the opponent.
	 *
	 * @param player the player
	 * @return the opponent
	 */
	public Player getOpponent(Player player) {
		if (players[0] == player) {
			return players[1];
		} else if (players[1] == player) {
			return players[0];
		} else {
			return null;
		}
	}
	
	/**
	 * With computer.
	 *
	 * @return true, if successful
	 */
	public boolean withComputer() {
		return getWordPlayer().isComputer(); 
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Game: wordPlayerName=%s, guessPlayerName=%s", getWordPlayer(), getGuessPlayer());
	}
}

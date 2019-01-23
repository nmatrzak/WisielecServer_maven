
package tests;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import game.Game;
import game.Player;
import server.GameServer;

// TODO: Auto-generated Javadoc
/**
 * The Class GameServerTests.
 */
public class GameServerTests {
	
	/** The game server. */
	private static GameServer gameServer;
	
	/**
	 * Inits the.
	 */
	@BeforeClass
	public static void init() {
		gameServer = new GameServer();
	}
	
	/**
	 * Shoul add player.
	 */
	@Test
	public void shoulAddPlayer() {
		Player player1 = new Player("Krzysztof");		
		gameServer.addPlayer(player1);
		assertTrue(gameServer.findPlayerByName(player1.getName())!=null);
	}
	
	/**
	 * Should remove player.
	 */
	@Test
	public void shouldRemovePlayer() {
		Player player1 = new Player("Krzysztof");		
		gameServer.addPlayer(player1);
		assertTrue(gameServer.findPlayerByName(player1.getName())!=null);
	}

	/**
	 * Creates the game with two players.
	 */
	@Test
	public void createGameWithTwoPlayers() {
		Player player1 = new Player("Piotr");
		Player player2 = new Player("Wojtek");		
		Game game = gameServer.createGame(player1, player2);
		assertTrue(game.playerIn(player1));
		assertTrue(game.playerIn(player2));
	}
	
	/**
	 * Game with computer and player is guess.
	 */
	@Test 
	public void gameWithComputerAndPlayerIsGuess() {
		Player player1 = new Player("Piotr");		
		Game game = gameServer.createGame(player1);
		
		assertTrue(game.getGuessPlayer() == player1);
		assertTrue(game.getWordPlayer().isComputer());
	}
}

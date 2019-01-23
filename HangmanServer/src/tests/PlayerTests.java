
package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import game.Player;
import game.PlayerStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerTests.
 */
public class PlayerTests {
	
	/**
	 * Player gets next id.
	 */
	@Test
	public void playerGetsNextId() {
		Player p1  = new Player("P1");
		Player p2  = new Player("P2");
		
		assertTrue(p1.getPlayerId()> 0);
		assertTrue(p2.getPlayerId() == p1.getPlayerId()+1);
	}
	
	/**
	 * Player is computer and has name.
	 */
	@Test
	public void playerIsComputerAndHasName() {
		Player p1  = Player.createComputerPlayer();		
		
		assertTrue(p1.getPlayerId()> 0);
		assertTrue(p1.isComputer());		
	}
	
	/**
	 * Player ended game has status created.
	 */
	@Test
	public void playerEndedGameHasStatusCreated() {
		Player p1  = Player.createComputerPlayer();
		p1.setStatus(PlayerStatus.PLAYING);
		p1.endGame();		
		assertTrue(p1.getStatus()==PlayerStatus.CREATED);		
	}

	/**
	 * Player no feedback.
	 */
	@Test
	public void playerNoFeedback() {
		Player p1  = Player.createComputerPlayer();
		p1.setStatus(PlayerStatus.PLAYING);
		try {
			Thread.sleep(5_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		assertFalse(p1.noneFeedBack());
		try {
			Thread.sleep(6_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		assertTrue(p1.noneFeedBack());
	}


}

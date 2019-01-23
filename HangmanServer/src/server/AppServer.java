
package server;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import game.Player;
import ws.IClientWebSocket;

// TODO: Auto-generated Javadoc
/**
 * The Class AppServer.
 */
@ApplicationScoped
public class AppServer implements IAppServer {

	/** The Constant CMD_SEP. */
	private static final String CMD_SEP = "#";

	/** The client ws. */
	@Inject
	private IClientWebSocket clientWs;
	
	/** The game server. */
	@Inject
	private IGameServer gameServer;
	
	/**
	 * Send message to client.
	 *
	 * @param player the player
	 * @param operation the operation
	 * @param data the data
	 */
	public void sendMessageToClient(Player player, String operation, String data) {
		if (player==null) { 
			System.out.println("sendMessageToClient - Player NULL!");
		}
		System.out.println("sendMessageToClient "+player.getName()+" > "+operation+" # "+data);
		if (player.isComputer()) {
			System.out.println("Players is the computer (virtual player), messege has not been sent");
			return;
		}
		clientWs.sendToPlayer(player.getPlayerId(), operation + CMD_SEP + data);		
	}
	
	/**
	 * Send message to client.
	 *
	 * @param player the player
	 * @param operation the operation
	 */
	public void sendMessageToClient(Player player, String operation) {
		if (player==null) { 
			System.out.println("sendMessageToClient - Player NULL!");
		}
		System.out.println("sendMessageToClient "+player.getName()+" > "+operation);
		if (player.isComputer()) {
			System.out.println("Players is the computer (virtual player), messege has not been sent");
			return;
		}
		clientWs.sendToPlayer(player.getPlayerId(), operation);		
	}

	/* (non-Javadoc)
	 * @see server.IAppServer#sendLetter(game.Player, java.lang.String)
	 */
	public void sendLetter(Player toPlayer, String letter) {
		sendMessageToClient(toPlayer, Command.CMD_LETTER.toString());
	}

	/* (non-Javadoc)
	 * @see server.IAppServer#sendMessagePlayerDisconnected(game.Player)
	 */
	public void sendMessagePlayerDisconnected(Player toPlayer) {
		sendMessageToClient(toPlayer, Command.CMD_DISCONNECTED.toString(), "");	
	}
	
	/* (non-Javadoc)
	 * @see server.IAppServer#sendMessageOpponentEndGame(game.Player)
	 */
	public void sendMessageOpponentEndGame(Player toPlayer) {
		sendMessageToClient(toPlayer, Command.CMD_OPPONENT_END_GAME.toString());	
	}
	
	/* (non-Javadoc)
	 * @see server.IAppServer#sendGoToPage(game.Player, java.lang.String)
	 */
	public void sendGoToPage(Player toPlayer, String page) {
		sendMessageToClient(toPlayer, Command.CMD_GOTO_PAGE.toString()+"_" + page);	
	}
	
	
	/* (non-Javadoc)
	 * @see server.IAppServer#wordUpdated(game.Player)
	 */
	public void wordUpdated(Player toPlayer) {
		sendMessageToClient(toPlayer, Command.CMD_WORD_UPDATED.toString());
	}

	/* (non-Javadoc)
	 * @see server.IAppServer#sendRefreshListPlayersToAll()
	 */
	public void sendRefreshListPlayersToAll() {
		clientWs.sendToAll(Command.CMD_REFERSH_PLAYERS.toString());	
	}

	/* (non-Javadoc)
	 * @see server.IAppServer#removePlayerById(long)
	 */
	public void removePlayerById(long playerId) {
		gameServer.removePlayer(gameServer.findPlayerById(playerId));
		sendRefreshListPlayersToAll();
	}
	
}

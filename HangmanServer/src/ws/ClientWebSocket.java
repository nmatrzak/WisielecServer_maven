
package ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


import server.IAppServer;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientWebSocket.
 */
@ApplicationScoped
@ServerEndpoint("/play")
public class ClientWebSocket implements IClientWebSocket {

	/** The Constant OPERATION_HELLO. */
	private static final String OPERATION_HELLO = "hello";
	
	/** The Constant OPERATION_BYEBYE. */
	private static final String OPERATION_BYEBYE = "byebye";

	/** The server. */
	// Opis
	@Inject
	private IAppServer server;

	/**
	 * Instantiates a new client web socket.
	 */
	public ClientWebSocket() {
		System.out.println("ClientWebSocket created");
	}

	/** The peers. */
	private static ConcurrentLinkedQueue<Session> peers = new ConcurrentLinkedQueue<>();
	
	/** The players sessions. */
	private static ConcurrentHashMap<String, Session> playersSessions = new ConcurrentHashMap<>();

	/**
	 * On open.
	 *
	 * @param session the session
	 */
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("WS:onOpen::" + session.getId());
		peers.add(session);
	}

	/**
	 * On message.
	 *
	 * @param message the message
	 * @param session the session
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("WS:onMessage:" + message);
		if (server == null) {
			System.out.println("Server is NULL");
		} else {
			System.out.println("WS:Forwarding message to app server");

			if (isMessageByeBye(message)) {
				removePlayerSession(message, session);
			} else if (isMessageHello(message)) {
				updatePlayerSession(message, session);
			}
//			else {
//				String playerName = getPlayerIdBySession(session);
//				if (!playerName.isEmpty() ) {
//				   server.messageReceived(playerName, message);
//				}
//			}
		}
	}

	/**
	 * On error.
	 *
	 * @param t the t
	 */
	@OnError
	public void onError(Throwable t) {
		System.out.println("WS:onError::" + t.getMessage());
	}

	/**
	 * Checks if is message hello.
	 *
	 * @param message the message
	 * @return true, if is message hello
	 */
	private boolean isMessageHello(String message) {
		return OPERATION_HELLO.equals(getOperationFromMessage(message));
	}

	/**
	 * Checks if is message bye bye.
	 *
	 * @param message the message
	 * @return true, if is message bye bye
	 */
	private boolean isMessageByeBye(String message) {
		return OPERATION_BYEBYE.equals(getOperationFromMessage(message));
	}

	/**
	 * Update player session.
	 *
	 * @param message the message
	 * @param session the session
	 */
	private void updatePlayerSession(String message, Session session) {
		System.out.println("WS:updatePlayerSession " + message + " > session: " + session.getId());
		String playerId = getDataFromMessage(message);
		playersSessions.put(playerId, session);
		synchronizeSessionPlayers();
	}

	/**
	 * On close.
	 *
	 * @param session the session
	 */
	@OnClose
	public void onClose(Session session) {
		System.out.println("WS:onClose::" + session.getId());
		String playerId = getPlayerIdBySession(session);
		playersSessions.remove(playerId);
		peers.remove(session);
	}

	/**
	 * Removes the player session.
	 *
	 * @param message the message
	 * @param session the session
	 */
	private void removePlayerSession(String message, Session session) {
		System.out.println("removePlayerSession " + message + " > session: " + session.getId());
		String playerId = getDataFromMessage(message);
		playersSessions.remove(playerId);
		peers.remove(session);
		synchronizeSessionPlayers();
		server.removePlayerById(Long.valueOf(playerId));
	}

	/**
	 * Gets the operation from message.
	 *
	 * @param message the message
	 * @return the operation from message
	 */
	private String getOperationFromMessage(String message) {
		String[] msgItems = message.split("#");
		if (msgItems.length > 0) {
			return msgItems[0];
		} else {
			return "";
		}
	}

	/**
	 * Gets the data from message.
	 *
	 * @param message the message
	 * @return the data from message
	 */
	private String getDataFromMessage(String message) {
		String[] msgItems = message.split("#");
		if (msgItems.length > 1) {
			return msgItems[1];
		} else {
			return "";
		}
	}

	/**
	 * Synchronize session players.
	 */
	private void synchronizeSessionPlayers() {
		System.out.println("WS:synchronizeSessionPlayers ");
		List<String> playerSessionsToRemove = new ArrayList<>();
		for (Map.Entry<String, Session> entry : playersSessions.entrySet()) {
			if (peerNotExists(entry.getValue())) {
				playerSessionsToRemove.add(entry.getKey());
			}
		}
		playerSessionsToRemove.forEach(playerId -> playersSessions.remove(playerId));
	}

	/**
	 * Peer not exists.
	 *
	 * @param session the session
	 * @return true, if successful
	 */
	private boolean peerNotExists(Session session) {
		return !peers.stream().anyMatch(peer -> peer.getId().equals(session.getId()));
	}

	/**
	 * Send.
	 *
	 * @param session the session
	 * @param msg the msg
	 */
	private void send(Session session, String msg) {
		System.out.println("WS:send (toSession)::" + session.getId() + " > " + msg);
		try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see ws.IClientWebSocket#sendToPlayer(long, java.lang.String)
	 */
	public void sendToPlayer(long playerId, String msg) {
		System.out.println("WS:sendToPlayer::" + playerId + " > " + msg);
		Session session = getSessionByPlayerId(String.valueOf(playerId));
		if (session != null) {
			send(session, msg);
		}
	}

	/* (non-Javadoc)
	 * @see ws.IClientWebSocket#sendToAll(java.lang.String)
	 */
	public void sendToAll(String msg) {
		System.out.println("WS:sendToAll::" + msg);
		peers.forEach(session -> {
			send(session, msg);
		});
	}

	/**
	 * Gets the session by player id.
	 *
	 * @param playerId the player id
	 * @return the session by player id
	 */
	private Session getSessionByPlayerId(String playerId) {
		return playersSessions.get(playerId);
	}

	/**
	 * Gets the player id by session.
	 *
	 * @param session the session
	 * @return the player id by session
	 */
	private String getPlayerIdBySession(Session session) {
		System.out.println("WS:getPlayerNameBySession::" + session.getId());
		if (session != null) {
			for (Map.Entry<String, Session> entry : playersSessions.entrySet()) {
				if (entry.getValue().getId().equals(session.getId())) {
					return entry.getKey();
				}
			}
		}
		return new String("");
	}

}

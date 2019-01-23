package ws;

// TODO: Auto-generated Javadoc
/**
 * The Interface IClientWebSocket.
 */
public interface IClientWebSocket {
	
	/**
	 * Send to all.
	 *
	 * @param msg the msg
	 */
	void sendToAll(String msg);	
    
    /**
     * Send to player.
     *
     * @param playerId the player id
     * @param msg the msg
     */
    void sendToPlayer(long playerId, String msg);

}

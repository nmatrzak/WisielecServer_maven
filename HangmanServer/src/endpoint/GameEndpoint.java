
package endpoint;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import dto.GameDto;
import game.Game;
import server.IGameServer;
import service.IPlayerService;

// TODO: Auto-generated Javadoc
/**
 * The Class GameEndpoint.
 */
@ApplicationScoped
@Path("/game")
public class GameEndpoint {
	
	/** The player service. */
	@Inject	
	private IPlayerService playerService;
	
	/** The game server. */
	@Inject
	private IGameServer gameServer;
	
	/** The gson. */
	private Gson gson;
	
	/**
	 * Instantiates a new game endpoint.
	 */
	public GameEndpoint() {
		gson = new Gson();
	}	
	
	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	@GET	
	@Produces(MediaType.TEXT_PLAIN)
	public String getList() {						
		System.out.println("getList");
		return toJson(gameServer.getListOfGames());
	}	
	
	/**
	 * Createt game.
	 *
	 * @param playerId the player id
	 * @param opponentId the opponent id
	 * @return the string
	 */
	@GET
	@Path("{playerId}/{opponentId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String createtGame(@PathParam("playerId") long playerId, @PathParam("opponentId") long opponentId) {
		System.out.println("GameEndpoint.createtGame "+playerId+" vs "+opponentId);		
		Game game = playerService.createGame(playerId, opponentId);
		if (game.getGuessPlayer().getPlayerId()==playerId) {
			return "guess";			
		}
		return "word";
	}	
	
	/**
	 * Gets the game.
	 *
	 * @param playerId the player id
	 * @return the game
	 */
	@GET
	@Path("gameByPlayerId/{playerId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getGame(@PathParam("playerId") long playerId) {
		System.out.println("GameEndpoint.getGame for player id = "+playerId);		
		GameDto game = playerService.getGame(playerId);
		System.out.println("GameEndpoint.getGame is = "+game);		
		return Optional
				.ofNullable(  game )
							.map(this::toJson)
							.map( json -> Response.ok(json,MediaType.TEXT_PLAIN).build() )
							.orElse( Response.status(Response.Status.NOT_FOUND).entity("GAME FOR USER NOT EXIST!" ).build() )
							;
	}
	
//	@GET
//	@Path("endGame/{playerId}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response endGame(@PathParam("playerId") long playerId) {
//		System.out.println("GameEndpoint.endGame for player id = "+playerId);		
//		GameDto game = playerService.endGame(playerId);	 		
//		return Optional
//				.ofNullable(  game )
//							.map(this::toJson)
//							.map( json -> Response.ok(json,MediaType.TEXT_PLAIN).build() )
//							.orElse( Response.status(Response.Status.NOT_FOUND).entity("GAME FOR USER NOT EXIST!" ).build() )
//							;
//	}
	
	
	/**
 * Send letter and return game.
 *
 * @param playerId the player id
 * @param letter the letter
 * @return the response
 */
@GET
	@Path("sendLetter/{playerId}/{letter}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sendLetterAndReturnGame(@PathParam("playerId") long playerId, @PathParam("letter") String letter) {
		System.out.println("GameEndpoint.getGame "+playerId);		
		return Optional
				.ofNullable( playerService.updateGappedWordLetter(playerId, letter) )
							.map(this::toJson)
							.map( json -> Response.ok(json,MediaType.TEXT_PLAIN).build() )
							.orElse( Response.status(Response.Status.NOT_FOUND).entity("GAME FOR USER NOT EXIST!" ).build() )
							;
	}	
	
	/**
	 * Sets the word and return game.
	 *
	 * @param playerId the player id
	 * @param word the word
	 * @return the response
	 */
	@GET
	@Path("updateWord/{playerId}/{word}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response setWordAndReturnGame(@PathParam("playerId") long playerId, @PathParam("word") String word) {
		System.out.println("GameEndpoint.getGame "+playerId);		
		return Optional
				.ofNullable( playerService.updateWord(playerId, word) )
							.map(this::toJson)
							.map( json -> Response.ok(json,MediaType.TEXT_PLAIN).build() )
							.orElse( Response.status(Response.Status.NOT_FOUND).entity("GAME FOR USER NOT EXIST!" ).build() )
							;
	}	
	
	/**
	 * To json.
	 *
	 * @param o the o
	 * @return the string
	 */
	private String toJson(Object o) {
		return gson.toJson(o);
	}

}

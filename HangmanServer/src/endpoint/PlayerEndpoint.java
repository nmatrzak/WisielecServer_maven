
package endpoint;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import dto.PlayerDto;
import service.IPlayerService;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerEndpoint.
 */
@ApplicationScoped
@Path("/players")
public class PlayerEndpoint {
	
	/** The player service. */
	@Inject	
	private IPlayerService playerService;	
	
	/** The gson. */
	private Gson gson;
	
	/**
	 * Instantiates a new player endpoint.
	 */
	public PlayerEndpoint() {
		gson = new Gson();
	}	
	
	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getList() {
		System.out.println("PlayersEndpoint.getList");		
		List<PlayerDto> players = playerService.getPlayersDto();
//		players.stream().forEach(p->System.out.println("PlayersEndpoint.getList > "+p));
		return toJson(players);
	}
	
	/**
	 * Gets the player by name.
	 *
	 * @param name the name
	 * @return the player by name
	 */
	@GET
	@Path("byName/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getPlayerByName(@PathParam("name") String name) {
		System.out.println("PlayersEndpoint.getPlayerByName "+name);
		return Optional
				.ofNullable( playerService.getPlayer(name) )
							.map(this::toJson)
							.map( dto -> Response.ok(dto,MediaType.TEXT_PLAIN).build() )
							.orElse( Response.status(Response.Status.NOT_FOUND).entity("USER NOT EXIST!" ).build() )
							;
	}
	
	/**
	 * Alive.
	 *
	 * @param id the id
	 * @return the string
	 */
	@GET
	@Path("alive/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String alive(@PathParam("id") long id) {
//		System.out.println("PlayersEndpoint.alive "+id);
		playerService.playerAlive(id);
		return "OK";					
	}
	
	/**
	 * Gets the player by id.
	 *
	 * @param id the id
	 * @return the player by id
	 */
	@GET
	@Path("byId/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getPlayerById(@PathParam("id") long id) {
		System.out.println("PlayersEndpoint.getPlayerById "+id);
		return Optional
				.ofNullable( playerService.getPlayer(id) )
							.map(this::toJson)
							.map( dto -> Response.ok(dto,MediaType.TEXT_PLAIN).build() )
							.orElse( Response.status(Response.Status.NOT_FOUND).entity("USER NOT EXIST!" ).build() )
							;
	}
	
	/**
	 * Creates the player.
	 *
	 * @param userName the user name
	 * @return the response
	 */
	@POST
	@Path("{userName}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response createPlayer(@PathParam("userName") String userName) {		
		System.out.println("PlayersEndpoint.createPlayer "+userName);
		return Optional
				.ofNullable( playerService.createPlayer(userName) )
							.map(this::toJson)
							.map( dto -> Response.status(Response.Status.CREATED).entity( dto ).build() )
							.orElse( Response.status(Response.Status.CONFLICT).entity("USER EXIST!" ).build() );
	}	
	
	/**
	 * Removes the player.
	 *
	 * @param id the id
	 * @return the response
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response removePlayer(@PathParam("id") long id) {		
		System.out.println("PlayersEndpoint.removePlayer "+id);
		return Optional
				.ofNullable( playerService.removePlayer(id) )
							.map(this::toJson)
							.map( dto -> Response.status(Response.Status.CREATED).entity( dto ).build() )
							.orElse( Response.status(Response.Status.NOT_FOUND).entity("USER NOT EXIST!" ).build() );
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


package server;

// TODO: Auto-generated Javadoc
/**
 * The Enum Command.
 */
public enum Command {
	
	/** The cmd hello. */
	CMD_HELLO("hello")	
	, 
 /** The cmd letter. */
 CMD_LETTER("letter")	
	, 
 /** The cmd disconnected. */
 CMD_DISCONNECTED("disconnected")
	, 
 /** The cmd opponent end game. */
 CMD_OPPONENT_END_GAME("opponnent_end_game")
	, 
 /** The cmd refersh players. */
 CMD_REFERSH_PLAYERS("refresh_player_list")
	, 
 /** The cmd word updated. */
 CMD_WORD_UPDATED("word_updated")
	, 
 /** The cmd goto page guess. */
 CMD_GOTO_PAGE_GUESS("goto_guess")
	, 
 /** The cmd goto page. */
 CMD_GOTO_PAGE("goto")
	, 
 /** The cmd echo. */
 CMD_ECHO("echo")
	, 
 /** The cmd unknown. */
 CMD_UNKNOWN("");
	
	/** The cmd. */
	String cmd;
	
	/**
	 * Instantiates a new command.
	 *
	 * @param cmd the cmd
	 */
	Command(String cmd) {
		this.cmd = cmd;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return cmd;
	}
	
	/**
	 * Resolve.
	 *
	 * @param cmd the cmd
	 * @return the command
	 */
	public static Command resolve(String cmd) {
		for(Command c : Command.values()) {
			if (c.cmd.equals(cmd)) { return c; }
		}
		return CMD_UNKNOWN;
	}
	
}


package game;

import java.util.Set;

import jersey.repackaged.com.google.common.collect.Sets;

// TODO: Auto-generated Javadoc
/**
 * The Enum PlayerStatus.
 */
public enum PlayerStatus {
	
	/** The invisible. */
	INVISIBLE, /** The created. */
 CREATED, /** The playing. */
 PLAYING;//;
	
	/** The busy statuses. */
public static Set<PlayerStatus> busyStatuses = Sets.immutableEnumSet( PLAYING );//, WON, LOST );

}

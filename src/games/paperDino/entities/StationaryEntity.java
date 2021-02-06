package games.paperDino.entities;

import games.paperDino.Entity;
import games.paperDino.World;

abstract public class StationaryEntity extends Entity {

	public StationaryEntity(World world, int[] position) {
		super(world, position);
	}
}

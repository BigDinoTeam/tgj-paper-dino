package games.paperDino.entities.stationary;

import games.paperDino.World;
import games.paperDino.entities.StationaryEntity;

abstract public class Nest extends StationaryEntity {

	private float[] paperProbabilities;

	public Nest(World world, int[] position) {
		super(world, position);
	}
}

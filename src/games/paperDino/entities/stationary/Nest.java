package games.paperDino.entities.stationary;

import org.newdawn.slick.Image;

import games.paperDino.World;
import games.paperDino.entities.StationaryEntity;

abstract public class Nest extends StationaryEntity {

	private static Image sprite;
	private float[] paperProbabilities;

	public Nest(World world, int[] position) {
		super(world, Nest.sprite, position);
	}
}

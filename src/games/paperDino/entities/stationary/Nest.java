package games.paperDino.entities.stationary;

import org.newdawn.slick.Image;

import games.paperDino.entities.StationaryEntity;

abstract public class Nest extends StationaryEntity {

	private static Image sprite;
	private float[] paperProbabilities;

	public Nest(int[] position) {
		super(Nest.sprite, position);
	}
}

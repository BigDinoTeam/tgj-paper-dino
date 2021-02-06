package games.paperDino.entities.stationary;

import games.paperDino.entities.StationaryEntity;

abstract public class Nest extends StationaryEntity {

	private float[] paperProbabilities;

	public Nest(int[] position) {
		super(position);
	}
}

package games.paperDino.entities.stationary;

import games.paperDino.entities.StationaryEntity;
import games.paperDino.entities.dynamic.dinos.AI;

public class Building extends StationaryEntity {

	private int type;
	private AI dino;

	public Building(int type, int[] position) {
		super(position);
		this.type = type;
	}
}

package games.paperDino.entities.stationary;

import games.paperDino.World;
import games.paperDino.entities.StationaryEntity;
import games.paperDino.entities.dynamic.dinos.AI;

public class Building extends StationaryEntity {

	private int type;
	private AI dino;

	public Building(World world, int type, int[] position) {
		super(world, position);
		this.type = type;
	}
}

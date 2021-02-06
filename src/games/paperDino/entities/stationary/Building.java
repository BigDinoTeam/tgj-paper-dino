package games.paperDino.entities.stationary;

import org.newdawn.slick.Image;

import games.paperDino.World;
import games.paperDino.entities.StationaryEntity;
import games.paperDino.entities.dynamic.dinos.AI;

public class Building extends StationaryEntity {

	private static Image sprite;
	private int type;
	private AI dino;

	public Building(World world, int type, int[] position) {
		super(world, Building.sprite, position);
		this.type = type;
	}
}

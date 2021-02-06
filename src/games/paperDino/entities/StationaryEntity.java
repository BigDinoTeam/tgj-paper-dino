package games.paperDino.entities;

import org.newdawn.slick.Image;

import games.paperDino.Entity;
import games.paperDino.World;

abstract public class StationaryEntity extends Entity {

	public StationaryEntity(World world, Image sprite, int[] position) {
		super(world, sprite, position);
	}
}

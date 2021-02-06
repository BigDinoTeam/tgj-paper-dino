package games.paperDino.entities;

import org.newdawn.slick.Image;

import games.paperDino.Entity;

abstract public class StationaryEntity extends Entity {

	public StationaryEntity(Image sprite, int[] position) {
		super(sprite, position);
	}
}

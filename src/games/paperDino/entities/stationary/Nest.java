package games.paperDino.entities.stationary;

import org.newdawn.slick.Image;

import app.AppLoader;

import games.paperDino.World;
import games.paperDino.entities.StationaryEntity;

abstract public class Nest extends StationaryEntity {

	private static Image sprite;

	static {
		Nest.sprite = AppLoader.loadPicture("/images/houses/maison_rouge.png");
	}

	private float[] paperProbabilities;

	public Nest(World world, int[] position) {
		super(world, Nest.sprite, position);
	}
}

package games.paperDino.entities.stationary;

import org.newdawn.slick.Image;

import app.AppLoader;

import games.paperDino.World;
import games.paperDino.entities.StationaryEntity;
import games.paperDino.entities.dynamic.dinos.AI;

public class Building extends StationaryEntity {

	private static Image sprite;

	static {
		Building.sprite = AppLoader.loadPicture("/images/houses/main_cyan.png");
	}

	private int type;
	private AI dino;

	public Building(World world, int type, int[] position) {
		super(world, Building.sprite, position);
		this.type = type;
	}
}

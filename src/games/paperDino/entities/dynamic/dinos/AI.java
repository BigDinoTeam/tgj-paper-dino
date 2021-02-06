package games.paperDino.entities.dynamic.dinos;

import org.newdawn.slick.Image;

import games.paperDino.Activity;
import games.paperDino.SpeciesColor;
import games.paperDino.World;
import games.paperDino.entities.stationary.Building;
import games.paperDino.entities.dynamic.Dino;

public class AI extends Dino {

	private static Image sprite;
	private Building building;
	private SpeciesColor color;
	private Activity activity;
	private boolean pacified;

	public AI(World world, int[] position) {
		super(world, AI.sprite, position);
	}

}

package games.paperDino.entities.dynamic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import games.paperDino.SpeciesColor;
import games.paperDino.World;
import games.paperDino.entities.DynamicEntity;

public class Paper extends DynamicEntity {

	private static Image sprite;
	private SpeciesColor color;
	private float[] speed;

	public Paper(World world, int[] position) {
		super(world, Paper.sprite, position);
	}

	@Override
	public int checkInput(GameContainer container, int delta) {
		return 0; //TODO : forcer un move() vers la même direction qu'au lancé (initialisation) de Paper
	}
}

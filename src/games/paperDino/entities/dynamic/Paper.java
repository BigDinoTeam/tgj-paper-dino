package games.paperDino.entities.dynamic;

import games.paperDino.SpeciesColor;
import games.paperDino.World;
import games.paperDino.entities.DynamicEntity;

public class Paper extends DynamicEntity {

	private SpeciesColor color;
	private float[] speed;

	public Paper(World world, int[] position) {
		super(world, position);
	}

}

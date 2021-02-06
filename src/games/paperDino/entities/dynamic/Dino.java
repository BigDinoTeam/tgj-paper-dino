package games.paperDino.entities.dynamic;

import games.paperDino.World;
import games.paperDino.entities.DynamicEntity;

abstract public class Dino extends DynamicEntity {

	public Dino(World world, int[] position) {
		super(world, position);
	}

}

package games.paperDino.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import games.paperDino.Entity;
import games.paperDino.World;

abstract public class DynamicEntity extends Entity {

	public DynamicEntity(World world, Image sprite, int[] position) {
		super(world, sprite, position);
	}

	abstract public void update(GameContainer container, StateBasedGame game, int delta);

	abstract public int checkInput(GameContainer container, int delta);

}

package games.paperDino.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import games.paperDino.Entity;
import games.paperDino.World;

abstract public class DynamicEntity extends Entity {

	private World world;

	public DynamicEntity(World world, int[] position) {
		super(position);
		this.world = world;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {}

	public void move() {}

}

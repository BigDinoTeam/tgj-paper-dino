package games.paperDino.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import games.paperDino.Entity;
import games.paperDino.World;

abstract public class DynamicEntity extends Entity {

	private World world;

	public DynamicEntity(World world) {}

	public void update(GameContainer container, StateBasedGame game, int delta) {}

	public void move() {}

}

package games.paperDino.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import games.paperDino.Entity;
import games.paperDino.Piece;
import games.paperDino.World;

abstract public class DynamicEntity extends Entity {

	private World world;

	public DynamicEntity(World world, Image sprite, int[] position) {
		super(sprite, position);
		this.world = world;
		this.setPieces(new Piece[][]{
			new Piece[]{
				new Piece(this, new int[]{0, 0}, false),
			},
		});
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {}

	public void move() {}

}

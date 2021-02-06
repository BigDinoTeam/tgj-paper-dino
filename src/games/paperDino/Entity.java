package games.paperDino;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

abstract public class Entity {

	public static Image sprite;
	private Piece[][] pieces;
	private int[] position;

	public Entity(int[] position) {
		this.position = position;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {}

}

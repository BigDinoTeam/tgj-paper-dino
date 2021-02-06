package games.paperDino;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

abstract public class Entity {

	public static Image sprite;
	private Piece[][] pieces;
	private int[] position;

	public Entity(World world, int[] position) {
		this.position = position;
		world.getGrid().insert(this.pieces, this.position); // Insère les pièces de l'entité dans la grille
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {}

}

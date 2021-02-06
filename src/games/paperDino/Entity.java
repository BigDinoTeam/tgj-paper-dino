package games.paperDino;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

abstract public class Entity {

	private World world;
	private Image sprite;
	private Piece[][] pieces;
	private int[] position;

	public Entity(World world, Image sprite, int[] position) {
		this.world = world;
		this.sprite = sprite;
		this.position = position;
		this.pieces = new Piece[0][0];
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {}

	public Image getSprite() {
		return this.sprite;
	}

	public void setPieces(Piece[][] pieces) {
		this.pieces = pieces;
		this.world.getGrid().insert(this.pieces, this.position); // Insère les pièces de l'entité dans la grille
	}

	public Piece[][] getPieces() {
		return this.pieces;
	}

	public int[] getPosition() {
		int i = this.position[0];
		int j = this.position[1];
		return new int[]{i, j};
	}

	public int[] getSize() {
		int li = this.pieces.length;
		int lj = this.pieces[0].length;
		return new int[]{li, lj};
	}

}

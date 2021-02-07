package games.paperDino;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

abstract public class Entity {

	private World world;
	private Image sprite;
	private Piece[][] pieces;
	private int[] position;
	private float[] translation;

	public Entity(World world, Image sprite, int[] position) {
		this.world = world;
		this.sprite = sprite;
		this.position = position;
		this.translation = new float[2];
		this.pieces = new Piece[0][0];
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {}

	public World getWorld() {
		return this.world;
	}

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

	public void setPosition(int[] position) {
		this.position = position;
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

	public void setTranslation(float[] translation) {
		this.translation = translation;
	}

	public float[] getTranslation() {
		float i = this.translation[0];
		float j = this.translation[1];
		return new float[]{i, j};
	}

}

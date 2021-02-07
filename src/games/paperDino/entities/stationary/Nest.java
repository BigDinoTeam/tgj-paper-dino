package games.paperDino.entities.stationary;

import org.newdawn.slick.Image;

import app.AppLoader;

import games.paperDino.Piece;
import games.paperDino.World;
import games.paperDino.entities.StationaryEntity;

public class Nest extends StationaryEntity {

	private static Image sprite;

	static {
		Nest.sprite = AppLoader.loadPicture("/images/colidino.png");
	}

	private float[] paperProbabilities;

	public Nest(World world, int[] position) {
		super(world, Nest.sprite, position);
		this.setPieces(new Piece[][]{
			new Piece[]{
				new Piece(this, new int[]{0, 0}, true),
				new Piece(this, new int[]{0, 1}, true),
				new Piece(this, new int[]{0, 2}, true),
			},
			new Piece[]{
				new Piece(this, new int[]{1, 0}, false),
				new Piece(this, new int[]{1, 1}, false),
				new Piece(this, new int[]{1, 2}, true),
			},
		});
	}
}

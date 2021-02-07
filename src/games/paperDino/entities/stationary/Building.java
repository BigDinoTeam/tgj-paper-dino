package games.paperDino.entities.stationary;

import org.newdawn.slick.Image;

import app.AppLoader;

import games.paperDino.Piece;
import games.paperDino.SpeciesColor;
import games.paperDino.World;
import games.paperDino.entities.StationaryEntity;
import games.paperDino.entities.dynamic.dinos.AI;

public class Building extends StationaryEntity {

	private static Image sprite;

	static {
		Building.sprite = AppLoader.loadPicture("/images/houses/maison_cyan.png");
	}

	private int type;
	private SpeciesColor color;

	public Building(World world, int type, int[] position, SpeciesColor color) {
		super(world, Building.sprite, position);
		this.type = type;
		this.setPieces(new Piece[][]{
			new Piece[]{
				new Piece(this, new int[]{0, 0}, true),
				new Piece(this, new int[]{0, 1}, true),
				new Piece(this, new int[]{0, 2}, true),
				new Piece(this, new int[]{0, 0}, true),
				new Piece(this, new int[]{0, 1}, true),
				new Piece(this, new int[]{0, 2}, true),
			},
			new Piece[]{
				new Piece(this, new int[]{1, 0}, false),
				new Piece(this, new int[]{1, 1}, false),
				new Piece(this, new int[]{1, 2}, false),
				new Piece(this, new int[]{1, 0}, false),
				new Piece(this, new int[]{1, 1}, false),
				new Piece(this, new int[]{1, 2}, false),
			},
			new Piece[]{
				new Piece(this, new int[]{2, 0}, false),
				new Piece(this, new int[]{2, 1}, false),
				new Piece(this, new int[]{2, 2}, false),
				new Piece(this, new int[]{2, 0}, false),
				new Piece(this, new int[]{2, 1}, false),
				new Piece(this, new int[]{2, 2}, false),
			},
			new Piece[]{
				new Piece(this, new int[]{3, 0}, false),
				new Piece(this, new int[]{3, 1}, false),
				new Piece(this, new int[]{3, 2}, false),
				new Piece(this, new int[]{3, 0}, false),
				new Piece(this, new int[]{3, 1}, false),
				new Piece(this, new int[]{3, 2}, false),
			},
		});
		this.color = color;
	}

	public SpeciesColor getColor() {
		return this.color;
	}
}

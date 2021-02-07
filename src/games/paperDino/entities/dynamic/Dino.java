package games.paperDino.entities.dynamic;

import org.newdawn.slick.Image;

import games.paperDino.Piece;
import games.paperDino.World;
import games.paperDino.entities.DynamicEntity;

abstract public class Dino extends DynamicEntity {

	public Dino(World world, Image sprite, int[] position) {
		super(world, sprite, position);
		this.setPieces(new Piece[][]{
			new Piece[]{
				new Piece(this, new int[]{0, 0}, false),
			},
		});
	}

}

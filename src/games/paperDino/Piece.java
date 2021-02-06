package games.paperDino;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public class Piece {

	private Entity entity;
	private int[] position;
	private boolean walkable;

	public Piece(Entity entity, int[] position, boolean walkable) {
		this.entity = entity;
		this.position = position;
		this.walkable = walkable;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		int[] position = this.entity.getPosition();
		int[] size = this.entity.getSize();
		Image sprite = this.entity.getSprite();
		int di1 = position[0];
		int dj1 = position[1];
		int di2 = di1 + 32;
		int dj2 = dj1 + 32;
		int si1 = this.position[0];
		int sj1 = this.position[1];
		int si2 = si1 + sprite.getHeight() / size[0];
		int sj2 = sj1 + sprite.getWidth() / size[1];
		context.drawImage(sprite, dj1, di1, dj2, di2, sj1, si1, sj2, si2);
	}

}

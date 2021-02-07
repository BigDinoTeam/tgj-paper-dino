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
		int clipHeight = sprite.getHeight() / size[0];
		int clipWidth = sprite.getWidth() / size[1];
		int cellSize = 128;
		int di = position[0] + this.position[0];
		int dj = position[1] + this.position[1];
		int si = this.position[0];
		int sj = this.position[1];
		context.drawImage(sprite, dj * cellSize, di * cellSize, (dj + 1) * cellSize, (di + 1) * cellSize, sj * clipWidth, si * clipHeight, (sj + 1) * clipWidth, (si + 1) * clipHeight);
	}

	public Entity getEntity() {
		return this.entity;
	}

	public boolean isWalkable() {
		return walkable;
	}

}

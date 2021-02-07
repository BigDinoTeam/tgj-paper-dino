package games.paperDino;

import games.paperDino.entities.dynamic.dinos.AI;
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
		float[] translation = this.entity.getTranslation();
		Image sprite = this.entity.getSprite();
		int clipHeight = sprite.getHeight() / size[0];
		int clipWidth = sprite.getWidth() / size[1];
		int cellSize = Cell.size;
		float di = this.position[0] + position[0] + translation[0];
		float dj = this.position[1] + position[1] + translation[1];
		int si = this.position[0];
		int sj = this.position[1];
		context.drawImage(sprite, dj * cellSize, di * cellSize, (dj + 1) * cellSize, (di + 1) * cellSize, sj * clipWidth, si * clipHeight, (sj + 1) * clipWidth, (si + 1) * clipHeight);
		if(getEntity() instanceof AI){
			AI entityAI = (AI) getEntity();
			if (entityAI.isPacified()){
				int bubbleHeight = AI.bubblePacifiedSprite.getHeight();
				int bubbleWidth = AI.bubblePacifiedSprite.getWidth();
				context.drawImage(AI.bubblePacifiedSprite, (dj + 0.5f) * cellSize, (di - 0.5f) * cellSize, (dj + 1.5f) * cellSize, (di + 0.5f) * cellSize, 0, 0, bubbleWidth, bubbleHeight); // dépasse un peu en haut et à gauche de la pièce (pour être visible)
			}
		}
	}

	public Entity getEntity() {
		return this.entity;
	}

	public boolean isWalkable() {
		return walkable;
	}

}

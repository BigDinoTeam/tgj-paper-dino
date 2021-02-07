package games.paperDino.entities.dynamic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.paperDino.Grid;
import games.paperDino.Piece;
import games.paperDino.SpeciesColor;
import games.paperDino.World;
import games.paperDino.entities.DynamicEntity;

public class Paper extends DynamicEntity {

	private static Image sprite;
	private static float speed;

	static {
		Paper.sprite = AppLoader.loadPicture("/images/houses/maison_vert.png");
		Paper.speed = 5;
	}

	private SpeciesColor color;
	private int[] initialPosition;
	private int[] finalPosition;
	private float initialCountdown;
	private float countdown;

	public Paper(World world, int[] initialPosition, int[] finalPosition) {
		super(world, Paper.sprite, initialPosition);
		this.initialPosition = initialPosition;
		this.finalPosition = finalPosition;
		float distance = (float) Math.sqrt(Math.pow(finalPosition[0] - initialPosition[0], 2) + Math.pow(finalPosition[1] - initialPosition[1], 2));
		this.countdown = this.initialCountdown = distance / speed * 1000;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		this.countdown -= delta;
		if (this.countdown <= 0) {
			return;
		}
		int[] initialPosition = this.initialPosition;
		int[] finalPosition = this.finalPosition;
		float initialCountdown = this.initialCountdown;
		float countdown = this.countdown;
		float i = (initialPosition[0] * countdown + finalPosition[0] * (initialCountdown - countdown)) / initialCountdown;
		float j = (initialPosition[1] * countdown + finalPosition[1] * (initialCountdown - countdown)) / initialCountdown;
		int[] oldPosition = this.getPosition();
		int[] newPosition = new int[]{
			(int) i,
			(int) j,
		};
		float[] translation = new float[]{
			i - newPosition[0],
			j - newPosition[1],
		};
		this.setTranslation(translation);
		if (oldPosition[0] == newPosition[0] && oldPosition[1] == newPosition[1]) {
			return;
		}
		this.setPosition(newPosition);
		Grid grid = this.getWorld().getGrid();
		Piece[][] pieces = this.getPieces();
		grid.extract(pieces, oldPosition);
		grid.insert(pieces, newPosition);
	}

}

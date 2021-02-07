package games.paperDino.entities.dynamic;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.paperDino.Cell;
import games.paperDino.Entity;
import games.paperDino.Grid;
import games.paperDino.Piece;
import games.paperDino.SpeciesColor;
import games.paperDino.World;
import games.paperDino.entities.DynamicEntity;
import games.paperDino.entities.dynamic.dinos.Player;

public class Paper extends DynamicEntity {

	private static Image[] sprites;
	private static float speed;

	static {
		Paper.sprites = new Image[]{
			AppLoader.loadPicture("/images/prospectus/prospectus-universal.png"),
			AppLoader.loadPicture("/images/prospectus/prospectus-red.png"),
			AppLoader.loadPicture("/images/prospectus/prospectus-yellow.png"),
			AppLoader.loadPicture("/images/prospectus/prospectus-green.png"),
			AppLoader.loadPicture("/images/prospectus/prospectus-blue.png"),
		};
		Paper.speed = 5;
	}

	private SpeciesColor color;
	private int[] initialPosition;
	private int[] finalPosition;
	private float initialCountdown;
	private float countdown;

	public Paper(World world, SpeciesColor color, int[] initialPosition, int[] finalPosition) {
		super(world, Paper.sprites[color.ordinal()], initialPosition);
		this.setPieces(new Piece[][]{
			new Piece[]{
				new Piece(this, new int[]{0, 0}, true),
			},
		});
		this.color = color;
		this.initialPosition = initialPosition;
		this.finalPosition = finalPosition;
		float distance = (float) Math.sqrt(Math.pow(finalPosition[0] - initialPosition[0], 2) + Math.pow(finalPosition[1] - initialPosition[1], 2));
		this.countdown = this.initialCountdown = distance / speed * 1000;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		int[] oldPosition = this.getPosition();
		World world = this.getWorld();
		Grid grid = world.getGrid();
		Cell cell = grid.getCells()[oldPosition[0]][oldPosition[1]];
		List<Piece> cellPieces = cell.getPieces();
		for (Piece piece: cellPieces) {
			Entity entity = piece.getEntity();
			if (!piece.isWalkable() && !(entity instanceof Player)) {
				// TODO: collision
				this.setPieces(null);
				world.removeDynamicEntity(this);
				return;
			}
		}
		if (this.initialCountdown == 0) {
			return;
		}
		this.countdown -= delta;
		if (this.countdown < 0) {
			this.countdown = 0;
		}
		int[] initialPosition = this.initialPosition;
		int[] finalPosition = this.finalPosition;
		float initialCountdown = this.initialCountdown;
		float countdown = this.countdown;
		float i = (initialPosition[0] * countdown + finalPosition[0] * (initialCountdown - countdown)) / initialCountdown;
		float j = (initialPosition[1] * countdown + finalPosition[1] * (initialCountdown - countdown)) / initialCountdown;
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
		if (countdown == 0) {
			this.initialCountdown = 0;
		}
	}

	@Override
	public int checkInput(GameContainer container, int delta) {
		return 0; //TODO : forcer un move() vers la même direction qu'au lancé (initialisation) de Paper
	}
}

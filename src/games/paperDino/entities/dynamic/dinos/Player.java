package games.paperDino.entities.dynamic.dinos;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.paperDino.Piece;
import games.paperDino.SpeciesColor;
import games.paperDino.World;
import games.paperDino.entities.dynamic.Dino;
import games.paperDino.entities.dynamic.Paper;

public class Player extends Dino {

	private static Image sprite;

	static {
		Player.sprite = AppLoader.loadPicture("/images/houses/maison_orange.png");
	}

	private int[] paperCounts;
	private int[] paperMaxCounts;
	private SpeciesColor color;
	private int score;

	public Player(World world, int[] position) {
		super(world, Player.sprite, position);
		this.setPieces(new Piece[][]{
			new Piece[]{
				new Piece(this, new int[]{0, 0}, true),
			},
		});
		this.paperCounts = new int[]{
			2,
			5,
			5,
			5,
			5,
		};
		this.color = SpeciesColor.universal;
		this.score = 0;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		super.update(container, game, delta);
		Input input = container.getInput();
		if (input.isMousePressed(0)) {
			int cellSize = 128;
			int[] initialPosition = this.getPosition();
			int[] finalPosition = new int[]{
				input.getMouseY() / cellSize,
				input.getMouseX() / cellSize,
			};
			this.throwPaper(initialPosition, finalPosition);
		}
	}

	public void collectPapers() {}

	public void selectPaper() {}

	public void throwPaper(int[] initialPosition, int[] finalPosition) {
		if (initialPosition[0] == finalPosition[0] && initialPosition[1] == finalPosition[1]) {
			return;
		}
		SpeciesColor color = this.color;
		int colorOrdinal = color.ordinal();
		if (this.paperCounts[colorOrdinal] == 0) {
			return;
		}
		--this.paperCounts[colorOrdinal];
		World world = this.getWorld();
		world.addDynamicEntity(new Paper(world, color, initialPosition, finalPosition));
	}

	public void punchWithPaper() {}

	public int checkInput(GameContainer container, int delta){
		Input input = container.getInput();

		// Déplacement ZQSD du joueur :
		if (input.isKeyDown(Input.KEY_Z)){
			return this.move(new int[] {-1, 0});
		} else if (input.isKeyDown(Input.KEY_D)) {
			return this.move(new int[] {0, 1});
		} else if (input.isKeyDown(Input.KEY_S)) {
			return this.move(new int[] {1, 0});
		} else if (input.isKeyDown(Input.KEY_Q)) {
			return this.move(new int[] {0, -1});
		} else {
			return 0;
		}

	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}

}

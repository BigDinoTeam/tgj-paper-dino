package games.paperDino.entities.dynamic.dinos;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import app.AppFont;
import app.AppLoader;

import games.paperDino.Cell;
import games.paperDino.Piece;
import games.paperDino.SpeciesColor;
import games.paperDino.World;
import games.paperDino.entities.dynamic.Dino;
import games.paperDino.entities.dynamic.Paper;

public class Player extends Dino {

	private static Image sprite;
	private static AppFont playerFont;

	static {
		Player.sprite = AppLoader.loadPicture("/images/dino.png");
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
			2, // universal
			5, // red
			5, // yellow
			5, // green
			5, // blue
		};
		this.paperMaxCounts = new int[]{
			5, // universal
			10, // red
			10, // yellow
			10, // green
			10, // blue
		};
		this.color = SpeciesColor.universal;
		this.score = 0;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		super.update(container, game, delta);
		Input input = container.getInput();
		if (input.isMousePressed(0)) {
			int cellSize = Cell.size;
			int[] initialPosition = this.getPosition();
			int[] finalPosition = new int[]{
				input.getMouseY() / cellSize,
				input.getMouseX() / cellSize,
			};
			this.throwPaper(initialPosition, finalPosition);
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		Player.playerFont = AppLoader.loadFont("/fonts/vt323.ttf", AppFont.PLAIN, 38*container.getHeight()/1080);

		context.setColor(Color.black);
		context.fillRect(640*container.getWidth()/1920, 925*container.getHeight()/1080, (1300-640)*container.getWidth()/1920+2, 112*container.getHeight()/1080+2);
		context.setColor(Color.white);
		context.fillRect((640+color.ordinal()*132)*container.getWidth()/1920, 925*container.getHeight()/1080, 112*container.getWidth()/1920+2, 112*container.getHeight()/1080+2);

		for (int i=0; i<paperCounts.length ; i++) {
			context.setColor(paperCounts[i]==paperMaxCounts[i]?Color.red:(i==color.ordinal()?Color.black:Color.white));
			context.setFont(playerFont);
			context.drawString(""+paperCounts[i] , (711+132*i)*container.getWidth()/1920, 995*container.getHeight()/1080);
		}
	}

	public void collectPapers() {}

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

		// Sélection des prospectus
		if (input.isKeyDown(Input.KEY_1)){
			this.color = SpeciesColor.universal;
		} else if (input.isKeyDown(Input.KEY_2)) {
			this.color = SpeciesColor.red;
		} else if (input.isKeyDown(Input.KEY_3)) {
			this.color = SpeciesColor.yellow;
		} else if (input.isKeyDown(Input.KEY_4)) {
			this.color = SpeciesColor.green;
		} else if (input.isKeyDown(Input.KEY_5)) {
			this.color = SpeciesColor.blue;
		}

		// Déplacement ZQSD du joueur :
		if (input.isKeyDown(Input.KEY_Z)){
			return this.move(new int[] {-1, 0});
		} else if (input.isKeyDown(Input.KEY_D)) {
			return this.move(new int[] {0, 1});
		} else if (input.isKeyDown(Input.KEY_S)) {
			return this.move(new int[] {1, 0});
		} else if (input.isKeyDown(Input.KEY_Q)) {
			return this.move(new int[] {0, -1});
		}

		return 0;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}

	public boolean isPacified() { // Le joueur est toujours "ami"
		return true;
	}

}

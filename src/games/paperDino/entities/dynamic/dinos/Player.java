package games.paperDino.entities.dynamic.dinos;

import org.newdawn.slick.Image;

import app.AppLoader;

import games.paperDino.SpeciesColor;
import games.paperDino.World;
import games.paperDino.entities.dynamic.Dino;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Dino {

	private static Image sprite;

	static {
		Player.sprite = AppLoader.loadPicture("/images/houses/maison_orange.png");
	}

	private int[] paperCounts;
	private int[] paperMaCounts;
	private SpeciesColor selectedPaper;

	public Player(World world, int[] position) {
		super(world, Player.sprite, position);
	}

	public void collectPapers() {}

	public void selectPaper() {}

	public void throwPaper() {}

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

}

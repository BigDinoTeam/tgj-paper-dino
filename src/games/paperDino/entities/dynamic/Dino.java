package games.paperDino.entities.dynamic;

import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import games.paperDino.Grid;
import games.paperDino.World;
import games.paperDino.entities.DynamicEntity;

abstract public class Dino extends DynamicEntity {

	private int countdown;
	private int initialCountdown; // Durée max de l'action quand on se déplace

	private int[] previousPosition;

	public Dino(World world, Image sprite, int[] position) {
		super(world, sprite, position);
		this.previousPosition = position;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		this.countdown -= delta;
		if (this.countdown <= 0) {
			this.previousPosition = this.getPosition();
			this.countdown = this.initialCountdown = checkInput(container,delta);
		}
		if (this.initialCountdown == 0) {
			return;
		}
		int[] position = this.getPosition();
		float initialCountdown = this.initialCountdown;
		float countdown = this.countdown;
		float i = (previousPosition[0] * countdown + position[0] * (initialCountdown - countdown)) / initialCountdown;
		float j = (previousPosition[1] * countdown + position[1] * (initialCountdown - countdown)) / initialCountdown;
		float[] translation = new float[]{
			i - position[0],
			j - position[1],
		};
		this.setTranslation(translation);
	}

	public int move(int[] direction) {

		int[] newPosition = this.getPosition();
		newPosition[0] += direction[0];
		newPosition[1] += direction[1];

		Grid grid = this.getWorld().getGrid();

		Dino dinoOnNewPositionCell = null;

		if (grid.cellExists(newPosition)) {
			dinoOnNewPositionCell = grid.getCell(newPosition).whoIsOnThisCell();
			if (dinoOnNewPositionCell != null && !dinoOnNewPositionCell.getClass().equals(this.getClass())) {  // S'il y a un dino sur la cellule newPosition et qu'il est de type différent cela signifie qu'un IA entre en colision avec le joueur
				if(!dinoOnNewPositionCell.isPacified() || !this.isPacified()){ // Si l'IA bougeant n'est pas pacifiée, tuer le joueur.
					this.getWorld().killPlayer();
				}
			}
		}

		if (!grid.canMoveToCell(newPosition)) {
			return 0;   // Le mouvement est impossible et est donc annulé
		}

		this.setPosition(newPosition);
		return 300;
	}

	abstract public boolean isPacified();
}

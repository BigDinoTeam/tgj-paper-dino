package games.paperDino.entities.dynamic;

import games.paperDino.Grid;
import org.newdawn.slick.Image;

import games.paperDino.Piece;
import games.paperDino.World;
import games.paperDino.entities.DynamicEntity;

abstract public class Dino extends DynamicEntity {

	public Dino(World world, Image sprite, int[] position) {
		super(world, sprite, position);
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
				this.getWorld().killPlayer();
			}
		}

		if (!grid.canMoveToCell(newPosition)) {
			return 0;   // Le mouvement est impossible et est donc annulé
		}

		this.setPosition(newPosition);
		return this.getDefaultCountdown(); //TODO : ajouter cooldown avant prochaine action
	}
}

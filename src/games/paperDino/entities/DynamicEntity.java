package games.paperDino.entities;

import games.paperDino.Cell;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import games.paperDino.Entity;
import games.paperDino.Piece;
import games.paperDino.World;

/**
 *
 */
abstract public class DynamicEntity extends Entity {

	protected int actionCountdown;
	protected boolean inAction;
	private int initialActionCountdown; // Durée max de l'action quand on se déplace

	private int defaultCountdown = 300; // Durée d'un déplacement par défaut

	public DynamicEntity(World world, Image sprite, int[] position) {
		super(world, sprite, position);
		this.setPieces(new Piece[][]{
			new Piece[]{
				new Piece(this, new int[]{0, 0}, false),
			},
		});
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		this.actionCountdown -= delta;
		if (this.actionCountdown <= 0) {
			this.inAction = false;
			this.actionCountdown = this.initialActionCountdown = checkInput(container,delta);
		}
	}

	/**
	 * Déplace l'entité vers une case en fonction du vecteur direction
	 * @param direction vecteur direction (i,j)
	 * return time before next move (in miliseconds)
	 */
	public int move(int[] direction) {


		int[] oldPosition = this.getPosition();;
		int[] newPosition = this.getPosition();
		newPosition[0] += direction[0];
		newPosition[1] += direction[1];

		Cell[][] cells = this.getWorld().getGrid().getCells();

		if(newPosition[0] < 0 || newPosition[0] >= cells.length || newPosition[1] < 0 || newPosition[1] >= cells[0].length || !cells[newPosition[0]][newPosition[1]].isWalkable()){
			return 0;   // Le mouvement est impossible et est donc annulé
		}

		this.setPosition(newPosition);
		this.getWorld().getGrid().extract(this.getPieces(), oldPosition);   // Retirer l'Entity de sa Cell
		this.getWorld().getGrid().insert(this.getPieces(), newPosition);   // Ajouter l'Entity à sa nouvelle Cell
		return this.defaultCountdown; //TODO : ajouter cooldown avant prochaine action
	}

	abstract public int checkInput(GameContainer container, int delta);

	public int getDefaultCountdown() {
		return defaultCountdown;
	}

}

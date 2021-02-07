package games.paperDino;

import games.paperDino.entities.dynamic.Dino;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Grid {

	private Cell[][] cells;

	public Grid(int height, int width){
		this.cells = new Cell[height][width];

		for(Cell[] line : this.cells){  // Initialisation des cellules de la grille
			for(int i = 0; i < line.length; i++){
				line[i] = new Cell();
			}
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		for (Cell[] line: this.cells) {
			for (Cell cell: line) {
				cell.render(container, game, context);
			}
		}
	}

	/**
	 * Insère les pièces dans les cellules de la grille
	 * @param pieces tableau 2D des pièces à insérer
	 * @param position coordonnées (x, y) de la pièce en haut à droite
	 */
	public void insert(Piece[][] pieces, int[] position){
		int insertPositionX, insertPositionY;
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[i].length; j++) {
				insertPositionY = position[0] + i;
				insertPositionX = position[1] + j;
				this.cells[insertPositionY][insertPositionX].insertPiece(pieces[i][j]);
			}
		}
	}

	/**
	 * Retirer les pièces des les cellules de la grille
	 * @param pieces tableau 2D des pièces à retirer
	 * @param position coordonnées (x, y) de la pièce en haut à droite
	 */
	public void extract(Piece[][] pieces, int[] position){
		int extractPositionX, extractPositionY;
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[i].length; j++) {
				extractPositionY = position[0] + i;
				extractPositionX = position[1] + j;
				this.cells[extractPositionY][extractPositionX].extractPiece(pieces[i][j]);
			}
		}
	}

	public Cell[][] getCells() {
		return cells;
	}

	public int getHeight(){
		return cells.length;
	}

	public int getWidth(){
		return cells[0].length;
	}

	/** Indique s'il est possible de se déplacer vers la cellule de coordonnées (i, j)
	 * @param position
	 * @return
	 */
	public boolean canMoveToCell(int[] position){
		int i = position[0] + 1;
		int j = position[1];
		if(!cellExists(position) || !cells[i][j].isWalkable()){
			return false;
		}
		return true;
	}


	/**
	 * @param position
	 * @return si la cellule existe (si la position n'est pas hors de Grid)
	 */
	public boolean cellExists(int [] position){
		int i = position[0];
		int j = position[1];
		if(i < 0 || i >= this.getHeight() || j < 0 || j >= this.getWidth()){
			return false;
		}
		return true;
	}

	public Cell getCell(int[] position){
		return cells[position[0]][position[1]];
	}
}

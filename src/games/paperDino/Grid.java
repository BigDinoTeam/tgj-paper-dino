package games.paperDino;

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
	 * Insert les pièces dans les cellules de la grille
	 * @param pieces tableau 2D des pièces à insérer
	 * @param position coordonnées (x, y) de la pièce en haut à droite
	 */
	public void insert(Piece[][] pieces, int[] position){
		int insertPositionX, insertPositionY;
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[i].length; j++) {
				insertPositionY = position[1] + i;
				insertPositionX = position[0] + j;
				this.cells[insertPositionY][insertPositionX].insertPiece(pieces[i][j]);
			}
		}
	}

}

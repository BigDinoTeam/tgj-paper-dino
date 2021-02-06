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

	public void render(GameContainer container, StateBasedGame game, Graphics context) {}

}

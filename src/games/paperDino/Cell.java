package games.paperDino;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Cell {

	private List<Piece> pieces;

	public void render(GameContainer container, StateBasedGame game, Graphics context) {}

	public void insertPiece(Piece piece){
		this.pieces.add(piece);
	}

}

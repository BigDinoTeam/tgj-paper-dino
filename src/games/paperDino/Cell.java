package games.paperDino;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Cell {

	private List<Piece> pieces;

	public Cell() {
		this.pieces = new ArrayList<>();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {}

	public void insertPiece(Piece piece){
		this.pieces.add(piece);
	}

	public void extractPiece(Piece piece){
		this.pieces.remove(piece);
	}

}

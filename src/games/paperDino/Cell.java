package games.paperDino;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Cell {

	private List<Piece> pieces;

	public Cell() {
		this.pieces = new ArrayList<Piece>();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		for (Piece piece: this.pieces) {
			piece.render(container, game, context);
		}
	}

	public void insertPiece(Piece piece){
		this.pieces.add(piece);
	}

	public void extractPiece(Piece piece){
		this.pieces.remove(piece);
	}

	/**
	 * @return S'il est possible de marcher sur la cellule. Il suffit d'une piece non walkable pour rendre une cellule non walkable.
	 */
	public boolean isWalkable(){
		for (Piece piece : this.pieces) {
			if (!piece.isWalkable()){
				return false;
			}
		}
		return true;
	}

}

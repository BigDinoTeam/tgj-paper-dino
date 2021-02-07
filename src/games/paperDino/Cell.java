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

	public void insertPiece(Piece piece) {
		Entity entity = piece.getEntity();
		int position = entity.getPosition()[0] + entity.getSize()[1];
		for (int k = 0, lk = this.pieces.size(); k < lk; ++k) {
			Entity otherEntity = this.pieces.get(k).getEntity();
			int otherPosition = otherEntity.getPosition()[0] + otherEntity.getSize()[0];
			if (otherPosition > position) {
				this.pieces.add(k, piece);
				break;
			}
		}
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

package games.paperDino;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

public class Night {
	
	private static Image nightBackground;
	private static Image diplo;
	private static Image tyra;

	static {
		Night.nightBackground = AppLoader.loadPicture("/images/night.png");
		Night.diplo = AppLoader.loadPicture("/images/diplo.png");
		Night.tyra = AppLoader.loadPicture("/images/tyra.png");
	}
	
	private int goal = 10;
	private static int lives = 5;
	private double[][] dinos;
	
	public Night(int score, boolean isEaten) {
		int i;
		
		dinos = new double[lives+(isEaten?1:0)][3];
		
		for (i=0; i<lives; i++) {
			dinos[i][0] = 0;
			dinos[i][1] = Math.random();
			dinos[i][2] = Math.random();
		}
		
		if (score < this.goal || isEaten) {
			lives--;
			dinos[i][0] = 1;
			dinos[i][1] = Math.random();
			dinos[i][2] = Math.random();
		}
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(Night.nightBackground, 0, 0, container.getWidth(), container.getHeight(), 0, 0, Night.nightBackground.getWidth(), Night.nightBackground.getHeight());
		for (int i=dinos.length-1; i>=0; i--) {
			if (dinos[i] != null) {
				context.drawImage(dinos[i][0]==0?Night.diplo:Night.tyra,(float)(dinos[i][1]*(container.getWidth()-(dinos[i][0]==0?Night.diplo:Night.tyra).getWidth())), (float)(container.getHeight()/2+dinos[i][2]*(container.getHeight()/2)-(dinos[i][0]==0?Night.diplo:Night.tyra).getHeight()));
			}
		}
	}
	
}

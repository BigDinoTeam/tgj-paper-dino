package games.paperDino;

import java.util.ArrayList;
import java.util.List;

import games.paperDino.entities.StationaryEntity;
import games.paperDino.entities.dynamic.dinos.AI;
import games.paperDino.entities.stationary.Building;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import games.paperDino.entities.DynamicEntity;
import games.paperDino.entities.dynamic.dinos.Player;

public class World extends BasicGameState {

	private int ID;
	private int state;

	private List<DynamicEntity> dynamicEntities;
	private List<StationaryEntity> stationaryEntities;
	private Player player;
	private Grid grid;

	public World(int ID) {
		this.ID = ID;
		this.state = 0;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au chargement du programme */
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play(container, game);
		} else if (this.state == 2) {
			this.resume(container, game);
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause(container, game);
		} else if (this.state == 3) {
			this.stop(container, game);
			this.state = 0; // TODO: remove
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			this.setState(1);
			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}

		for (DynamicEntity dynamicEntity : dynamicEntities) {
			dynamicEntity.update(container,game,delta);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		this.grid.render(container, game, context);
	}

	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		loadLevel();
	}

	public void pause(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
	}

	public void resume(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
	}

	public void stop(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	/**
	 * Charge la Grid et les entitées dynamiques
	 */
	public void loadLevel(){
		this.grid = new Grid(7,9); // Initialisation manuelle de la grid

		this.dynamicEntities = new ArrayList<>();
		this.stationaryEntities = new ArrayList<>();

		this.player = new Player(this, new int[]{1, 7});

		this.dynamicEntities.add(player); // Ajouter le joueur
		this.dynamicEntities.add(new AI(this, new int[]{1, 2})); // Ajouter un dino IA
		this.dynamicEntities.add(new AI(this, new int[]{4, 8})); // Ajouter un dino IA
		this.dynamicEntities.add(new AI(this, new int[]{3, 7})); // Ajouter un dino IA
		//
		this.stationaryEntities.add(new Building(this,0, new int[] {0, 6}));  // Ajouter une maison

	}

	public Grid getGrid() {
		return grid;
	}
}

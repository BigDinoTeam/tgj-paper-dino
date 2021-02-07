package games.paperDino;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;

import games.paperDino.entities.DynamicEntity;
import games.paperDino.entities.StationaryEntity;
import games.paperDino.entities.dynamic.dinos.AI;
import games.paperDino.entities.dynamic.dinos.Player;
import games.paperDino.entities.stationary.Building;
import games.paperDino.entities.stationary.Nest;

public class World extends BasicGameState {

	private static Image background;
	private static Image gui;
	private static Image nightBackground;
	private static Image diplo;
	private static Image tyra;

	static {
		World.background = AppLoader.loadPicture("/images/herbe.png");
		World.gui = AppLoader.loadPicture("/images/GUI.png");
		World.nightBackground = AppLoader.loadPicture("/images/night.png");
		World.diplo = AppLoader.loadPicture("/images/diplo.png");
		World.tyra = AppLoader.loadPicture("/images/tyra.png");
	}

	private int ID;
	private int state;

	private List<DynamicEntity> dynamicEntities;
	private List<StationaryEntity> stationaryEntities;
	private Player player;
	private Grid grid;
	
	private boolean isDay;
	private int lives;
	private int goal; //TODO progressif

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
		if (isDay) {
			for (int k = this.dynamicEntities.size() - 1; k >= 0; --k) {
				this.dynamicEntities.get(k).update(container, game, delta);
			}
		} else {
			if (input.isKeyDown(Input.KEY_SPACE)) {
				this.loadLevel();
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		if (isDay) {
			context.drawImage(World.background, 0, 0, container.getWidth(), container.getHeight(), 0, 0, World.background.getWidth(), World.background.getHeight());
			this.grid.render(container, game, context);
			
			// GUI en dernier !
			this.player.render(container, game, context);
			context.drawImage(World.gui, 0, 0, container.getWidth(), container.getHeight(), 0, 0, World.gui.getWidth(), World.gui.getHeight());
		} else {
			this.renderNightScreen(container, game, context);
		}
	}

	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		this.lives = 5;
		this.goal = 10;
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
		this.isDay = true;
		
		this.grid = new Grid(9, 16); // Initialisation manuelle de la grid

		this.dynamicEntities = new ArrayList<>();
		this.stationaryEntities = new ArrayList<>();

		this.player = new Player(this, new int[]{1, 7});

		this.dynamicEntities.add(player); // Ajouter le joueur
		this.dynamicEntities.add(new AI(this, new int[]{1, 2}, SpeciesColor.red)); // Ajouter un dino IA
		this.dynamicEntities.add(new AI(this, new int[]{4, 6}, SpeciesColor.yellow)); // Ajouter un dino IA
		this.dynamicEntities.add(new AI(this, new int[]{3, 7}, SpeciesColor.green)); // Ajouter un dino IA

		this.stationaryEntities.add(new Nest(this, new int[]{7, 0})); // Ajouter le fourgon
		this.stationaryEntities.add(new Building(this, 0, new int[] {1, 6}, SpeciesColor.red));  // Ajouter une maison
	}

	public Grid getGrid() {
		return grid;
	}

	public void addDynamicEntity(DynamicEntity entity) {
		this.dynamicEntities.add(entity);
	}

	public void removeDynamicEntity(DynamicEntity entity) {
		this.dynamicEntities.remove(entity);
	}

	public void renderNightScreen(GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(World.nightBackground, 0, 0, container.getWidth(), container.getHeight(), 0, 0, World.nightBackground.getWidth(), World.nightBackground.getHeight());
		
		if (this.player.getScore() < this.goal) {
			lives--;
			context.drawImage(tyra, (int)(Math.random()*(container.getWidth()-tyra.getWidth())), (int)(container.getHeight()/2+Math.random()*(container.getHeight()/2)));
		}
		
		for (int i=0; i<lives; i++) {
			context.drawImage(diplo, (int)(Math.random()*(container.getWidth()-diplo.getWidth())), (int)(container.getHeight()/2+Math.random()*(container.getHeight()/2)));
		}
	}

	public Player getPlayer() {
		return this.player;
	}

	public void killPlayer(){
		this.isDay = false;
		System.out.println("Collision avec le joueur ! Day over");
	}
}

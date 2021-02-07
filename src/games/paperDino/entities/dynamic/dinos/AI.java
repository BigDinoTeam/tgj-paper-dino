package games.paperDino.entities.dynamic.dinos;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import app.AppLoader;

import games.paperDino.Activity;
import games.paperDino.Grid;
import games.paperDino.Piece;
import games.paperDino.SpeciesColor;
import games.paperDino.World;
import games.paperDino.entities.stationary.Building;
import games.paperDino.entities.dynamic.Dino;

import java.util.*;

import static java.lang.Math.*;

public class AI extends Dino {

	private static Image sprite;
	private int[] initialPosition;  // Centre de la zone de patrouille de l'IA
	private int patrolRange = 2; // Nombre de cases autour de initialPosition constituant la zone à patrouiller par l'IA
	private int[] minPositionPatrol;    // Point le plus en haut à gauche de la zone de patrouille de l'IA
	private int[] maxPositionPatrol;    // Point le plus en bas à droite de la zone de patrouille de l'IA
	private int[] latestMove; // Vecteur du dernier déplacement réalisé


	static {
		AI.sprite = AppLoader.loadPicture("/images/t_rex/t_rex-red.png");
	}

	private Building building;
	private SpeciesColor color;
	private Activity activity;
	private boolean pacified;

	public AI(World world, int[] position, SpeciesColor color) {
		super(world, AI.sprite, position);
		this.setPieces(new Piece[][]{
			new Piece[]{
				new Piece(this, new int[]{0, 0}, false),
			},
		});
		this.color = color;
		// Variables de zone de patrouille :
		this.latestMove = new int[] {-1, 0};
		this.initialPosition = position;
		this.minPositionPatrol = new int[] {max(this.initialPosition[0] - this.patrolRange, 0), max(this.initialPosition[1] - this.patrolRange, 0)};
		this.minPositionPatrol = new int[] {min(this.initialPosition[0] + this.patrolRange, world.getGrid().getHeight()), max(this.initialPosition[1] + this.patrolRange, world.getGrid().getWidth())};
	}

	@Override
	public int checkInput(GameContainer container, int delta) {
		// Si l'IA peut continuer tout droit sans sortir de sa zone de patrouille, il le fait. Il détermine son orientation actuelle grâce à latestMove
		// Sinon, il choisit aléatoirement entre prendre à gauche, à droite, voire faire demi-tour (avec le moins de probabilité)
		Random random = new Random();
		int randomNumber = random.nextInt(10);

		Grid grid = this.getWorld().getGrid();
		int[] moveToTest;
		int[] destination;

		List<int[]> movesToTest = new ArrayList<>();
		movesToTest.add(new int[] {-1,0});
		movesToTest.add(new int[] {0, 1});
		movesToTest.add(new int[] { 1, 0});
		movesToTest.add(new int[] {0, -1});

		Collections.shuffle(movesToTest, random); // Mélange la liste de déplacement possibles

		// Ensure that the lastestMove is at the end of the list
		movesToTest.remove(latestMove);
		movesToTest.add(latestMove);

		for (int i = movesToTest.size() - 1 ; i >= 0 ; i--) {
			moveToTest = movesToTest.get(i);
			destination = addVectorToPoint(this.getPosition(), moveToTest);
			if (pointIsInPatrolRange(destination) && grid.canMoveToCell(destination)) {
				this.latestMove = moveToTest;
				return move(moveToTest);
			}
		}
		return 300; // Si aucun déplacement n'est possible, attendre un peu avant de retenter
	}

	private boolean pointIsInPatrolRange(int[] point){
		return abs(point[0] - this.initialPosition[0]) <= this.patrolRange && abs(point[1] - this.initialPosition[1]) <= this.patrolRange;
	}

	private int[] addVectorToPoint(int[] point, int[] vector){
		return new int[] {point[0] + vector[0], point[1] + vector[1]};
	}

	public SpeciesColor getColor() {
		return this.color;
	}

	public boolean isPacified() {
		return pacified;
	}

	public void pacify(){
		this.pacified = true;
		//TODO : ajouter bulle de réaction
	}
}

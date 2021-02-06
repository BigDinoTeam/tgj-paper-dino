package games.paperDino.entities.dynamic.dinos;

import games.paperDino.SpeciesColor;
import games.paperDino.World;
import games.paperDino.entities.dynamic.Dino;

public class Player extends Dino {

	private int[] paperCounts;
	private int[] paperMaCounts;
	private SpeciesColor selectedPaper;

	public Player(World world, int[] position) {
		super(world, position);
	}

	public void collectPapers() {}

	public void selectPaper() {}

	public void throwPaper() {}

	public void punchWithPaper() {}

}

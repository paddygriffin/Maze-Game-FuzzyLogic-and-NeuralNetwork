package ie.gmit.sw.ai.sprite;


import ie.gmit.sw.ai.maze.Node;
import ie.gmit.sw.ai.traversers.*;

public class Spiders extends Sprite implements Runnable {

	private Node[][] maze;
	private Player player;
	private int row;
	private int col;
	private int id;
	private double strength;
	
	public Spiders(String name, String... images) throws Exception {
		super(name, images);
	}

	public Spiders(Node[][] maze, Player player, int row, int col, int id, int strength) {
		super();
		this.maze = maze;
		this.player = player;
		this.row = row;
		this.col = col;
		this.setId(id);
		this.strength = strength;
		
	}

	@Override
	public void run() {
		System.out.println("running");
		
		Traversator dlDFST = new DepthLimitedDFSTraversator(10, player, this);
		dlDFST.traverse(maze, maze[row][col]);
		
	}
	
	public void spriteManeuver(int xPosition, int yPosition) throws InterruptedException{
		if(maze[row][col].getTypeOfNode() != '0'){
			maze[this.row][this.col].setTypeOfNode('\u0020');
			maze[xPosition][yPosition].setTypeOfNode('\u0036');
			this.row = xPosition;
			this.col = yPosition;
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

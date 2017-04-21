package ie.gmit.sw.ai.maze;


import ie.gmit.sw.ai.Sprite;
import ie.gmit.sw.ai.traversers.*;

public class SpiderSprite extends Sprite implements Runnable {

	private Node[][] maze;
	private Player player;
	private int row;
	private int col;
	private int id;
	private double strength;
	
	public SpiderSprite(String name, String[] images) throws Exception {
		super(name, images);
	}

	public SpiderSprite(Node[][] maze, Player player, int row, int col, int id, int strength) {
		super();
		this.maze = maze;
		this.player = player;
		this.row = row;
		this.col = col;
		this.id = id;
		this.strength = strength;
		
	}

	@Override
	public void run() {
		System.out.println("run");
		
		//Traversator t = new AStarTraversator();
		
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

package ie.gmit.sw.ai.sprite;


import ie.gmit.sw.ai.fuzzy.FuzzyEngage;
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
		this.maze = maze;
		this.player = player;
		this.row = row;
		this.col = col;
		this.setId(id);
		this.strength = strength;
		System.out.println("No");
	}

	@Override
	public void run() {
		while(true){
			System.out.println("running");
			//Traversator aStar = new AStarTraversator();
			Traversator dlDFST = new DepthLimitedDFSTraversator(20, player, this);
			dlDFST.traverse(maze, maze[row][col]);
		}
		
	}
	
//	public void spriteManeuver(int xPosition, int yPosition) throws InterruptedException{
//		if(maze[xPosition][yPosition].getTypeOfNode() != '0'){
//			maze[this.row][this.col].setTypeOfNode('\u0020');
//			maze[xPosition][yPosition].setTypeOfNode('\u0036');
//			this.row = xPosition;
//			this.col = yPosition;
//		}
//	}
	
	public void moveSprite(int newX, int newY) throws InterruptedException {
		if (maze[newX][newY].getTypeOfNode() != '0') {
			maze[this.row][this.col].setTypeOfNode('\u0020');
			maze[newX][newY].setTypeOfNode('\u0036');
			this.row = newX;
			this.col = newY;
			System.out.println("row : " + newX);
			System.out.println("row : " + newY);
			
		}
	}
	
	
	public void fuzzyEngage(){
		FuzzyEngage fe = new FuzzyEngage();
		player.setHealth(fe.fight(player.getSwordPower(), player.getHealth(), this.strength));
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

package ie.gmit.sw.ai.sprite;

import java.util.Random;

import ie.gmit.sw.ai.fuzzy.FuzzyEngage;
import ie.gmit.sw.ai.maze.Node;
//import ie.gmit.sw.ai.traversers.RecursiveDFSTraversator;

public class NeuralSprite extends Sprite implements Runnable{
	private Player player;
	private Node[][] maze;
	private int row;
	private int col;
	//private double strength;
	Random rand = new Random();
	private int bombChoice = 0;
	private int bomb = 0;
	private int strength = 50;
	private int id;
	
	private Node temp = null;
	
	public NeuralSprite(String name, String... images) throws Exception{
		super(name, images);
		
	}
	
	public NeuralSprite(Node[][] maze, Player player, int row, int col,  double strength, int id){
		super();
		this.player = player;
		this.maze = maze;
		this.row = row;
		this.col = col;
		this.setId(id);
	}

	@Override
	public void run() {
		
//		System.out.println("neural sprite running");
//		
//		RecursiveDFSTraversator rdfs = new RecursiveDFSTraversator();
//		rdfs.traverse(maze, maze[row][col]);
	}
	
	public void moveSprite(int newX, int newY) throws InterruptedException {
		if (maze[newX][newY].getTypeOfNode() != '0') {
			//maze[this.row][this.col].setNodeType(NodeType.WalkableNode);
			maze[this.row][this.col].setTypeOfNode('\u0020');
			maze[newX][newY].setTypeOfNode('\u0037');
			this.row = newX;
			this.col = newY;
			//System.out.println("row : " + x);
			
		}
		
	}
	
	/*public void engageNN(){
		double healthy = 0;
		EngageNN enn = new EngageNN();//add in the nn
		try {
			if(player.getHealth() >= 100)
				healthy = 2;
			else if (player.getHealth() >= 40)
				healthy = 1;
			else
				healthy = 0;
			
			bombChoice = rand.nextInt(2) + 1;
			if (bombChoice == 1)
					bomb = player.getBomb();
			else 
				bomb = player.getBigbomb();
			int action = enn.action(healthy, player.getSword(), bomb, 1.0);
			if (action == 2){
				System.out.println("action is attack");
				FuzzyEngage f = new FuzzyEngage();
				player.setHealth(f.fight(player.getSwordPower(), player.getHealth(), this.strength));
			}
			else if (action == 1) {
				System.out.println("action is panic");
			}
			else if (action == 3){
				System.out.println("hide");
				maze[this.row][this.col].setTypeOfNode('\u0020');
				maze[row + 1][col].setTypeOfNode('\u0037');
				this.row = row + 1;
				this.col = row + 1;
			}
			else if (action == 4){
				System.out.println("run away");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

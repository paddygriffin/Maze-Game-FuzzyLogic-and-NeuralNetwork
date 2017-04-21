package ie.gmit.sw.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import ie.gmit.sw.ai.maze.*;
import ie.gmit.sw.ai.sprite.NeuralSprite;
import ie.gmit.sw.ai.sprite.Object;
import ie.gmit.sw.ai.sprite.Player;
import ie.gmit.sw.ai.sprite.Spiders;

public class Maze {
	private Node[][] maze;
	private Player player;
	private ExecutorService ex = Executors.newFixedThreadPool(100);//automatically populated threads
	private List<Spiders> sprites = new ArrayList<>();
	private List<NeuralSprite> nsprites = new ArrayList<>();
	
	public Maze(int dimension, Player player){
		
		maze = new Node[dimension][dimension];
		this.player=player;//
		init();
		buildMaze();
		//addFeature(5, -1, 1);
		
		int featureNumber = 20;
		addFeature('\u0031', '0', featureNumber); //1 is a sword, 0 is a hedge
		addFeature('\u0032', '0', featureNumber); //2 is help, 0 is a hedge
		addFeature('\u0033', '0', featureNumber); //3 is a bomb, 0 is a hedge
		addFeature('\u0034', '0', featureNumber); //4 is a hydrogen bomb, 0 is a hedge
	
		featureNumber = 20;//(int)((dimension * dimension) * 0.01);
		addFeature('\u0036', '0', featureNumber); //6 is a Black Spider, 0 is a hedge
		addFeature('\u0037', '0', featureNumber); //7 is a Blue Spider, 0 is a hedge
		addFeature('\u0038', '0', featureNumber); //8 is a Brown Spider, 0 is a hedge
		addFeature('\u0039', '0', featureNumber); //9 is a Green Spider, 0 is a hedge
		addFeature('\u003A', '0', featureNumber); //: is a Grey Spider, 0 is a hedge
		addFeature('\u003B', '0', featureNumber); //; is a Orange Spider, 0 is a hedge
		addFeature('\u003C', '0', featureNumber); //< is a Red Spider, 0 is a hedge
		addFeature('\u003D', '0', featureNumber); //= is a Yellow Spider, 0 is a hedge
	}

	private void init(){
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				maze[row][col] = new Node(row, col); //Index 0 is a hedge...
				maze[row][col].setTypeOfNode('0');
				player = getPlayer();
			}
		}
	}
	
	//fires in a particular feature(replaces the 0's with for example 6 for a bomb)
	private void addFeature(char feature, int replace, int number){
		int counter = 0;
		while (counter < number){
			int row = (int) (maze.length * Math.random());
			int col = (int) (maze[0].length * Math.random());
			
				if (maze[row][col].getTypeOfNode() == replace){
					maze[row][col].setTypeOfNode(feature);
					//if the feature is greater than 5, a spider will be created
					if(number > 19 && maze[row][col].getTypeOfNode() == '\u0036'){
						Spiders spider = new Spiders(maze, player, row, col, 25, counter); //25 is spiders strength
						sprites.add(spider);
						ex.execute(spider);
					}
					else if(number > 19 && maze[row][col].getTypeOfNode() == '\u0037'){
						System.out.println("has been called");
						NeuralSprite nsprite = new NeuralSprite(maze, player, row, col, 25, counter);
						nsprites.add(nsprite);
						ex.execute(nsprite);
					}
				}
				counter++;
			}
			
		}
	
	//
	public Player getPlayer() {
		return this.player;
	}
	
	public void setPlayer(Player player){
		this.player=player;
	}

	private void buildMaze(){ 
		for (int row = 1; row < maze.length - 1; row++){
			for (int col = 1; col < maze[row].length - 1; col++){
				int num = (int) (Math.random() * 10);
				if (num > 5 && col + 1 < maze[row].length - 1){
					maze[row][col + 1].setTypeOfNode('\u0020'); //\u0020 = 0x20 = 32 (base 10) = SPACE
				}
				else{
					if (row + 1 < maze.length - 1)maze[row + 1][col].setTypeOfNode('\u0020');
				}
			}
		}		
	}
	
	public Node[][] getMaze(){
		return this.maze;
	}
	
	public Node get(int row, int col){
		return this.maze[row][col];
	}
	
	public void set(int row, int col, char n){
		//n.setRow(row);
		//n.setCol(col);
		this.maze[row][col].setTypeOfNode(n);
	}
	
	public int size(){
		return this.maze.length;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				sb.append(maze[row][col]);
				if (col < maze[row].length - 1) sb.append(",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public Spiders getSprite(int row, int col){
		for(Spiders s : sprites ){
			if (maze[row][col].getRow() == row && maze[row][col].getCol() == col){
				return s;
			}
		}
		return null;
	}
	
	public NeuralSprite getNeuralSprite(int row, int col){
		for(NeuralSprite s : nsprites ){
			if (maze[row][col].getRow() == row && maze[row][col].getCol() == col){
				return s;
			}
		}
		return null;
	}
	
	
	
}
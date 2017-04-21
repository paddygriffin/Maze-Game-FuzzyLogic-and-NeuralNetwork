package ie.gmit.sw.ai.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ie.gmit.sw.ai.Sprite;
import ie.gmit.sw.ai.traversers.*;

public class SpiderSprite extends Sprite implements Runnable {

	private Node[][] maze = null;
	private Player player;
	private int row;
	private int col;

	public SpiderSprite(String name, String[] images) throws Exception {
		super(name, images);
	}

	public SpiderSprite(Node[][] maze, Player player, int row, int col) {
		super();
		this.maze = maze;
		this.player = player;
		this.row=row;
		this.col=col;
		
	}

	@Override
	public void run() {
		System.out.println("run");
		
	}
	
	public void spriteManeuver(int xPosition, int yPosition) throws InterruptedException{
		if(maze[row][col].getTypeOfNode() != '0'){
			maze[this.row][this.col].setTypeOfNode('\u0020');
			maze[xPosition][yPosition].setTypeOfNode('\u0036');
			this.row = xPosition;
			this.col = yPosition;
		}
	}
	
}

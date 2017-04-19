package ie.gmit.sw.ai.maze;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpiderMovement extends Node{
	
	private Object lock = null;
	private Node[][] maze = null;
	private ExecutorService executor = Executors.newFixedThreadPool(1);

	public SpiderMovement(int row, int col, int id, Object lock, Node[][] maze) {
		super(row, col, id);
		
		this.lock = lock;
		this.maze = maze;
		
		System.out.println("Start");
		System.out.println(adjacentNodes(maze).length);
		System.out.println("Finish\n");
	}
}

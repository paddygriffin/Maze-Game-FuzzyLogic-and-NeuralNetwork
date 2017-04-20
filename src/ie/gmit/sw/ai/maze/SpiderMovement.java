package ie.gmit.sw.ai.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ie.gmit.sw.ai.traversers.*;

public class SpiderMovement extends Node {

	private Object lock = null;
	private Node[][] maze = null;
	private ExecutorService executor = Executors.newFixedThreadPool(1);
	private long speed = 10;
	private Random r = new Random();
	private volatile int moveNumber = 0;
	private PlayerMovement pm;
	private Node nextMovement;
	private boolean moveAvailable = false;

	public SpiderMovement(int row, int col, int id, Object lock, Node[][] maze, PlayerMovement pm) {
		super(row, col, id);

		this.lock = lock;
		this.maze = maze;
		this.pm = pm;
		//this.id=id; //lets you know which spider it is
		
		executor.submit(() -> {
			while (true) {
				try {
					//movement();
					// sleep thread to simulate a movement pace
					Thread.sleep(speed);
					traverse(getRow(),getCol());
					if(moveAvailable){
						movement();
					}
					else{
						randomMovement();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
	}

	private void randomMovement() {
		
		synchronized (lock) {
			Node[] adjacentNodes = null;
			List<Node> moveTo = new ArrayList<>();
			
			//get spider adjacent nodes
			adjacentNodes = adjacentNodes(maze);

			for (int i = 0; i < adjacentNodes.length; i++) {
				Node n = adjacentNodes[i];
				// check if the node is empty space
				if (n.getId() == -1) {
				//add node to list
				 	moveTo.add(n);
				}
			}

			if (moveTo.size() > 0) {
				
				
				
				int x, y, newX, newY;
				x = getRow();
				y = getCol();

				//random index to move to
				int index = r.nextInt(moveTo.size());

				newX = nextMovement.getRow();
				newY = nextMovement.getCol();

				setRow(newX);
				setCol(newY);

				// move to that node
				maze[newX][newY] = (SpiderMovement) this;

				// remove self from original spot
				maze[x][y]= nextMovement;
			}

		}
		
	}

	//movement for the spider run in a thread
	private void movement() {
		if(nextMovement != null){
			synchronized (lock) {
				Node[] adjacentNodes = null;
				List<Node> moveTo = new ArrayList<>();

				//get spider adjacent nodes
				adjacentNodes = adjacentNodes(maze);

				for (int i = 0; i < adjacentNodes.length; i++) {
					Node n = adjacentNodes[i];
					
					// check if the node is empty space
					if (nextMovement.equals(n)){
						int x, y, newX, newY;
						x = getRow();
						y = getCol();

						//random index to move to
						int index = r.nextInt(moveTo.size());

						newX = nextMovement.getRow();
						newY = nextMovement.getCol();

						setRow(newX);
						setCol(newY);

						// move to that node
						maze[newX][newY] = (SpiderMovement) this;

						// remove self from original spot
						maze[x][y]= nextMovement;
						nextMovement = null;
						moveAvailable = false;
						return;
					}
				}
				randomMovement();
				nextMovement = null;
				moveAvailable = false;
				return;

			}
		}
		else{
			randomMovement();
			moveAvailable = false;
		}
	}
	
	public void traverse(int row, int col){
        Traversator bf = new AStarTraversator(pm);
        
        bf.traverse(maze, maze[row][col]);
        nextMovement = bf.getNextNode();
        
        if(nextMovement != null){
        	moveAvailable  = true;

            System.out.println(nextMovement);
        }
        else{
        	moveAvailable = false;
        }
	}
	
	
}

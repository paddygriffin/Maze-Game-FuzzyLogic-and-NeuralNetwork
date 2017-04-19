package ie.gmit.sw.ai.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpiderMovement extends Node {

	private Object lock = null;
	private Node[][] maze = null;
	private ExecutorService executor = Executors.newFixedThreadPool(1);
	private long speed = 2000;
	private Random r = new Random();
	private volatile int moveNumber = 0;

	public SpiderMovement(int row, int col, int id, Object lock, Node[][] maze) {
		super(row, col, id);

		this.lock = lock;
		this.maze = maze;

		
		executor.submit(() -> {
			while (true) {
				try {
					movement();
					// sleep thread to simulate a movement pace
					Thread.sleep(speed);
				} catch (Exception ex) {
					
				}
			}
		});
	}

	//movement for the spider run in a thread
	private void movement() {
		synchronized (lock) {
			//System.out.println("Moving number: " + moveNumber);

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

				newX = moveTo.get(index).getRow();
				newY = moveTo.get(index).getCol();

				setRow(newX);
				setCol(newY);
				moveTo.get(index).setRow(x);
				moveTo.get(index).setCol(y);

				// move to that node
				maze[newX][newY] = (SpiderMovement) this;

				// remove self from original spot
				maze[x][y] = moveTo.get(index);
			}

		}

	}
	
	private void Traverse(){
		
	}
}

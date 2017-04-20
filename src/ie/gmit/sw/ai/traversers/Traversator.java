package ie.gmit.sw.ai.traversers;

import java.util.HashMap;

import ie.gmit.sw.ai.maze.*;
public interface Traversator {
	//public void traverse(Node[][] maze, Node start);
	public void traverse(Node[][] maze, Node start);
	public Node getNextNode();
}

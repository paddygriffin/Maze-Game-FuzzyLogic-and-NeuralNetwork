package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import ie.gmit.sw.ai.sprite.Object;
import ie.gmit.sw.ai.sprite.Player;
import ie.gmit.sw.ai.sprite.Spiders;
import ie.gmit.sw.ai.sprite.Sprite;


public class GameRunner implements KeyListener{
	private static final int MAZE_DIMENSION = 50;//100x100
	private static final int IMAGE_COUNT = 14;//14 images
	private GameView view;
	private Maze model;
	private int currentRow;
	private int currentCol;
	private Player player;
	
	public GameRunner() throws Exception{
		currentRow = (int) (MAZE_DIMENSION * Math.random());
    	currentCol = (int) (MAZE_DIMENSION * Math.random());
		model = new Maze(MAZE_DIMENSION, player);//added player because of maze
    	view = new GameView(model);//create a game view and tell it to display out gameview model
    	placePlayer();
    	Sprite[] sprites = getSprites();
    	view.setSprites(sprites);
    	
    	//updateView();
    	
    	//set up your view
    	Dimension d = new Dimension(GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
    	view.setPreferredSize(d);
    	view.setMinimumSize(d);
    	view.setMaximumSize(d);
    	
    	JFrame f = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(this);
        f.getContentPane().setLayout(new FlowLayout());
        f.add(view);
        f.setSize(1000,1000);
        f.setLocation(100,100);
        f.pack();
        f.setVisible(true);
	}

	private void updateView(){
		view.setCurrentRow(currentRow);
		view.setCurrentCol(currentCol);
	}

	private void placePlayer(){   	
//    	currentRow = (int) (MAZE_DIMENSION * Math.random());
//    	currentCol = (int) (MAZE_DIMENSION * Math.random());
    	model.set(currentRow, currentCol, '5'); //A Spartan warrior is at index 5
    	updateView(); 		
	}
	
	//which keys are pressed
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentCol < MAZE_DIMENSION - 1) {
        	if (isValidMove(currentRow, currentCol + 1)) currentCol++;   		
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentCol > 0) {
        	if (isValidMove(currentRow, currentCol - 1)) currentCol--;	
        }else if (e.getKeyCode() == KeyEvent.VK_UP && currentRow > 0) {
        	if (isValidMove(currentRow - 1, currentCol)) currentRow--;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentRow < MAZE_DIMENSION - 1) {
        	if (isValidMove(currentRow + 1, currentCol)) currentRow++;        	  	
        }else if (e.getKeyCode() == KeyEvent.VK_Z){
        	view.toggleZoom();
        }else{
        	return;
        }
        
        updateView();       
    }
    public void keyReleased(KeyEvent e) {} //Ignore
	public void keyTyped(KeyEvent e) {} //Ignore

    
	private boolean isValidMove(int row, int col){
		if (row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getTypeOfNode() == '\u0020')
		{
			model.set(currentRow, currentCol, '\u0020');
			model.set(row, col, '5');
			return true;
		}
		else if(row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getTypeOfNode() == '\u0031'){
			model.getMaze()[row][col].setTypeOfNode('0');
			return false; //Can't move
		}
		else if(row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getTypeOfNode() == '\u0032'){
			model.getMaze()[row][col].setTypeOfNode('0');
			return false; //Can't move
		}
		else if(row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getTypeOfNode() == '\u0033'){
			model.getMaze()[row][col].setTypeOfNode('0');
			return false; //Can't move
		}
		else if(row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getTypeOfNode() == '\u0034'){
			model.getMaze()[row][col].setTypeOfNode('0');
			player.addHbomb();
			return false; //Can't move
		}
		else{
			return false;
		}
	}
	
	//create the sprite
	private Sprite[] getSprites() throws Exception{
		//Read in the images from the resources directory as sprites. Note that each
		//sprite will be referenced by its index in the array, e.g. a 3 implies a Bomb...
		//Ideally, the array should dynamically created from the images... 
		Sprite[] sprites = new Sprite[IMAGE_COUNT];
		sprites[0] = new Object("Hedge", "resources/hedge.png");
		sprites[1] = new Object("Sword", "resources/sword.png");
		sprites[2] = new Object("Help", "resources/help.png");
		sprites[3] = new Object("Bomb", "resources/bomb.png");
		sprites[4] = new Object("Hydrogen Bomb", "resources/h_bomb.png");
		sprites[5] = new Object("Spartan Warrior", "resources/spartan_1.png", "resources/spartan_2.png");
		sprites[6] = new Spiders("Black Spider", "resources/black_spider_1.png", "resources/black_spider_2.png");
		sprites[7] = new Spiders("Blue Spider", "resources/blue_spider_1.png", "resources/blue_spider_2.png");
		sprites[8] = new Spiders("Brown Spider", "resources/brown_spider_1.png", "resources/brown_spider_2.png");
		sprites[9] = new Spiders("Green Spider", "resources/green_spider_1.png", "resources/green_spider_2.png");
		//=====
		sprites[10] = new Spiders("Grey Spider", "resources/grey_spider_1.png", "resources/grey_spider_2.png");
		sprites[11] = new Spiders("Orange Spider", "resources/orange_spider_1.png", "resources/orange_spider_2.png");
		sprites[12] = new Spiders("Red Spider", "resources/red_spider_1.png", "resources/red_spider_2.png");
		sprites[13] = new Spiders("Yellow Spider", "resources/yellow_spider_1.png", "resources/yellow_spider_2.png");
		return sprites;
		//read in these sprites and pass them into an array of sprites
	}
	
	public static void main(String[] args) throws Exception{
		new GameRunner();
	}
}
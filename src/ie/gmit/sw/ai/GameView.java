package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GameView extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_VIEW_SIZE = 800;//600	
	private int cellspan = 5;//5x5 grid
	private int cellpadding = 2;
	private Maze maze;
	private Sprite[] sprites;
	private int enemy_state = 5;
	private Timer timer;
	private int currentRow;
	private int currentCol;
	private boolean zoomOut = false;
	private int imageIndex = -1;
	private int offset = 48; //The number 0 is ASCII 48.
	private Color[] reds = {new Color(255,160,122), new Color(139,0,0), new Color(255, 0, 0)}; //Animate enemy "dots" to make them easier to see
	
	public GameView(Maze maze) throws Exception{
		this.maze = maze;//pass in the maze
		currentRow = maze.getPlayer().getRow();
    	currentCol = maze.getPlayer().getCol();
		setBackground(Color.LIGHT_GRAY);
		setDoubleBuffered(true);//image optimization
		timer = new Timer(300, this);
		timer.start();
	}
	
	public void setCurrentRow(int row) {
		//specify the row
		if (row < cellpadding){
			currentRow = cellpadding;
		}else if (row > (maze.size() - 1) - cellpadding){
			currentRow = (maze.size() - 1) - cellpadding;
		}else{
			currentRow = row;
		}
	}

	public void setCurrentCol(int col) {
		//specify the column
		if (col < cellpadding){
			currentCol = cellpadding;
		}else if (col > (maze.size() - 1) - cellpadding){
			currentCol = (maze.size() - 1) - cellpadding;
		}else{
			currentCol = col;
		}
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
              
        cellspan = zoomOut ? maze.size() : 5;//if not zoomed out show 5x5 view      
        final int size = DEFAULT_VIEW_SIZE/cellspan;
        g2.drawRect(0, 0, GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);//draw rectangle around the area we want
        
        for(int row = 0; row < cellspan; row++) {
        	for (int col = 0; col < cellspan; col++){  
        		int x1 = col * size;
        		int y1 = row * size;
        		
        		int id = 0;
       		
        		if (zoomOut){
        			id = maze.get(row, col).getId();//give me the character at this ro col position
        			if (id >= 5){
	        			if (row == currentRow && col == currentCol){
	        				g2.setColor(Color.YELLOW);
	        			}else{
	        				g2.setColor(reds[(int) (Math.random() * 3)]);
	        			}
        				g2.fillRect(x1, y1, size, size);
        			}
        		}else{//if you're not zoomed out
        			id = maze.get(currentRow - cellpadding + row, currentCol - cellpadding + col).getId();
        		}
        		
        		imageIndex = id;
        		
        		if (imageIndex < 0){
        			
        			g2.setColor(Color.LIGHT_GRAY);//Empty cell
        			g2.fillRect(x1, y1, size, size);   			
        		}else{//paint the image
        			g2.drawImage(sprites[imageIndex].getNext(), x1, y1, null);
        		}
        	}
        }
	}
	
	public void toggleZoom(){
		zoomOut = !zoomOut;		
	}

	//key event 
	public void actionPerformed(ActionEvent e) {	
		if (enemy_state < 0 || enemy_state == 5){
			enemy_state = 6;
		}else{
			enemy_state = 5;
		}
		this.repaint();
	}
	
	public void setSprites(Sprite[] sprites){
		this.sprites = sprites;
	}
}
package ie.gmit.sw.ai.sprite;

public class Player{
	
	private int row;
	private int col;
	private int hbomb = 0;
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getHbomb() {
		return hbomb;
	}
	public void addHbomb() {
		hbomb++;
	}
}

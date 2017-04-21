package ie.gmit.sw.ai.sprite;

public class Player{
	
	private int row;
	private int col;
	private int sword = 0;
	private int bomb = 0;
	private int hbomb = 0;
	private int swordPower = 0;
	private int bombPower = 0;
	private int hbombPower = 0;
	
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
	
	public int getSword() {
		return sword;
	}
	
	public void setSword(int sword) {
		this.sword = sword;
	}
	
	public int getBomb() {
		return bomb;
	}
	
	public void setBomb(int bomb) {
		this.bomb = bomb;
	}
	
	public int getSwordPower() {
		return swordPower;
	}
	
	public void setSwordPower(int swordPower) {
		this.swordPower = swordPower;
	}
	
	public int getBombPower() {
		return bombPower;
	}
	
	public void setBombPower(int bombPower) {
		this.bombPower = bombPower;
	}
	
	public int getHbombPower() {
		return hbombPower;
	}
	
	public void setHbombPower(int hbombPower) {
		this.hbombPower = hbombPower;
	}
}

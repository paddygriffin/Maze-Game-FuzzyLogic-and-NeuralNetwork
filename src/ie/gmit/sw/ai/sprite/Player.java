package ie.gmit.sw.ai.sprite;

public class Player{
	
	private int row;
	private int col;
	private int sword = 0;
	private int bomb = 0;
	private int bigBomb = 0;
	private int swordPower = 0;
	private int bombPower = 20;
	private int bigBombPower = 40;
	private double health = 200;
	
	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}
	
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
	
	public int getBigbomb() {
		return bigBomb;
	}
	
	public void addBigbomb() {
		bigBomb++;
	}
	
	public int getSword() {
		return sword;
	}
	
	public void addSword() {
		this.sword++;
	}
	
	public int getBomb() {
		return bomb;
	}
	
	public void addBomb() {
		this.bomb++;
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
	
	public int getBigBombPower() {
		return bigBombPower;
	}
	
	public void setHbombPower(int hbombPower) {
		this.bigBombPower = hbombPower;
	}
}

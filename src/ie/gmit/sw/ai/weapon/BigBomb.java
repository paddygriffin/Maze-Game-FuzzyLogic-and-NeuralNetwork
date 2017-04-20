package ie.gmit.sw.ai.weapon;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BigBomb extends Weapon implements Explodeable {

	BufferedImage image;
	private int damageRadius = 15;
	
	public BigBomb(String weaponName, int attackValue) {
		super(weaponName, attackValue);
		
		try {
			image = ImageIO.read(new java.io.File("resources/h_bomb.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//constructor

	
	public int getDamageRadius() {
		return this.damageRadius;
	}//get


	public void setDamageRadius(int damageRadius) {
		this.damageRadius = damageRadius;
	}//set

}

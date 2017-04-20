package ie.gmit.sw.ai.weapon;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bomb extends Weapon implements Explodeable {

	BufferedImage image;
	private int damageRadius = 5;
	
	public Bomb(String weaponName, int attackValue) {
		super(weaponName, attackValue);
		
		try {
			image = ImageIO.read(new java.io.File("resources/bomb.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//bomb

	
	public int getDamageRadius() {
		return this.damageRadius;
	}//radius


	public void setDamageRadius(int damageRadius) {
		this.damageRadius = damageRadius;
	}//set

}

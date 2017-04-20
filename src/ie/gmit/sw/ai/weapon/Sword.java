package ie.gmit.sw.ai.weapon;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sword extends Weapon {
	
	BufferedImage image;
	
	public Sword(String weaponName, int attackValue) {
		super(weaponName, attackValue);
		
		try {
			image = ImageIO.read(new java.io.File("resources/sword.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

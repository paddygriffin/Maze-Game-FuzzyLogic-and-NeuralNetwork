package ie.gmit.sw.ai;

import javax.imageio.*;
import java.awt.image.*;
//sprite is just an animated image
public class Sprite {
	private String name; //The name of this sprite
	private BufferedImage[] frames; //The set of image frames to animate(represent the image as a bufferedImage)
 	private int index = 0; //Initial starting index in array
 	
 	//private double lifeForce = 500;
 	
	public Sprite(String name, String... images) throws Exception{//pass in the name and images
		//fill the values of each one
		this.name = name;
		this.index = 0; //Initialise the starting index to zero
		this.frames = new BufferedImage[images.length]; //Initialise the image frames
		
		for (int i = 0; i < images.length; i++){
			frames[i] = ImageIO.read(new java.io.File(images[i])); //Read in each image as a BufferedImage and fire it into the array
		}
	}
	
	/*example of fuzzy logic being put in the game
	public double engage(double weapon, double anger){
	
		NeuralNetwork nn = new NeuralNetworkFactory.getInstance.getNetwork("engage");
		double[] inputs = {weapon, anger};
		double result = nn.process(inputs);
		lifeForce += result;
		
		FIS fis = new FuzzyLogicFactory.getInstance.getFIS("engage");
		double result = dis.evaluate(weapon, anger);
		lifeForce += result;
	}
	
	public boolean isAlive(){
		return lifeForce > 0;
	}
	*/
	
	public BufferedImage getNext(){ //Returns the next image frame(getNext)
		//threading should go in here
		int idx = index;
		if (index < frames.length - 1){
			index++;
		}else{
			index = 0; //Circle back to the start of the array
		}
		return frames[idx]; 
	}
	
	public String getName(){
		return this.name;
	}
	
	
}

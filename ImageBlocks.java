package Tetris;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
/*
 we need to add the images to our resources files and set each image.
 each image contains one of the 7 different Tetromines and its all different rotation.
 
 */
public class ImageBlocks extends JFrame{
	private ImageIcon image, image2, image3, image4, image5, image6, image7, image8, image9, image10,
						image11, image12, image13, image14, image15, image16, image17, image18, image19;
	ImageBlocks() {
		// L blocks
		image = new ImageIcon(getClass().getResource("I-1.png"));
		image2 = new ImageIcon(getClass().getResource("I-2.png"));
		
		// J blocks 
		image3 = new ImageIcon(getClass().getResource("J-1.png"));
		image4 = new ImageIcon(getClass().getResource("J-2.png"));
		image5 = new ImageIcon(getClass().getResource("J-3.png"));
		image6 = new ImageIcon(getClass().getResource("J-4.png"));
		
		// L blocks 
		image7 = new ImageIcon(getClass().getResource("L-1.png"));
		image8 = new ImageIcon(getClass().getResource("L-2.png"));
		image9 = new ImageIcon(getClass().getResource("L-3.png"));
		image10 = new ImageIcon(getClass().getResource("L-4.png"));
		
		// O blocks 
		image11 = new ImageIcon(getClass().getResource("O.png"));
		
		// S blocks
		image12 = new ImageIcon(getClass().getResource("S-1.png"));
		image13 = new ImageIcon(getClass().getResource("S-2.png"));
		
		// T blocks
		image14 = new ImageIcon(getClass().getResource("T-1.png"));
		image15 = new ImageIcon(getClass().getResource("T-2.png"));
		image16 = new ImageIcon(getClass().getResource("T-3.png"));
		image17 = new ImageIcon(getClass().getResource("T-4.png"));
		
		// Z Blocks
		image18 = new ImageIcon(getClass().getResource("Z-1.png"));
		image19 = new ImageIcon(getClass().getResource("Z-2.png"));
	}
	public void getIBlock() {
		
	}
	public void getJBlock() {
		
	}
	public void getLBlock() {
		
	}
	public void getOBlock() {
		
	}
	public void getSBlock() {
		
	}
	public void getTBlock() {
		
	}
	public void getZBlock() {
		
	}
} // End of ImageBlocks method



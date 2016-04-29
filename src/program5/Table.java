package program5;

import java.awt.Font;
import java.awt.Graphics;

public class Table {
	final int COLUMN = 10, ROW = 20, SIDE = 28; 
	String table[][];
	SDD sdd;
	int widthSDD, heightSDD;
	Duo pos;
	
	public Table(SDD sdd){
		this.sdd = sdd;
		calcPos();
		table = new String[COLUMN][ROW];
		emptySpace();
	}//End of Table
	
	public void emptySpace() {
		for(int Y = 0; Y < ROW; Y++){
			for(int X = 0; X < COLUMN; X++){
				table[X][Y] = "";
			}//End of inner for loop
		}//End of for loop
	}// End of emptySpace
	
	public void calcPos(){
		//calculate each column and row position
		widthSDD = sdd.getWidth();
		heightSDD = sdd.getHeight();
		pos = new Duo((widthSDD - COLUMN * SIDE)/2, 
						(heightSDD - ROW * SIDE)/2);
	}//End of calcPos method 

	public void draw(Graphics g){
		for(int Y = 0; Y < ROW; Y++){
			for(int X = 0; X < COLUMN; X++){
				Duo posTemp = new Duo(X * SIDE + pos.X, Y * SIDE + pos.Y);
				
				/*Draw the empty space */
				g.drawRect((int)posTemp.X, (int)posTemp.Y, SIDE, SIDE);
				
				//Draw the position on each space
				g.setFont(new Font("Calibri", Font.PLAIN, 8));
				g.drawString(X + "," + Y, (int)posTemp.X, (int)posTemp.Y + 11);
				
			}//End of inner for loop
		}//End of for loop
	}//End of draw method
	
}// End of Table class

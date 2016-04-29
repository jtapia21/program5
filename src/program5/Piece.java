package program5;

import java.awt.Graphics;

public class Piece {
	SDD sdd;
	Table table;
	Image image;
	Duo pos = new Duo(5,5);
	Tetrimino Z = Tetrimino.consult("Z");
	Tetrimino S = Tetrimino.consult("S");
	Tetrimino T = Tetrimino.consult("T");
	Tetrimino L = Tetrimino.consult("L");
	Tetrimino J = Tetrimino.consult("J");
	Tetrimino I = Tetrimino.consult("I");
	
	
	public Piece(SDD sdd){
		this.sdd = sdd;
		this.table = sdd.table;
		this.image = sdd.image;
	}//End of Piece method
	
	public void draw(Graphics g) {
		//Need to add the other pieces here and make them appear at random.
		//Most likely need a random generator function and call it here.
		for(int i = 0; i < 4; i++){
			image.drawStuff(new Duo(pos.X + Z.stuff[i].X , pos.Y + Z.stuff[i].Y), g);
		}//End for loop 
		
	}//End of draw method
}//End of Piece class

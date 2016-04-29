package program5;

import java.awt.Graphics;
import java.util.Random;

public class Piece {
	SDD sdd;
	Table table;
	Image image;
	Random gen = new Random();
	Duo pos = new Duo(gen.nextInt(Table.COLUMN-1),1);
	String pieceType;
	Tetrimino myTetrimino = Tetrimino.consult("Z");
	
	
	public Piece(SDD sdd, String pieceType){
		this.sdd = sdd;
		this.table = sdd.table;
		this.image = sdd.image;
		this.pieceType = pieceType;
		if (!isValidPieceType(pieceType)) {
			myTetrimino = Tetrimino.consult("Z");//default to Z type if no type specified
		} else {
			myTetrimino = Tetrimino.consult(pieceType);
		}
	}//End of Piece method
	
	public void draw(Graphics g) {
		//Need to add the other pieces here and make them appear at random.
		//Most likely need a random generator function and call it here.
		for(int i = 0; i < 4; i++){
			image.drawStuff(new Duo(pos.X + myTetrimino.stuff[i].X , pos.Y + myTetrimino.stuff[i].Y), g);
		}//End for loop 
		
	}//End of draw method
	
	private boolean isValidPieceType(String type) {
		if (!type.equals("Z")&&!type.equals("S")&&!type.equals("T")&&!type.equals("L")&&!type.equals("J")&&!type.equals("I")) {
			return false;
		}
		return true;
	}
}//End of Piece class

package program5;

import java.awt.Color;
import java.awt.Graphics;

public class Piece {
	SDD sdd;
	Table table;
	Image image;
	Duo pos = new Duo(1+Main.gen.nextInt(Table.COLUMN-2),1);
	String pieceType;
	Tetrimino myTetrimino = Tetrimino.consult("Z");
	
	
	public Piece(SDD sdd, String pieceType){
		this.sdd = sdd;
		this.table = sdd.table;
		this.image = sdd.image;
		this.pieceType = pieceType;
		if (!isValidPieceType(pieceType)) {
			myTetrimino = Tetrimino.consult("Z");//default to Z type if no type specified
			pieceType = "Z";
		} else {
			myTetrimino = Tetrimino.consult(pieceType);
		}
	}//End of Piece method
	
	public void draw(Graphics g) {
		if (pieceType.equals("I"))
			g.setColor(new Color(255, 0, 0));//red
		else if (pieceType.equals("T"))
			g.setColor(new Color(80, 80, 80));//grey
		else if (pieceType.equals("O"))
			g.setColor(new Color(148, 228, 255));//cyan
		else if (pieceType.equals("L"))
			g.setColor(new Color(255, 255, 0));//yellow
		else if (pieceType.equals("J"))
			g.setColor(new Color(255, 0, 255));//pink
		else if (pieceType.equals("S"))
			g.setColor(new Color(0, 0, 255));//blue
		else if (pieceType.equals("Z"))
			g.setColor(new Color(0, 255, 0));//green
		else 
			g.setColor(new Color(255, 255, 255));
		for(int i = 0; i < 4; i++){
			image.drawStuff(new Duo(pos.X + myTetrimino.stuff[i].X , pos.Y + myTetrimino.stuff[i].Y), g);
		}//End for loop 
		
	}//End of draw method
	
	private boolean isValidPieceType(String type) {
		if (!type.equals("Z")&&!type.equals("S")&&!type.equals("T")&&!type.equals("L")&&!type.equals("J")&&!type.equals("I")&&!type.equals("O")) {
			return false;
		}
		return true;
	}
}//End of Piece class

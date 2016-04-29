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
	//move commands return true if the move succeeded, or false if otherwise
	//TODO: Have the pieces make sure they aren't intersecting any other pieces here before they move.
	//TODO: also have these move commands update a Main.timeOfLastMove with System.nanowhatever so we can have pieces float and only lock in after they've not moved for a second
	public boolean moveRight() {
		if (this.pos.nextStepWithinBounds(1, 0, this.pieceType)) {
			this.pos.moveRight();
			return true;//move success
		}
		return false;
	}
	public boolean moveLeft() {
		if (this.pos.nextStepWithinBounds(-1, 0, this.pieceType)) {
			this.pos.moveLeft();
			return true;//move success
		}
		return false;
	}
	public boolean moveDown() {
		if (this.pos.nextStepWithinBounds(0, 1, this.pieceType)) {
			this.pos.moveDown();
			return true;//move success
		}
		return false;
	}
}//End of Piece class

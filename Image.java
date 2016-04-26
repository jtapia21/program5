package program5;

import java.awt.Graphics;

public class Image {
	
	SDD sdd;
	Duo posTable;
	int side;
	
	public Image(SDD sdd){
		this.sdd = sdd;
		posTable = sdd.table.pos;
		side = sdd.table.SIDE;
	}//End of Image method
	
	public Duo calcPos(Duo pos){
		int X = (int)(pos.X * side + posTable.X);
		int Y = (int)(pos.Y * side + posTable.Y);
		return new Duo(X,Y);
	}//End of calcPos method
	
	public void drawStuff(Duo pos, Graphics g){
		g.fillRect((int)calcPos(pos).X, (int)calcPos(pos).Y, side, side);
	}//End of drawStuff method
	
}// End of Image class

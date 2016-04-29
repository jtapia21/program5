package program5;

public class Duo {
	double X,Y;
	
	public Duo(double X, double Y){
		this.X = X;
		this.Y = Y;
	}//End of Duo method
	
	public double getX(){
		
		return X;
		
	}// End of get getX method
	
	public void setX(double X){
		
		this.X = X;
		
	}//End of setX method
	
	public double getY(){
		
		return Y;
		
	}// End of get getY method
	
	public void setY(double Y){
		
		this.Y = Y;
		
	}//End of setY method
	
	public void moveRight(){
		X++;
	}//End of moveRight method
	
	public void moveLeft(){
		X--;
	}//End of moveLeft method

	public void moveDown(){
		Y++;
	}//End of moveDown method
	
	public void rotateBlock(){
		//Need to rotate block each time one presses the key
	}//End of rotateBlock method
	
	//basic bounds checking
	public boolean isWithinBounds() {
		if (X < 2 || X > Table.COLUMN || Y < 2 || Y > Table.ROW) {
			return false;
		}
		return true;
	}
	//checks if the specified next step is valid
	public boolean nextStepWithinBounds(int x, int y, String pieceType) {
		if (pieceType.equals("T")) {//T type is 3 wide, so it clips on the right side, need to handle special case...
			if (X+x < 1 || X+x >= Table.COLUMN -1 || Y+y < 1 || Y+y >= Table.ROW) {
				return false;
			}
			return true;
		} else {
			if (X+x < 1 || X+x >= Table.COLUMN || Y+y < 1 || Y+y >= Table.ROW) {
				return false;
			}
			return true;
		}
	}
	
}//End of Duo class

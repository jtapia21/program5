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
	
}//End of Duo class

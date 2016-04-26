package program5;

public class Tetrimino {
	Duo stuff[];
	String name;
	public Tetrimino(Duo stuff0, Duo stuff1, Duo stuff2, Duo stuff3,
						String name){
		stuff = new Duo[4];
		
		stuff[0] = stuff0;
		stuff[1] = stuff1;
		stuff[2] = stuff2;
		stuff[3] = stuff3;
		
		this.name = name;
		
	}//End of Tetrimino method
	
	public static Tetrimino consult(String name){
		
		//Create names and shapes
		switch(name) {
			case "Z":
				return new Tetrimino(new Duo(0,0), new Duo(-1,-1), 
					new Duo(0,-1),new Duo(1,0), "Z");
			case "S":
				return new Tetrimino(new Duo(0,0), new Duo(-1,0), 
					new Duo(0,-1),new Duo(1,-1), "S");
			case "J":
				return new Tetrimino(new Duo(0,0), new Duo(-1,-1), 
					new Duo(-1,0),new Duo(1,0), "J");
			case "L":
				return new Tetrimino(new Duo(0,0), new Duo(-1,0), 
					new Duo(1,-1),new Duo(1,0), "L");
			case "T":
				return new Tetrimino(new Duo(0,0), new Duo(0,-1), 
					new Duo(-1,0),new Duo(1,0), "T");
			case "O":
				return new Tetrimino(new Duo(0,0), new Duo(0,-1), 
					new Duo(1,-1),new Duo(1,0), "O");
			case "I":
				return new Tetrimino(new Duo(0,0), new Duo(-1,0), 
					new Duo(1,0),new Duo(2,0), "I");
		}
		
		//Tetrimino is empty(default)
		return new Tetrimino(new Duo(0,0), new Duo(0,0), 
								new Duo(0,0),new Duo(0,0), "");
	}//End of consult
}//End of Tetrimino class

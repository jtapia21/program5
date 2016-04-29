package program5;

public class Main {
	private static SDD sdd;
	private static window Window;
	private static int APS = 0;
	private static int FPS = 0;
	
	public static void main(String[] args){
		game();
		
	}//End of main method
	
	public static void game(){
		createWindow(950,600,"Tetris - Project 5");
		mainLoop();
	}//End of game method
	
	public static void act(){
		APS++;
		sdd.draw();
	}//End of act method
	
	public static void draw(){
		FPS++;
	}//End of draw method
	
	public static void createWindow(int width, int height, String name) {
		sdd = new SDD(width, height);
		Window = new window(name, sdd);
	}//End of createWIndow method
	
	public static void mainLoop(){
		final int nano = 1000000000;
		final int obj = 60;
		final int actualized = nano/obj;
		
		//Time reference 
		long  timeRef = System.nanoTime();
		long timeCount = System.nanoTime();
		
		double delta = 0;
		double time;
		
		while(true) {
			//fix to current time 
			long timeIn = System.nanoTime();
			time = timeIn - timeRef;
			timeRef = timeIn;
			delta += time/actualized;
			while (delta >= 1){
				delta--; 
				act();
			}//End of inner while loop
			
			draw();
			
			if(System.nanoTime() - timeCount > actualized) {
				//System.out.println("APS = " + APS + " FPS = " + FPS);
				timeCount = System.nanoTime();
				APS = 0;
				FPS = 0;
			}//End of if loop
		}//End of while loop
	}//End of MainLoop	
}//End of main

package program5;

import java.util.Random;

public class Main {
	private static SDD sdd;
	private static window Window;
	private static int APS = 0;
	private static int FPS = 0;
	public static Random gen = new Random();
	public static int tickSpeed = 1000;//lower this to increase gravity rate, piece lock rate.
	private static long lastTick = System.currentTimeMillis();//use this one to calculate time since the last ticktime passed, so we can trigger a game tick
	public static int gameScore = 0;
	public static int linesFilled = 0;
	
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
	
	public static void tick() {
		//one game tick has passed! Trigger tick sensitive events, such as gravity!
		lastTick = System.currentTimeMillis();
		
		sdd.gravityTick();//trigger gravity/lock-in impulse
		sdd.scoreTick();//trigger scoring of filled lines
		tickSpeed = (50 - (linesFilled * 2) / 60)+1000;
	}//end of tick method
	
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
			
			if (System.currentTimeMillis() - tickSpeed > lastTick) {
				tick();
			}
			
			if(System.nanoTime() - timeCount > actualized) {
				//System.out.println("APS = " + APS + " FPS = " + FPS);
				timeCount = System.nanoTime();
				APS = 0;
				FPS = 0;
			}//End of if loop
		}//End of while loop
	}//End of MainLoop	
}//End of main

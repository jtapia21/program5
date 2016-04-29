package program5;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class SDD extends Canvas {

	private BufferStrategy buffer;
	private Graphics g;
	Image image;
	Table table;
	Piece piece;
	
	public SDD(int width, int height){
		//this.setPreferredSize(new Dimension(width, height));
		setSize(width, height);
		table = new Table(this);
		image = new Image(this);
		piece = new Piece(this);
		captureKey();
		setFocusable(true);
		
	}//End of SDD
	
	public void draw(){
		buffer = getBufferStrategy();
		
		//check if buffer doesn't have the same value
		if(buffer == null){
			createBufferStrategy(2);
			return;
		}// End of if loop
		
		g = buffer.getDrawGraphics(); //Initialized graphics
		
		/*Draw what we need here*/
		table.draw(g);
		piece.draw(g);
		
		g.dispose(); //Doesn't fill ram with unnecessary stuff.
		buffer.show();//Show what buffer has
		
		
	}//End of draw
	
	public void captureKey(){
		addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					piece.pos.moveRight();
					return;
				}
				else if(e.getKeyCode() == KeyEvent.VK_LEFT){
					piece.pos.moveLeft();
					return;
				}
				else if(e.getKeyCode() == KeyEvent.VK_DOWN){
					piece.pos.moveDown();
					return;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});//End of addKeyListener
	}//End of captureKey method
}// End of SDD method

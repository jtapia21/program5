package program5;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class SDD extends Canvas {

	private BufferStrategy buffer;
	private Graphics g;
	Image image;
	Table table;
	ArrayList<Piece> pieces = new ArrayList<Piece>(10);
	Piece activePiece;
	
	public SDD(int width, int height){
		//this.setPreferredSize(new Dimension(width, height));
		setSize(width, height);
		table = new Table(this);
		image = new Image(this);
		activePiece = new Piece(this, Tetrimino.getTetriminoType(Main.gen.nextInt(6)));
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
		
		g.clearRect(0, 0, (int)(Table.COLUMN * Table.SIDE + table.pos.X), (int)(Table.ROW * Table.SIDE + table.pos.Y));
		/*Draw what we need here*/
		table.draw(g);
		for (Piece p : pieces) {
			p.draw(g);
		}
		activePiece.draw(g);
		
		g.dispose(); //Doesn't fill ram with unnecessary stuff.
		buffer.show();//Show what buffer has
		
		
	}//End of draw
	
	public void captureKey(){
		addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					if (activePiece.pos.nextStepWithinBounds(1, 0, activePiece.pieceType)) {
						activePiece.pos.moveRight();
					}
					return;
				}
				else if(e.getKeyCode() == KeyEvent.VK_LEFT){
					if (activePiece.pos.nextStepWithinBounds(-1, 0, activePiece.pieceType))
						activePiece.pos.moveLeft();
					return;
				}
				else if(e.getKeyCode() == KeyEvent.VK_DOWN){
					if (activePiece.pos.nextStepWithinBounds(0, 1, activePiece.pieceType))
						activePiece.pos.moveDown();
					return;
				} else if (e.getKeyCode() == KeyEvent.VK_J) {//debug to retire piece manually
					nextPiece();
				}
				//make sure we didn't move outside bounds
				if (!activePiece.pos.isWithinBounds()) {
					System.err.println("DEBUG! moved piece outside of bounds...");
					//TODO: piece.destroy()
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

	public Piece getActivePiece() {
		return activePiece;
	}
	public void setActivePiece(Piece activePiece) {
		this.activePiece = activePiece;
	}
	
	//This method generates the next piece to at the top of the board, and retires the old one.
	public void nextPiece() {
		pieces.add(activePiece);
		activePiece = new Piece(this, Tetrimino.getTetriminoType(Main.gen.nextInt(6)));
	}
}// End of SDD method

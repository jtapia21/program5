package program5;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;

import javax.management.relation.RelationServiceNotRegisteredException;

@SuppressWarnings("serial")
public class SDD extends Canvas {

	private BufferStrategy buffer;
	private Graphics g;
	Image image;
	Table table;
	ArrayList<Piece> pieces = new ArrayList<Piece>(10);
	Piece activePiece;
	long lastMove = System.currentTimeMillis();//this keeps track of the last successful move, which keeps the piece alive and unlocked until you stop moving it
	Piece[][] occupiedLocations; //computed list of occupied locations, keeping track of rotated pieces, computed once a piece is locked in.
	
	public SDD(int width, int height){
		//this.setPreferredSize(new Dimension(width, height));
		occupiedLocations = new Piece[Table.COLUMN][Table.ROW];
		//array is already defaulted to false, thankfully
		setSize(width, height);
		table = new Table(this);
		image = new Image(this);
		activePiece = new Piece(this, Tetrimino.getTetriminoType(Main.gen.nextInt(6)));//first piece can be anything but I
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
					if (activePiece.moveRight()) {
						lastMove = System.currentTimeMillis();
					}
				} else if(e.getKeyCode() == KeyEvent.VK_LEFT){
					if (activePiece.moveLeft()) {
						lastMove = System.currentTimeMillis();
					}
				} else if(e.getKeyCode() == KeyEvent.VK_DOWN){
					if (activePiece.moveDown()) {
						lastMove = System.currentTimeMillis();
					}
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
	
	//move pieces according to gravity and lock in the activePiece/spawn a new one if necessary
	public void gravityTick() {
		boolean resetCollisionMap = false;
		for (Piece p : pieces) {
			if (p.moveDown()) {
				//one of the non-active pieces moved, probably because a line cleared or something
				resetCollisionMap = true;//so we have to regen the occupiedLocations map to account for a moved locked piece
			}
		}
		if (!activePiece.moveDown()) {//we couldn't move the activePiece down due to gravity, it must be directly on top of something
			if (System.currentTimeMillis() - Main.tickSpeed > lastMove) {//if the last move was more than 1 tick ago, then:
				nextPiece();//lock the latest piece, spawn a new one
			}
		}
		if (resetCollisionMap) {
			rebuildOccupiedLocations();
		}
	}
	//calculate score and clear lines if necessary
	public void scoreTick() {
		int linesClearedThisTime = 0;
		for (int y = Table.ROW-1; y>=0; y--) {
			for (int x = 0; x < Table.COLUMN; x++) {
				if (occupiedLocations[x][y] == null) {
					//this line is not full, do not remove it, do not add score.
					break;//move to the next line
				}
				if (x == Table.COLUMN -1) {
					//if we got here without breaking, we checked a full line
					//TODO: this simply removes the entire piece
					//      we probably want it to remove just the part that makes a full line, instead of the entire piece.
					//      however, I think this is still valid is some varients of Tetris.
					//remove the entire line of pieces
					linesClearedThisTime++;
					for (int z = 0; z < Table.COLUMN; z++) {
						//remove all affected Tetriminos
						Piece me = occupiedLocations[z][y];
						pieces.remove(me);
						occupiedLocations[z][y] = null;
						removeAllInstances(me);
					}
				}
			}
		}
		//update our level
		Main.linesFilled += linesClearedThisTime;
		//calculate score
		switch (linesClearedThisTime) {
		case 0:
			break;
		case 1:
			Main.gameScore += 40 * (int)(Main.linesFilled/10);
			break;
		case 2:
			Main.gameScore += 100 * (int)(Main.linesFilled/10);
			break;
		case 3:
			Main.gameScore += 300 * (int)(Main.linesFilled/10);
			break;
		case 4:
		default:
			Main.gameScore += 40 * (int)(Main.linesFilled/10);
			break;
		}
		rebuildOccupiedLocations();
	}
	
	//This method generates the next piece to at the top of the board, and retires the old one.
	public void nextPiece() {
		pieces.add(activePiece);
		consumeToOccupiedLocations(activePiece);
		activePiece = new Piece(this, Tetrimino.getTetriminoType(Main.gen.nextInt(7)));
	}

	private void consumeToOccupiedLocations(Piece p) {
		if (p == null) {
			System.err.println("Debug: Error: tried to consume null piece");
			return;
		}
		Tetrimino t = Tetrimino.consult(p.getPieceType(), p);
		for (int i = 0; i < 4; i++)
			occupiedLocations[(int)(p.getPosition().getX()+t.stuff[i].getX())][(int)(p.getPosition().getY()+t.stuff[i].getY())] = p;
		//now we've added the block locations to our collision check grid, and they won't ever move because the pieces are already locked in at this point
	}
	
	private void rebuildOccupiedLocations() {
		occupiedLocations = new Piece[Table.COLUMN][Table.ROW];
		for (Piece p : pieces) {
			consumeToOccupiedLocations(p);
		}
	}
	
	public Piece[][] getOccupiedLocations() {
		return occupiedLocations;
	}
	
	private void removeAllInstances (Piece p) {
		for (int x = 0; x < Table.COLUMN; x++) {
			for (int y = 0; y < Table.ROW; y++) {
				if (occupiedLocations[x][y] != null &&occupiedLocations[x][y].equals(p)) {
					occupiedLocations[x][y] = null;
				}
			}
		}
	}
}// End of SDD method

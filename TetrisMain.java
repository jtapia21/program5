package Tetris;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TetrisMain extends Canvas implements Runnable, KeyListener{
	
	public static final int WIDTH = 400, HEIGHT = 565;
	
	public static void main(String args[]) {
		
		// Set GUI Display
		JFrame frame = new JFrame("Tetris");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		
		JMenuBar bar = new JMenuBar();
		bar.setBounds(0, 0, WIDTH, 25);
		
		JMenu menu = new JMenu("Menu");
		menu.setBounds(0, 0, 45, 24);
		
		JMenuItem sr = new JMenuItem("Start/Restart");
		sr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start or restarting game...");
			}
		});

		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Quitting...");
				System.exit(0);
			}
		});
		
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("about us...");
			}
		});
		
		JMenuItem help = new JMenuItem("Help");
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("how to use the program...");
			}
		});
		
		
		// Add the GUI display
		TetrisMain tm = new TetrisMain();
		tm.setBounds(0, 25, WIDTH, HEIGHT - 25);
		
		frame.add(tm);
		menu.add(sr);
		menu.add(about);
		menu.add(help);
		menu.add(quit);
		bar.add(menu);
		frame.add(bar);
		frame.setVisible(true);
		tm.start();
		
	}// End of main 
	
	
	public void start() {
		Thread t = new Thread(this);
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}// End of start
	
	
	public void run() {
		boolean running = true;
		while(running) {
			update();
			BufferStrategy buf = getBufferStrategy();
			if(buf == null) {
				createBufferStrategy(3);
				continue;
			}// End of if loop
			Graphics2D g = (Graphics2D) buf.getDrawGraphics();
			render(g);
			buf.show();
		}// End of while loop
	}// End of runGame
	
	
	public void update() {
		
	}// End of update
	
	
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Calibri", Font.PLAIN, 20));
		g.drawString("Tetris", 170, 50);
	}// End of render method
	
	
	public void keyPressed (KeyEvent e){
		
	}// End of keyPressed
	
	
	public void keyTyped(KeyEvent e) {
		
	}// End of keyTyped 
	
	public void keyRealeased(KeyEvent e){
		
	}// End of keyRealeased method
	
}// End of TetrisMain
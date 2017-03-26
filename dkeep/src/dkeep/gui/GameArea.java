package dkeep.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import dkeep.logic.Game;
import dkeep.logic.Personality;


public class GameArea extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game = null;
	private JLabel gameStatus;
	private BufferedImage background;
	private BufferedImage hero;
	private BufferedImage heroArmed;
	private BufferedImage heroKey;
	private BufferedImage doorClosed;
	private BufferedImage doorOpen;
	private BufferedImage exit;
	private BufferedImage lever;
	private BufferedImage key;
	private BufferedImage ogre;
	private BufferedImage club;
	private BufferedImage guard;
	private BufferedImage guardSleeping;
	private BufferedImage wall;
	private BufferedImage floor;

	public GameArea(){
		try {
            // Set System L&F
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void loadImages() throws IOException{
		floor = ImageIO.read(new File("res/floor.png"));
		wall = ImageIO.read(new File("res/wall.png"));
		exit = ImageIO.read(new File("res/exit.png"));
		hero = ImageIO.read(new File("res/hero_unarmed.png"));
		lever = ImageIO.read(new File("res/lever.png"));
		key = ImageIO.read(new File("res/key.png"));
		guardSleeping = ImageIO.read(new File("res/guard_sleeping.png"));
		guard = ImageIO.read(new File("res/guard.png"));
		heroArmed = ImageIO.read(new File("res/hero_armed.png"));
		heroKey = ImageIO.read(new File("res/hero_key.png"));
		ogre = ImageIO.read(new File("res/ogre.png"));
		club = ImageIO.read(new File("res/club.png"));
		doorClosed = ImageIO.read(new File("res/door_closed.png"));
		doorOpen = ImageIO.read(new File("res/door_open.png"));
		
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}

	public void newGame(GameSettings settings) {
		
		
	}

	
	
}

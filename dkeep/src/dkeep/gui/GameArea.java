package dkeep.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.UIManager;

import dkeep.logic.Direction;
import dkeep.logic.Game;
import dkeep.logic.State;


public class GameArea extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game = null;
	private BufferedImage background;
	private BufferedImage hero;
	private BufferedImage heroArmed;
	private BufferedImage heroKey;
	private BufferedImage door;
	private BufferedImage exit;
	private BufferedImage lever;
	private BufferedImage key;
	private BufferedImage ogre;
	private BufferedImage club;
	private BufferedImage guard;
	private BufferedImage guardSleeping;
	private BufferedImage wall;
	private BufferedImage floor;
	private BufferedImage over;
	private BufferedImage win;
	
	public GameArea() throws IOException{
		addKeyListener(this);
		
		try {
            // Set System L&F
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		loadImages();
	}
	
	void loadImages() throws IOException{
		floor = ImageIO.read(new File("./res/floor.png"));
		wall = ImageIO.read(new File("./res/wall.png"));
		exit = ImageIO.read(new File("./res/door_open.png"));
		hero = ImageIO.read(new File("./res/hero_unarmed.png"));
		lever = ImageIO.read(new File("./res/lever.png"));
		key = ImageIO.read(new File("./res/key.png"));
		guardSleeping = ImageIO.read(new File("./res/guard_sleeping.png"));
		guard = ImageIO.read(new File("./res/guard.png"));
		heroArmed = ImageIO.read(new File("./res/hero_armed.png"));
		heroKey = ImageIO.read(new File("./res/hero_key.png"));
		ogre = ImageIO.read(new File("./res/ogre.png"));
		club = ImageIO.read(new File("./res/club.png"));
		door = ImageIO.read(new File("./res/door_closed.png"));
		background = ImageIO.read(new File("res/start.png"));
		over = ImageIO.read(new File("res/over.png"));
		win = ImageIO.read(new File("res/win.png"));
		
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		switch (arg0.getKeyCode()) {
			case KeyEvent.VK_UP: 	
				game.updateGame(Direction.UP); 
				break;
			case KeyEvent.VK_DOWN: 	
				game.updateGame(Direction.DOWN); 
				break;
			case KeyEvent.VK_LEFT: 	
				game.updateGame(Direction.LEFT); 
				break;
			case KeyEvent.VK_RIGHT:
				game.updateGame(Direction.RIGHT); 
				break;
			default: 
				break;
		}
		
		if(game.isGameOver())
			this.setEnabled(false);
		
		repaint();

	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	    if(game == null){
			g.drawImage(background, 0, 0,getWidth(),getHeight(),null);
			return;
		}
	    
	    if(game.getState() == State.DEFEAT){
			g.drawImage(over, 0, 0,getWidth(),getHeight(),null);
			return;
	    }
		
	    if(game.getState() == State.WON){
			g.drawImage(win, 0, 0,getWidth(),getHeight(),null);
			return;
	    }
	    
		char[][] map = game.getCurrentLevel().printMap();
	
		if(!game.isGameOver()){
			int Hnorm = getHeight() / map.length;
			int Wnorm = (getWidth() - 0) / map.length;
				
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++){
					switch (map[i][j]) {
					case ' ': 
						g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
						break;
					case 'S':
						g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
						g.drawImage(exit, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
						break;
					case 'X': 
						g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
						g.drawImage(wall, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
						break;
					case 'H': 
						g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
						g.drawImage(hero, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
						break;
					case 'A': 
						g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
						g.drawImage(heroArmed, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
					break;
					case 'k': 
						g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
						if(game.getState() == State.LEVEL1)
							g.drawImage(lever, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
						else g.drawImage(key, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
						break;
					case 'O': 
						g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
						g.drawImage(ogre, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
						break;
					case 'G': 
						g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
						g.drawImage(guard, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
						break;
					case 'g': 
						g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
						g.drawImage(guardSleeping, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
						break;
					case 'K': 
						g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
						g.drawImage(heroKey, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
						break;
					case 'I':
						g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
						g.drawImage(door, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
						break;
					case '*':
						g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
						g.drawImage(club, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
						break;
					case '$':
						g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
						g.drawImage(key, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
					default: 
						break;
					}
				}
			}
		}	
		
	}

	public void newGame(GameSettings settings) {
		if(settings.getMap() == null){
			this.game = new Game(settings.getPersonality());
			this.game.setOgreCount(settings.getOgreNum());
		}
		else {
			this.game = new Game(settings.getMap());
		}
		
		requestFocus();
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
}

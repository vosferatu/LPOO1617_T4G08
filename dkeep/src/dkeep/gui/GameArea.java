package dkeep.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dkeep.logic.Game;


public class GameArea extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game = null;
	private JLabel gameStatus;
	private Image background;
	private Image hero;
	private Image heroArmed;
	private Image heroKey;
	private Image door;
	private Image exit;
	private Image lever;
	private Image key;
	private Image ogre;
	private Image club;
	private Image guard;
	private Image guardSleeping;
	private Image wall;
	private Image floor;

	public GameArea(){
		gameStatus = new JLabel("You can start a new game", SwingConstants.CENTER);
		gameStatus.setBounds(257, 0, 324, 30);
		getRootPane().add(gameStatus);
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

	
	
}

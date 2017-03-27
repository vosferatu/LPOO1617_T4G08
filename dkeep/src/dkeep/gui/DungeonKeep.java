package dkeep.gui;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

import java.awt.Dimension;

import javax.swing.UIManager;


public class DungeonKeep {
	
	private GameArea gamePanel;
	private GameSettings settings;
	private GameOptions optionsPanel;
	private LevelEditor editor;
	private JFrame generalPanel;
	
	private JButton btnNewGame;
	private JButton btnLevelEditor;
	private JButton btnExitGame;
	private JButton btnOptions;

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws URISyntaxException 
	 */
	public DungeonKeep() throws IOException {
		generalPanel = new JFrame();
		generalPanel.setTitle("DUNGEON KEEP");
		generalPanel.setIconImage(Toolkit.getDefaultToolkit().getImage("./res/hero_armed.png")); 
		generalPanel.setBounds(100, 100, 707, 710);
		generalPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		generalPanel.getContentPane().setLayout(null);
		
		settings = new GameSettings();
		optionsPanel = new GameOptions();
		
		gamePanel = new GameArea();
		gamePanel.setFocusTraversalKeysEnabled(false);
		gamePanel.setBounds(0, 0, 707, 624);
		generalPanel.getContentPane().add(gamePanel);
		gamePanel.setFocusable(true);
		
		editor = new LevelEditor();
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		generalPanel.setLocation(screen.width/2 - generalPanel.getSize().width/2,
				screen.height/2 - generalPanel.getSize().height/2);
	
		generalPanel.requestFocus();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//---------------------------------------BUTTONS---------------------------------------------------
		btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewGame.setBounds(0, 624, 193, 30);
		generalPanel.getContentPane().add(btnNewGame);
		btnNewGame.setFocusable(false);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(settings.getMap() == null)
					settings = optionsPanel.getSettings();
				gamePanel.setEnabled(true);
				gamePanel.setFocusable(true);
				gamePanel.requestFocus();
				gamePanel.newGame(settings);
			}
		});
		
		btnLevelEditor = new JButton("Level Editor");
		btnLevelEditor.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLevelEditor.setFocusable(false);
		btnLevelEditor.setBounds(191, 624, 187, 30);
		generalPanel.getContentPane().add(btnLevelEditor);
		btnLevelEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.setEnabled(true);
				editor.setVisible(true);
				generalPanel.setEnabled(false);
				generalPanel.setVisible(false);
				editor.setFocusable(true);
			}
		});
		
		btnOptions = new JButton("Options");
		btnOptions.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnOptions.setBounds(377, 624, 193, 30);
		btnOptions.setFocusable(false);
		generalPanel.getContentPane().add(btnOptions);
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				optionsPanel.setVisible(true);
			}
		});

		
		btnExitGame = new JButton("Exit");
		btnExitGame.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExitGame.setBounds(569, 624, 116, 30);
		btnExitGame.setFocusable(false);
		generalPanel.getContentPane().add(btnExitGame);
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		editor.addWindowListener(new WindowListener(){
			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				generalPanel.setFocusable(true);
				generalPanel.setEnabled(true);
				generalPanel.setVisible(true);
				gamePanel.requestFocus();
				
				if(editor.isDone()){
					settings.setMap(editor.getSettings().getMap());
					generalPanel.setEnabled(true);
					generalPanel.setFocusable(true);
					generalPanel.setVisible(true);
				}				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {

			}

			@Override
			public void windowIconified(WindowEvent arg0) {

			}

			@Override
			public void windowOpened(WindowEvent arg0) {

			}
		});
		
	}

	public void run() {
		try {
            // Set System L&F
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		 
		try {
			generalPanel.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GameSettings getSettings() {
		return settings;
	}

	public void setSettings(GameSettings settings) {
		this.settings = settings;
	}
}

package dkeep.gui;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

import java.awt.Dimension;

import javax.swing.UIManager;


public class DungeonKeep extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private GameArea gamePanel;
	private GameSettings settings;
	private GameOptions optionsPanel;
	private LevelEditor editor;
	
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
		setTitle("DUNGEON KEEP");
		setIconImage(Toolkit.getDefaultToolkit().getImage("./res/hero_armed.png")); 
		setBounds(100, 100, 707, 710);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		settings = new GameSettings();
		optionsPanel = new GameOptions();
		
		gamePanel = new GameArea();
		gamePanel.setFocusTraversalKeysEnabled(false);
		gamePanel.setBounds(0, 0, 707, 624);
		getContentPane().add(gamePanel);
		gamePanel.setFocusable(true);
		
		editor = new LevelEditor();
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screen.width/2 - this.getSize().width/2, screen.height/2 - this.getSize().height/2);
	
		initialize();
		requestFocus();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*
		if(gamePanel.getGame() != null && gamePanel.getGame().isGameOver()){
			btnLevelEditor.setEnabled(true); //when to turn true
			btnOptions.setEnabled(true);
		}
		*/
		
		//---------------------------------------BUTTONS---------------------------------------------------
		btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewGame.setBounds(0, 624, 193, 30);
		getContentPane().add(btnNewGame);
		btnNewGame.setFocusable(false);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(settings.getMap() == null)
					settings = optionsPanel.getSettings();
				btnLevelEditor.setEnabled(false); //when to turn true
				btnOptions.setEnabled(false);
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
		getContentPane().add(btnLevelEditor);
		btnLevelEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.setEnabled(true);
				editor.setVisible(true);
				setEnabled(false);
				setVisible(false);
				setFocusable(true);
			}
		});
		
		btnOptions = new JButton("Options");
		btnOptions.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnOptions.setBounds(377, 624, 193, 30);
		btnOptions.setFocusable(false);
		getContentPane().add(btnOptions);
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				optionsPanel.setVisible(true);
			}
		});

		
		btnExitGame = new JButton("Exit");
		btnExitGame.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExitGame.setBounds(569, 624, 116, 30);
		btnExitGame.setFocusable(false);
		getContentPane().add(btnExitGame);
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
			this.setVisible(true);
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

package dkeep.gui;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.UIManager;


public class DungeonKeep extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private GameArea gamePanel;
	private GameSettings settings;
	private GameOptions optionsPanel;
	private JButton btnNewGame;
	private JButton btnLevelEditor;
	private JButton btnExitGame;
	private JButton btnOptions;

	/**
	 * Create the application.
	 */
	public DungeonKeep() {
		initialize();
		requestFocus();
		
		settings = new GameSettings();
		optionsPanel = new GameOptions();
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screen.width/2 - this.getSize().width/2, screen.height/2 - this.getSize().height/2);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("DUNGEON KEEP");
		setForeground(Color.BLACK);
		setFont(new Font("Yu Gothic", Font.PLAIN, 20));
		setBackground(Color.DARK_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jo\u00E3o Mendes\\git\\LPOO1617_T4G08\\dkeep\\res\\hero_armed.png")); //TODO:change
		setBounds(100, 100, 707, 710);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		gamePanel = new GameArea();
		getContentPane().add(gamePanel);
		
		//---------------------------------------BUTTONS---------------------------------------------------
		btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settings = optionsPanel.getSettings();
				gamePanel.newGame(settings);
			}
		});
		btnNewGame.setBounds(0, 624, 193, 30);
		getContentPane().add(btnNewGame);
		
		btnLevelEditor = new JButton("Level Editor");
		btnLevelEditor.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLevelEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLevelEditor.setBounds(191, 624, 187, 30);
		getContentPane().add(btnLevelEditor);
		
		btnOptions = new JButton("Options");
		btnOptions.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				optionsPanel.setVisible(true);
			}
		});
		btnOptions.setBounds(377, 624, 193, 30);
		getContentPane().add(btnOptions);
		
		btnExitGame = new JButton("Exit");
		btnExitGame.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExitGame.setBounds(569, 624, 116, 30);
		getContentPane().add(btnExitGame);
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

package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dkeep.logic.Direction;
import dkeep.logic.Game;
import dkeep.logic.Personality;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;


public class DungeonKeep {

	private Game game;
	private JFrame frmDungeonKeep;
	private JLabel gameStatus;
	private JLabel lblNumberOfOgres;
	private JLabel lblGuardPersonality;
	private JButton btnNewGame;
	private JButton btnUp;
	private JButton btnRight;
	private JButton btnLeft;
	private JButton btnDown;
	private JButton btnExitGame;
	private JComboBox<Personality> comboBox;
	private JTextArea textArea;
	private JSlider slider;
	private int ogreCount = 1;
	private Personality personality = Personality.SIMPLE;

	/**
	 * Create the application.
	 */
	public DungeonKeep() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDungeonKeep = new JFrame();
		frmDungeonKeep.setTitle("Dungeon Keep");
		frmDungeonKeep.setForeground(Color.BLACK);
		frmDungeonKeep.setFont(new Font("Yu Gothic", Font.PLAIN, 20));
		frmDungeonKeep.setBackground(Color.DARK_GRAY);
		frmDungeonKeep.setBounds(100, 100, 703, 542);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);
		
		lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(17, 18, 109, 14);
		frmDungeonKeep.getContentPane().add(lblNumberOfOgres);
		
		lblGuardPersonality = new JLabel("Guard personality");
		lblGuardPersonality.setBounds(17, 72, 109, 14);
		frmDungeonKeep.getContentPane().add(lblGuardPersonality);
		
		btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game = new Game(personality);
				game.setOgreCount(ogreCount);
				String gameText = game + "";
				textArea.setText(gameText);
				updateLabel(gameStatus);
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
			}
		});
		btnNewGame.setBounds(500, 18, 109, 33);
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
		btnUp = new JButton("Up");
		btnUp.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateGame(Direction.UP);
			}
		});
		btnUp.setBounds(520, 230, 71, 20);
		frmDungeonKeep.getContentPane().add(btnUp);
		
		btnRight = new JButton("Right");
		btnRight.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnRight.setEnabled(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(Direction.RIGHT);
			}
		});
		btnRight.setBounds(566, 262, 71, 20);
		frmDungeonKeep.getContentPane().add(btnRight);
		
		comboBox = new JComboBox<Personality>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personality = (Personality)comboBox.getSelectedItem();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<Personality>(Personality.values()));
		comboBox.setMaximumRowCount(5);
		comboBox.setBounds(123, 72, 109, 20);
		frmDungeonKeep.getContentPane().add(comboBox);
		
		btnLeft = new JButton("Left");
		btnLeft.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnLeft.setEnabled(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(Direction.LEFT);
			}
		});
		btnLeft.setBounds(467, 262, 71, 20);
		frmDungeonKeep.getContentPane().add(btnLeft);
		
		btnDown = new JButton("Down");
		btnDown.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(Direction.DOWN);
			}
		});
		btnDown.setBounds(520, 296, 71, 20);
		frmDungeonKeep.getContentPane().add(btnDown);
		
		btnExitGame = new JButton("Exit");
		btnExitGame.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExitGame.setBounds(520, 431, 89, 23);
		frmDungeonKeep.getContentPane().add(btnExitGame);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 16));
		textArea.setBounds(39, 129, 324, 268);
		frmDungeonKeep.getContentPane().add(textArea);
		
		gameStatus = new JLabel("You can start a new game");
		gameStatus.setBounds(17, 460, 324, 14);
		frmDungeonKeep.getContentPane().add(gameStatus);
		
		slider = new JSlider();
		slider.setFont(new Font("Dialog", Font.PLAIN, 10));
		slider.setValue(1);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(!slider.getValueIsAdjusting()) {
					ogreCount = (int)slider.getValue();
				}
			}
		});
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);
		slider.setToolTipText("");
		slider.setPaintTicks(true);
		slider.setMinimum(1);
		slider.setMaximum(5);
		slider.setBounds(124, 18, 147, 39);
		frmDungeonKeep.getContentPane().add(slider);
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public void updateGame(Direction move){
		game.updateGame(move); //check why doens't print last frame
		if(game != null){
			textArea.setText(game.toString());
			this.updateLabel(gameStatus);
		}
		
		if(game.isGameOver()){
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
			
			textArea.setText(game.toString());
			this.updateLabel(gameStatus);
			
			game = null;
		}
	}
	
	public void updateLabel(JLabel status){
		if(game == null){
			status.setText("You can start a new game.");
			return;
		}
		
		switch(game.getState()){
		case DEFEAT:
			status.setText("YOU LOST!");
			break;
		case WON:
			status.setText("YOU WON!");
			break;
		case LEVEL1:
			status.setText("Escape the Dungeon Keeper...");
			break;
		case LEVEL2:
			status.setText("Oh no! Now you have to deal with the Keep's horde of Crazy Ogres...");
			break;
			default:
				status.setText("You can start a new game.");
			break;
		}
		
	}

	public void run() {
		try {
			this.frmDungeonKeep.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}

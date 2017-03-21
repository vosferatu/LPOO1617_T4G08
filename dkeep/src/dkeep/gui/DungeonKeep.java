package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dkeep.logic.Direction;
import dkeep.logic.Game;
import dkeep.logic.KeepLevel;
import dkeep.logic.Personality;
import dkeep.logic.State;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;

public class DungeonKeep {

	private Game game;
	private JFrame frame;
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DungeonKeep window = new DungeonKeep();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frame = new JFrame();
		frame.setBounds(100, 100, 568, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(17, 18, 109, 14);
		frame.getContentPane().add(lblNumberOfOgres);
		
		lblGuardPersonality = new JLabel("Guard personality");
		lblGuardPersonality.setBounds(17, 53, 109, 14);
		frame.getContentPane().add(lblGuardPersonality);
		
		btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game = new Game(personality);
				game.setOgreCount(ogreCount);
				String gameText = game + "";
				textArea.setText(gameText);
				gameStatus.setText("Level 1 - Escape the guard!");
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
			}
		});
		btnNewGame.setBounds(395, 18, 109, 33);
		frame.getContentPane().add(btnNewGame);
		
		btnUp = new JButton("Up");
		btnUp.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game != null)
					textArea.setText(game.toString());
				if(game.getCurrentLevel().updateGame(Direction.UP)) {
					if(game.getCurrentLevel().isHeroDead()) {
						gameStatus.setText("You Lost");
					} else if(game.getState() == State.LEVEL2) {
						gameStatus.setText("Level 2 - Oh no, a wild ogre appears...");
						game.setCurrentLevel(new KeepLevel(ogreCount));
					} else {
						gameStatus.setText("You Won!");
					}
				}
			}
		});
		btnUp.setBounds(413, 181, 71, 20);
		frame.getContentPane().add(btnUp);
		
		btnRight = new JButton("Right");
		btnRight.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnRight.setEnabled(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(game != null)
					textArea.setText(game.toString());
				if(game.getCurrentLevel().updateGame(Direction.RIGHT)) {
					if(game.getCurrentLevel().isHeroDead()) {
						gameStatus.setText("You Lost");
					} else if(game.getState() == State.LEVEL2) {
						gameStatus.setText("Level 2 - Oh no, a wild ogre appears...");
						game.setCurrentLevel(new KeepLevel(ogreCount));
					} else {
						gameStatus.setText("You Won!");
					}
				}
			}
		});
		btnRight.setBounds(453, 206, 71, 20);
		frame.getContentPane().add(btnRight);
		
		comboBox = new JComboBox<Personality>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personality = (Personality)comboBox.getSelectedItem();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<Personality>(Personality.values()));
		comboBox.setMaximumRowCount(5);
		comboBox.setBounds(123, 50, 109, 20);
		frame.getContentPane().add(comboBox);
		
		btnLeft = new JButton("Left");
		btnLeft.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnLeft.setEnabled(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(game != null)
					textArea.setText(game.toString());
				if(game.getCurrentLevel().updateGame(Direction.LEFT)) {
					if(game.getCurrentLevel().isHeroDead()) {
						gameStatus.setText("You Lost");
					} else if(game.getState() == State.LEVEL2) {
						gameStatus.setText("Level 2 - Oh no, a wild ogre appears...");
						game.setCurrentLevel(new KeepLevel(ogreCount));
					} else {
						gameStatus.setText("You Won!");
					}
				}
			}
		});
		btnLeft.setBounds(372, 206, 71, 20);
		frame.getContentPane().add(btnLeft);
		
		btnDown = new JButton("Down");
		btnDown.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(game != null)
					textArea.setText(game.toString());
				if(game.getCurrentLevel().updateGame(Direction.DOWN)) {
					if(game.getCurrentLevel().isHeroDead()) {
						gameStatus.setText("You Lost");
					} else if(game.getState() == State.LEVEL2) {
						gameStatus.setText("Level 2 - Oh no, a wild ogre appears...");
						game.setCurrentLevel(new KeepLevel(ogreCount));
					} else {
						gameStatus.setText("You Won!");
					}
				}
			}
		});
		btnDown.setBounds(413, 230, 71, 20);
		frame.getContentPane().add(btnDown);
		
		btnExitGame = new JButton("Exit");
		btnExitGame.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExitGame.setBounds(402, 327, 89, 23);
		frame.getContentPane().add(btnExitGame);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 16));
		textArea.setBounds(17, 82, 324, 268);
		frame.getContentPane().add(textArea);
		
		gameStatus = new JLabel("You can start a new game");
		gameStatus.setBounds(17, 367, 324, 14);
		frame.getContentPane().add(gameStatus);
		
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
		slider.setBounds(123, 2, 147, 39);
		frame.getContentPane().add(slider);
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}

package dkeep.gui;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class LevelEditor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameSettings settings;
	private EditorPanel editor;
	private int mapSize;
	private boolean done = false;
	private EditorOptions options;
	

	

	public LevelEditor() {
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Keep Editor");
		setIconImage(Toolkit.getDefaultToolkit().getImage("./res/hero_armed.png"));
		setVisible(false);
		setEnabled(false);
		done = true;
		
		initialize();
	}


	private void initialize() {
		setBounds(100, 100, 632, 632);

		
	}


	public GameSettings getSettings() {
		return settings;
	}
}

package dkeep.gui;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LevelEditor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EditorOptions options;
	private EditorPanel editor;
	private JLabel lblCharacter;
	private JComboBox<String> objects;
	private JButton ok;
	
	private GameSettings settings;
	private int mapSize;
	private boolean done = false;
	

	

	public LevelEditor() throws IOException {
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Keep Editor");
		setIconImage(Toolkit.getDefaultToolkit().getImage("./res/hero_armed.png"));
		setVisible(false);
		setEnabled(false);
		done = true;
		
		initialize();
	}


	private void initialize() throws IOException {
		setBounds(100, 100, 700, 700);
		
		objects = new JComboBox<String>();
		objects.setEnabled(false);
		objects.setModel(new DefaultComboBoxModel<String>(new String[] {"Exit", "Hero", "Key", "Ogre", "Wall"}));
		objects.setSelectedIndex(0);
		objects.setBounds(185, 12, 92, 20);
		getContentPane().add(objects);
		
		lblCharacter = new JLabel("Character");
		lblCharacter.setEnabled(false);
		lblCharacter.setBounds(95, 15, 94, 14);
		getContentPane().add(lblCharacter);

		ok = new JButton("Ok");
		ok.setEnabled(false);
		ok.setBounds(410, 11, 89, 23);
		getContentPane().add(ok);
		
		editor = new EditorPanel();
		editor.setFocusTraversalKeysEnabled(false);
		editor.setBounds(0, 37, 678, 607);
		getContentPane().add(editor);
		
		options = new EditorOptions();

		
		options.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {

			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				setEnabled(true);
				lblCharacter.setEnabled(true);
				objects.setEnabled(true);
				ok.setEnabled(true);
				mapSize = options.getMapNum();
				editor.setMap(mapSize);
				editor.setEnabled(true);
				editor.setFocusable(true);
				editor.requestFocus();
					
			}

			@Override
			public void windowActivated(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
			
			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
	
			}

			@Override
			public void windowIconified(WindowEvent e) {

			}
		});
	}


	public GameSettings getSettings() {
		return settings;
	}
	
	public boolean isDone(){
		return done;
	}
}

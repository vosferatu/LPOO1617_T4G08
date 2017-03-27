package dkeep.gui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dkeep.logic.Personality;
import java.awt.Dimension;
import javax.swing.JButton;

public class GameOptions extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<Personality> comboBox;
	private JSlider slider;
	private GameSettings settings;
	private JLabel lblNumberOfOgres;
	private JLabel lblGuardPersonality;
	private JButton btnOk;
	
	
	public GameOptions() {
		try {
            // Set System L&F
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jo\u00E3o Mendes\\git\\LPOO1617_T4G08\\dkeep\\res\\hero_unarmed.png"));
		setTitle("Options");
		setBounds(100, 100, 416, 340);
		getContentPane().setLayout(null);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screen.width/2 - this.getSize().width/2, screen.height/2 - this.getSize().height/2);
		
		settings = new GameSettings();
		
		requestFocus();
		initialize();
	}


	private void initialize() {
		
		lblGuardPersonality = new JLabel("Guard Personality:");
		lblGuardPersonality.setBounds(15, 56, 134, 20);
		getContentPane().add(lblGuardPersonality);
		
		comboBox = new JComboBox<Personality>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settings.setPersonality((Personality)comboBox.getSelectedItem());
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<Personality>(Personality.values()));
		comboBox.setMaximumRowCount(5);
		comboBox.setBounds(185, 53, 172, 26);
		getContentPane().add(comboBox);
		
		lblNumberOfOgres = new JLabel("Number of Ogres:");
		lblNumberOfOgres.setBounds(15, 138, 134, 20);
		getContentPane().add(lblNumberOfOgres);
		
		slider = new JSlider();
		slider.setMinorTickSpacing(1);
		slider.setFont(new Font("Dialog", Font.PLAIN, 12));
		slider.setValue(1);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(!slider.getValueIsAdjusting()) {
					settings.setOgreNum((int)slider.getValue());
				}
			}
		});
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);
		slider.setMajorTickSpacing(1);
		slider.setToolTipText("");
		slider.setPaintTicks(true);
		slider.setMaximum(5);
		slider.setMinimum(1);
		slider.setBounds(186, 126, 200, 64);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		getContentPane().add(slider);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settings.setOgreNum((int)slider.getValue());
				settings.setPersonality((Personality)comboBox.getSelectedItem());
				setVisible(false);
			}
		});
		btnOk.setBounds(142, 220, 115, 29);
		getContentPane().add(btnOk);
	}

	public GameSettings getSettings() {
		return settings;
	}


	public void setSettings(GameSettings settings) {
		this.settings = settings;
	}
}

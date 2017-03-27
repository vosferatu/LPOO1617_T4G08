package dkeep.gui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Dimension;
import javax.swing.JButton;

public class EditorOptions extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JSlider slider;
	private JLabel mapSize;
	private JButton btnOk;
	private int mapNum = 8;
	
	
	public EditorOptions() {
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
		setBounds(100, 100, 586, 322);
		getContentPane().setLayout(null);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screen.width/2 - this.getSize().width/2, screen.height/2 - this.getSize().height/2);
		
		requestFocus();
		
		initialize();
	}


	private void initialize() {
		
		mapSize = new JLabel("Map Size:");
		mapSize.setFont(new Font("Tahoma", Font.PLAIN, 30));
		mapSize.setBounds(15, 77, 149, 42);
		getContentPane().add(mapSize);
		
		slider = new JSlider();
		slider.setMinorTickSpacing(1);
		slider.setFont(new Font("Dialog", Font.PLAIN, 12));
		slider.setValue(1);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(!slider.getValueIsAdjusting()) {
					setMapNum((int)slider.getValue());
				}
			}
		});
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(4);
		slider.setMinorTickSpacing(1);
		slider.setToolTipText("");
		slider.setPaintTicks(true);
		slider.setMaximum(40);
		slider.setMinimum(8);
		slider.setBounds(155, 59, 394, 85);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		getContentPane().add(slider);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setMapNum((int)slider.getValue());
				setVisible(false);
			}
		});
		btnOk.setBounds(183, 176, 132, 49);
		getContentPane().add(btnOk);
	}


	public int getMapNum() {
		return mapNum;
	}


	public void setMapNum(int mapNum) {
		this.mapNum = mapNum;
	}

}


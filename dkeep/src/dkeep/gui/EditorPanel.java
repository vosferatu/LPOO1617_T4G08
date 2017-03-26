package dkeep.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EditorPanel extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage hero;
	private BufferedImage ogre;
	private BufferedImage key;
	private BufferedImage wall;
	private BufferedImage exit;
	private BufferedImage floor;
	
	private boolean heroDone = false;
	private boolean exitDone = false;
	private boolean keyDone = false;
	private int ogreNum = 0;
	
	private char[][] map;
	private char placing;
	
	public EditorPanel() throws IOException {
		this.addMouseListener(this);
		setPlacing(' ');
		
		loadImages();
	}
	
	void loadImages() throws IOException {
		floor = ImageIO.read(new File("./res/floor.png"));
		wall = ImageIO.read(new File("./res/wall.png"));
		exit = ImageIO.read(new File("./res/door_closed.png"));
		hero = ImageIO.read(new File("./res/hero_unarmed.png"));
		key = ImageIO.read(new File("./res/key.png"));
		ogre = ImageIO.read(new File("./res/ogre.png"));		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(map == null)
			return;

		int Hnorm = getHeight() / map.length;
		int Wnorm = (getWidth() - 0) / map.length;
			
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++){
				switch (map[i][j]) {
				case ' ': 
					g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
					break;
				case 'X': 
					g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
					g.drawImage(wall, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
					break;
				case 'H': 
					g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
					g.drawImage(hero, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
					break;

				case 'k': 
					g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
					g.drawImage(key, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
					break;
				case 'O': 
					g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
					g.drawImage(ogre, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
					break;
				case 'I':
					g.drawImage(floor, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null);
					g.drawImage(exit, j*Wnorm, i*Hnorm, Wnorm, Hnorm, null); 
					break;
				default: 
					break;
				}
			}
		}
	}
	
	public void setMap(int size){
		map = new char[size][size];
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map.length; j++){ //square
				if(i == 0 || i == (map.length -1) 
						|| j == 0 || j == (map.length-1))
					map[i][j]='X';
				else
					map[i][j]=' ';
			}
		}
		
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
		double xProportion = (getWidth() / map.length);
		double yProportion = (getHeight() / map.length);
		
		double mazeX = (double)arg0.getX() / xProportion;
		double mazeY = (double)arg0.getY() / yProportion;
		
		int x = (int) Math.floor(mazeX);
		int y = (int) Math.floor(mazeY);
		
		if(arg0.getButton() == MouseEvent.BUTTON3){ //RIGHT MOUSE BUTTON
			if(map[y][x] == 'k'){
				map[y][x] = ' ';
				keyDone = false;
			}
			if(map[y][x] == '0'){
				map[y][x] = ' ';
				keyDone = false;
			}
			if(map[y][x] == 'H'){
				map[y][x] = ' ';
				heroDone = false;
			}
			if(map[y][x] == 'I'){ //exit goes to wall
				map[y][x] = 'X';
				exitDone = false;
			}
			if(x != 0 && y != 0 && (x != map.length - 1) && (y != map.length - 1)){
				map[y][x]= ' ';
			}
		}
		
		if(arg0.getButton() == MouseEvent.BUTTON1){ //LEFT MOUSE BUTTON
			if( placing == 'I' && (x == 0 || y == 0 ||x == map.length -1 || y == map.length -1) //door on edges
					&& !exitDone
					&& 
				!((x == 0 && y == 0) || (x == map.length -1 && y == map.length -1) || (x == map.length -1 && y == 0) || (x == 0 && y == map.length -1))){ 
				
				map[y][x]= placing;
				exitDone = true;
			}
			if(placing == 'k' && map[y][x]== ' ' && !keyDone){
				map[y][x]= placing;
				keyDone = true;
			}
			if(placing == 'H' && map[y][x]== ' ' && !heroDone){
				map[y][x] = placing;
				heroDone = true;
			}
			if(placing == '0' && map[y][x]== ' ' && (ogreNum < 5)){
				map[y][x] = placing;
			}
			if(map[y][x] == ' ' && placing != 'S' && placing != 'H' && placing != 'k'){
				map[y][x] = placing;
			}
		}
		
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	public char getPlacing() {
		return placing;
	}

	public void setPlacing(char placing) {
		this.placing = placing;
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}

	public int getOgreNum() {
		return ogreNum;
	}

	public void setOgreNum(int ogreNum) {
		this.ogreNum = ogreNum;
	}
	
	
}

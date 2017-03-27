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
	private char character;
	
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

		int heightStretch = getHeight() / map.length;
		int widthStretch = (getWidth() - 0) / map.length;
			
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++){
				switch (map[i][j]) {
				case ' ': 
					g.drawImage(floor, j*widthStretch, i*heightStretch, widthStretch, heightStretch, null); 
					break;
				case 'X': 
					g.drawImage(floor, j*widthStretch, i*heightStretch, widthStretch, heightStretch, null);
					g.drawImage(wall, j*widthStretch, i*heightStretch, widthStretch, heightStretch, null); 
					break;
				case 'H': 
					g.drawImage(floor, j*widthStretch, i*heightStretch, widthStretch, heightStretch, null);
					g.drawImage(hero, j*widthStretch, i*heightStretch, widthStretch, heightStretch, null); 
					break;

				case 'k': 
					g.drawImage(floor, j*widthStretch, i*heightStretch, widthStretch, heightStretch, null);
					g.drawImage(key, j*widthStretch, i*heightStretch, widthStretch, heightStretch, null);
					break;
				case 'O': 
					g.drawImage(floor, j*widthStretch, i*heightStretch, widthStretch, heightStretch, null);
					g.drawImage(ogre, j*widthStretch, i*heightStretch, widthStretch, heightStretch, null); 
					break;
				case 'I':
					g.drawImage(floor, j*widthStretch, i*heightStretch, widthStretch, heightStretch, null);
					g.drawImage(exit, j*widthStretch, i*heightStretch, widthStretch, heightStretch, null); 
					break;
				default: 
					break;
				}
			}
		}
	}
	
	public void setMap(int size){
		character = 'S';
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
			if(map[y][x] == 'X'){ 
				map[y][x] = ' ';
				exitDone = false;
			}
			if(x != 0 && y != 0 && (x != map.length - 1) && (y != map.length - 1)){
				map[y][x] = ' ';
			}
		}
		
		if(arg0.getButton() == MouseEvent.BUTTON1){ //LEFT MOUSE BUTTON
			if( character == 'I' && (x == 0 || y == 0 ||x == map.length -1 || y == map.length -1) //door on edges
					&& !exitDone
					&& 
				!((x == 0 && y == 0) || (x == map.length -1 && y == map.length -1) || (x == map.length -1 && y == 0) || (x == 0 && y == map.length -1))){ 
				
				map[y][x]= character;
				exitDone = true;
			}
			if(character == 'k' && map[y][x]== ' ' && !keyDone){
				map[y][x]= character;
				keyDone = true;
			}
			if(character == 'H' && map[y][x]== ' ' && !heroDone){
				map[y][x] = character;
				heroDone = true;
			}
			if(character == '0' && map[y][x]== ' ' && (ogreNum < 5)){
				map[y][x] = character;
			}
			if(map[y][x] == ' ' && character != 'I' && character != 'H' && character != 'k'){
				map[y][x] = character;
			}
			if(character == 'X' && map[y][x]== ' '){ //check adjacent walls
				map[y][x]= character;
			}
		}
		
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	public char getPlacing() {
		return character;
	}

	public void setPlacing(char character) {
		this.character = character;
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

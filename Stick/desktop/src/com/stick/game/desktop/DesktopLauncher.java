package com.stick.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.stick.game.StickGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Stick";
		config.width = StickGame.V_WIDTH;
		config.height = StickGame.V_HEIGHT;
		/*config.resizable = false;
		config.useGL30 = false;
		//config.addIcon("C:\\LPOO\\Stick\\android\\res\\drawable-mhdpi\\ic_launcher.png", Files.FileType.Internal);
		*/
		new LwjglApplication(new StickGame(), config);
	}
}

package battleShipGUI;

import java.io.FileInputStream;
import java.io.ObjectInputStream;


import battleShipGame.Game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainMenu {
	
	//this window is static so that other screen classes can close it
	public static Stage window;
	
	public void display()
	{
		
		//window settings
		window = new Stage();
		window.setMinWidth(100);
		window.setMinHeight(250);
		window.setTitle("BattleShip");
		
		
		//Main Menu label
		Label logo = new Label();
		logo.setText("BattleShip");
		
		
		//start button
		Button startButton = new Button("Start");
		
		startButton.setOnAction(e -> {
			
			//this solves a very obscure bug that only occurs when the console mode button is clicked but the console does not run
			//which can be triggered by running the game from a .jar file causing the player attack method to never process input
			//making the buttons on the enemy grid in the battle screen not functional
			//this will be removed when the game is changed so that the window closes immediately after clicking the console mode button
			GUIApplication.game.settings.consoleMode = false;
			GUIApplication.game.info.setPlayerName(GUIApplication.game.settings.playerName);
			//change to the ship placement screen
			ShipPlacementScreen gameStart = new ShipPlacementScreen();
			Scene tempScene = gameStart.apply();
			window.setScene(tempScene);
			
		});
		//Continue button
		Button continueButton = new Button("Continue");
		continueButton.setOnAction(e -> {
			 try {
					
				 	FileInputStream fis = new FileInputStream("autoSave.ser");
				 	ObjectInputStream ois = new ObjectInputStream(fis);
				 	GUIApplication.game = (Game) ois.readObject();
				 	ois.close();
				 	
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			 if(GUIApplication.game != null)
			 {
				BattleScreen gameStart = new BattleScreen();
				Scene tempScene = gameStart.apply();
				window.setScene(tempScene);
			 }
		});
		
		// Load game button
		Button loadGameButton = new Button("load game");
		loadGameButton.setOnAction(e -> {
			LoadGameWindow LoadGame = new LoadGameWindow();
			LoadGame.display();
		});
		// Stats button
		Button statsButton = new Button("Stats");
		statsButton.setOnAction( e-> {
			StatsScreen gameStart = new StatsScreen();
			Scene tempScene = gameStart.apply();
			window.setScene(tempScene);
		});
		
		
		//settings button
		Button settingsButton = new Button("Settings");
		
		settingsButton.setOnAction(e -> {
			
			//open the settings window
			SettingsWindow settings = new SettingsWindow();
			settings.display();
			
		});
		
		
		//console mode button
		Button consoleMode = new Button("Console Mode");
		
		consoleMode.setOnAction(e -> {
			
			//run the game in console mode
			GUIApplication.game.run();
			
			
			//close the window after the game is done
			window.close();
			
			
		});
		
		
		//exit button
		Button exitButton = new Button("Exit");
		
		exitButton.setOnAction(e -> {
			closeProgram();
		});
		
		
		
		//window final layout
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setAlignment(Pos.TOP_LEFT);
		layout.getChildren().addAll(logo, startButton,continueButton,loadGameButton,statsButton, settingsButton, consoleMode, exitButton);
		
		
		//window scene and display this menu
		Scene scene = new Scene(layout, 400, 300);
		window.setScene(scene);
		window.show();
		
	}
	
	//method to confirm exit
	public void closeProgram()
	{
		boolean answer = false;
		ExitPromptBox exit = new ExitPromptBox();
		answer = exit.display();
		
		if(answer)
		{
			window.close();
		}
	}
	
	
}



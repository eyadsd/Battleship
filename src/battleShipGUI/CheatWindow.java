package battleShipGUI;

import battleShipGame.Coordinates;
import battleShipGame.Mine;
import battleShipGame.State;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class CheatWindow {
	
	public Stage window = new Stage();
	
	public Coordinates currentMineLocation = new Coordinates(0, 0);
	
	public void display()
	{
		
		
		window.setMinWidth(100);
		window.setMinHeight(250);
		window.setTitle("Cheat Window");
		
		
		GridPane gridLayout = new GridPane();
		gridLayout.setHgap(5);
		gridLayout.setVgap(5);
	    gridLayout.setPadding(new Insets(5, 5, 5, 5));
	    gridLayout.setMinWidth(400);
		gridLayout.setMinHeight(400);
		gridLayout.setAlignment(Pos.CENTER);

		
		Button outputButtons[][] = new Button[GUIApplication.game.settings.gridHeight][GUIApplication.game.settings.gridWidth];
		
		//add all the buttons to the ship
		for(int i = 0; i < GUIApplication.game.settings.gridHeight; i++)
		{
			for(int j = 0; j < GUIApplication.game.settings.gridWidth; j++)
			{
				
				Button tempButton = new Button();
				outputButtons[i][j] = tempButton;
				outputButtons[i][j].setPrefWidth(25);
				outputButtons[i][j].setPrefHeight(25);
				
				gridLayout.add(outputButtons[i][j], j, i);
			}
		}
		
		//disable the buttons that do not have ships and color the buttons that do have ships
		for(int i = 0; i < GUIApplication.game.settings.gridHeight; i++)
		{
			for(int j = 0; j < GUIApplication.game.settings.gridWidth; j++)
			{
				if(GUIApplication.game.CPU.playerGrid.tileArray[i][j].TileState == State.ship ||
					GUIApplication.game.CPU.playerGrid.tileArray[i][j].TileState == State.hit)
				{
					outputButtons[i][j].setStyle("-fx-background-color:#dc9656");
					outputButtons[i][j].setDisable(false);
				}
				else
				{
					outputButtons[i][j].setStyle("-fx-background-color: DarkTurquoise ");
					outputButtons[i][j].setDisable(true);
				}
			}
		}
		
		
		//reveal the CPU mine on the cheat window
		if(GUIApplication.game.settings.mines == true)
		{	
			Mine CPUMine = GUIApplication.game.CPU.playerMine;
			outputButtons[CPUMine.location.x][CPUMine.location.y].setStyle("-fx-background-color: Red ");
			
			currentMineLocation = new Coordinates(CPUMine.location.x, CPUMine.location.y);
			
			Timeline moveMines = new Timeline(new KeyFrame(
			        Duration.millis(50),
			        e -> {
			        	if(!currentMineLocation.equals(new Coordinates(CPUMine.location.x, CPUMine.location.y)))
			        	{
			        		outputButtons[currentMineLocation.x][currentMineLocation.y].setStyle("-fx-background-color: DarkTurquoise ");
			        		
			        		currentMineLocation.x = CPUMine.location.x;
			        		currentMineLocation.y = CPUMine.location.y;
			        		
			        		outputButtons[currentMineLocation.x][currentMineLocation.y].setStyle("-fx-background-color: Red ");
			        		if(CPUMine.detonated == true)
			        		{
			        			outputButtons[currentMineLocation.x][currentMineLocation.y].setStyle("-fx-background-color: DarkTurquoise ");
			        		}
			        	}   	
			        }));
			moveMines.setCycleCount(Animation.INDEFINITE);
			moveMines.play();
		}
		
		
		
		Scene scene = new Scene(gridLayout, 350, 350);
		window.setScene(scene);
		window.show();
		
	}

}


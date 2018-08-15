package battleShipGUI;



import battleShipGame.ComputerPlayer;
import battleShipGame.HumanPlayer;
import battleShipGame.State;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class ReplayScreen {
	
	public Button outputButtons[][];
	public Button inputButtons[][];
	double replaySpeed = 1.0;
	
	public Scene apply()
	{
		GUIApplication.game.human = new HumanPlayer();
		GUIApplication.game.human.playerGrid = GUIApplication.game.info.initialHumanGrid;
		GUIApplication.game.CPU = new ComputerPlayer();
		GUIApplication.game.CPU.playerGrid = GUIApplication.game.info.initialCPUGrid;
		
		/*for(int i = 0;i < GUIApplication.game.info.gameRecord.size(); i++)
		{
			System.out.println(GUIApplication.game.info.gameRecord.get(i).currentPlayer
					+ " " + GUIApplication.game.info.gameRecord.get(i).attackResult.toString()
					+ " " + GUIApplication.game.info.gameRecord.get(i).attackCoords.x
					+ " " + GUIApplication.game.info.gameRecord.get(i).attackCoords.y);
		}*/
		
		
		//this is the player grid on the left
		
		GridPane playerGrid = new GridPane();
		playerGrid.setHgap(5);
		playerGrid.setVgap(5);
		playerGrid.setPadding(new Insets(5, 5, 5, 5));
		playerGrid.setMinWidth(350);
		playerGrid.setMinHeight(350);
		playerGrid.setAlignment(Pos.CENTER);
		
		
		outputButtons = new Button[GUIApplication.game.settings.gridHeight][GUIApplication.game.settings.gridWidth];
		
		
		for(int i = 0; i < GUIApplication.game.settings.gridHeight; i++)
		{
			for(int j = 0; j < GUIApplication.game.settings.gridWidth; j++)
			{
				
				Button tempButton = new Button();
				outputButtons[i][j] = tempButton;
				outputButtons[i][j].setPrefWidth(25);
				outputButtons[i][j].setPrefHeight(25);
				
				if(GUIApplication.game.human.playerGrid.tileArray[i][j].TileState == State.ship)
				{
					outputButtons[i][j].setStyle("-fx-background-color: DarkSlateGray ");
				}
				else if(GUIApplication.game.human.playerGrid.tileArray[i][j].TileState == State.hit)
				{
					outputButtons[i][j].setStyle("-fx-background-color: LimeGreen ");
				}
				else if(GUIApplication.game.human.playerGrid.tileArray[i][j].TileState == State.miss)
				{
					outputButtons[i][j].setStyle("-fx-background-color: Maroon ");
				}
				else
				{
					outputButtons[i][j].setStyle("-fx-background-color: DarkTurquoise ");
				}
				
				playerGrid.add(outputButtons[i][j], j, i);
			}
		}
		
		
		//this is the computer grid on the right
		
		GridPane computerGrid = new GridPane();
		computerGrid.setHgap(5);
		computerGrid.setVgap(5);
		computerGrid.setPadding(new Insets(5, 5, 5, 5));
		computerGrid.setMinWidth(350);
		computerGrid.setMinHeight(350);
		computerGrid.setAlignment(Pos.CENTER);
		
		
		//the logic here determines what happens when a button on the computer grid is clicked
		inputButtons = new Button[GUIApplication.game.settings.gridHeight][GUIApplication.game.settings.gridWidth];
		
		for(int i = 0; i < GUIApplication.game.settings.gridHeight; i++)
		{
			for(int j = 0; j < GUIApplication.game.settings.gridWidth; j++)
			{
				
				Button tempButton = new Button();
				inputButtons[i][j] = tempButton;
				inputButtons[i][j].setPrefWidth(25);
				inputButtons[i][j].setPrefHeight(25);
				/*inputButtons[i][j].setStyle("-fx-background-color: DarkTurquoise ");*/
				
				if(GUIApplication.game.CPU.playerGrid.tileArray[i][j].TileState == State.hit)
				{
					inputButtons[i][j].setStyle("-fx-background-color: LimeGreen ");
					/*inputButtons[i][j].setDisable(true);*/
				}
				else  if(GUIApplication.game.CPU.playerGrid.tileArray[i][j].TileState == State.miss)
				{
					inputButtons[i][j].setStyle("-fx-background-color: Maroon ");
					/*inputButtons[i][j].setDisable(true);*/

				}
				else if(GUIApplication.game.CPU.playerGrid.tileArray[i][j].TileState == State.ship)
				{
					inputButtons[i][j].setStyle("-fx-background-color: DarkSlateGray ");
				}
				else
				{
					inputButtons[i][j].setStyle("-fx-background-color: DarkTurquoise ");
				}
				
				
				//add buttons functionality here
				
				/*inputButtons[i][j].setOnAction(e -> {
					

				});*/
				
				computerGrid.add(inputButtons[i][j], j, i);
			}
		}
		
		
		Timeline replay = new Timeline(new KeyFrame(
		        Duration.seconds(replaySpeed),
		        e -> {
		        	if(GUIApplication.game.info.gameRecord.isEmpty() == false)
		        	{
		        		if(GUIApplication.game.info.gameRecord.get(0).currentPlayer.equals("CPU"))
			        	{
			        		if(GUIApplication.game.info.gameRecord.get(0).attackResult == State.hit)
			        		outputButtons[GUIApplication.game.info.gameRecord.get(0).attackCoords.x]
			        					[GUIApplication.game.info.gameRecord.get(0).attackCoords.y].setStyle("-fx-background-color: LimeGreen ");
			        		else
			        		{
			        		outputButtons[GUIApplication.game.info.gameRecord.get(0).attackCoords.x]
			        					[GUIApplication.game.info.gameRecord.get(0).attackCoords.y].setStyle("-fx-background-color: Maroon ");
			        		}
			        	}
			        	else
			        	{
			        		if(GUIApplication.game.info.gameRecord.get(0).attackResult == State.hit)
				        	inputButtons[GUIApplication.game.info.gameRecord.get(0).attackCoords.x]
				        				[GUIApplication.game.info.gameRecord.get(0).attackCoords.y].setStyle("-fx-background-color: LimeGreen ");
				        	else
				        	{
				        	inputButtons[GUIApplication.game.info.gameRecord.get(0).attackCoords.x]
				        				[GUIApplication.game.info.gameRecord.get(0).attackCoords.y].setStyle("-fx-background-color: Maroon ");
				        	}
			        	}
			        	GUIApplication.game.info.gameRecord.remove(0);
		        	}	
		        	
		        }));
		replay.setCycleCount(GUIApplication.game.info.gameRecord.size());
		replay.play();
		
		
		/*
		//activate the mines and move them around
		if(GUIApplication.game.settings.mines == true)
		{
			
			Mine humanMine = GUIApplication.game.human.playerMine;
			outputButtons[humanMine.location.x][humanMine.location.y].setStyle("-fx-background-color: Red ");
			
			Mine CPUMine = GUIApplication.game.CPU.playerMine;
			
			Timeline moveMines = new Timeline(new KeyFrame(
			        Duration.millis(1500),
			        e -> {
			        	
			        	CPUMine.move();
			        	outputButtons[humanMine.location.x][humanMine.location.y].setStyle("-fx-background-color: DarkTurquoise ");
			        	humanMine.move();
			        	outputButtons[humanMine.location.x][humanMine.location.y].setStyle("-fx-background-color: Red ");
			        	
			        	if(CPUMine.detonated == true)
			        	{
			        		for(int i = 0; i < CPUMine.report.size() ; i++)
			        		{
			        			if(CPUMine.report.get(i).tileState == State.hit)
			        			{
				        			inputButtons[CPUMine.report.get(i).tileCoordinates.x][CPUMine.report.get(i).tileCoordinates.y].setStyle("-fx-background-color: LimeGreen ");
						        	inputButtons[CPUMine.report.get(i).tileCoordinates.x][CPUMine.report.get(i).tileCoordinates.y].setDisable(true);

			        			}
			        				
			        			if(CPUMine.report.get(i).tileState == State.miss)
			        			{
				        			inputButtons[CPUMine.report.get(i).tileCoordinates.x][CPUMine.report.get(i).tileCoordinates.y].setStyle("-fx-background-color: Maroon ");
						        	inputButtons[CPUMine.report.get(i).tileCoordinates.x][CPUMine.report.get(i).tileCoordinates.y].setDisable(true);
			        			}
			        		}
			        	}
			        	
			        	if(humanMine.detonated == true)
			        	{
			        		for(int i = 0; i < humanMine.report.size() ; i++)
			        		{
			        			if(humanMine.report.get(i).tileState == State.hit)
			        			{
				        			outputButtons[humanMine.report.get(i).tileCoordinates.x][humanMine.report.get(i).tileCoordinates.y].setStyle("-fx-background-color: LimeGreen ");
			        			}
			        				
			        			if(humanMine.report.get(i).tileState == State.miss)
			        			{
			        				outputButtons[humanMine.report.get(i).tileCoordinates.x][humanMine.report.get(i).tileCoordinates.y].setStyle("-fx-background-color: Maroon ");
			        			}
			        		}
			        	}
			        	
			        	if(humanMine.detonated == true)
			        	{
			        		
			        	}
			        	
			        	
			        }));
			
			moveMines.setCycleCount(Animation.INDEFINITE);
			moveMines.play();
		}*/
		
		//this is the stuff on the left
		
		Label playerLabel = new Label("your grid");
		
		VBox leftPanel = new VBox(10);
		leftPanel.getChildren().addAll(playerLabel, playerGrid);
		leftPanel.setAlignment(Pos.CENTER);
		
		
		//this is the stuff on the right
		
		Label computerLabel = new Label("opponents grid");
		
		VBox rightPanel = new VBox(10);
		rightPanel.getChildren().addAll(computerLabel, computerGrid);
		rightPanel.setAlignment(Pos.CENTER);


		//this is the stuff in the middle
		Button continueButton = new Button("continue");
		continueButton.setOnAction(e -> {
			replay.play();
		});
		
		Button pauseButton = new Button("pause");
		pauseButton.setOnAction(e -> {
			replay.pause();
		});
		
		/*Button fasterButton = new Button("faster");
		fasterButton.setOnAction(e -> {
			replaySpeed /= 2.0;
			replay.stop();
			replay.play();
		});
		
		Button slowerButton = new Button("slower");
		slowerButton.setOnAction(e -> {
			replaySpeed *= 2.0;
			replay.setDelay(Duration.seconds(replaySpeed));
		});*/
		
		VBox middlePanel = new VBox(30);
		middlePanel.setMinWidth(200);

		
		middlePanel.setAlignment(Pos.CENTER);
		middlePanel.getChildren().addAll(continueButton, pauseButton);
		
		
		
		//this is the final layout of the window
		HBox layout = new HBox(30);
		layout.getChildren().addAll(leftPanel, middlePanel, rightPanel);
		layout.setAlignment(Pos.CENTER);
		
			
		
		
		Scene scene = new Scene(layout, 1000, 600);
		
		return scene;
	}
	
	
	//this method colors the pressed buttons according to the result of the attack and disables them after
	/*private void updateComputerGrid(){
		
		if(GUIApplication.game.human.getPreviousAttackResult() == State.hit)
		{
			inputButtons[GUIApplication.game.human.getPreviousAttackCoordinates().x]
						[GUIApplication.game.human.getPreviousAttackCoordinates().y].setStyle("-fx-background-color: LimeGreen ");
			inputButtons[GUIApplication.game.human.getPreviousAttackCoordinates().x]
						[GUIApplication.game.human.getPreviousAttackCoordinates().y].setDisable(true);
		}
		else
		{
			inputButtons[GUIApplication.game.human.getPreviousAttackCoordinates().x]
						[GUIApplication.game.human.getPreviousAttackCoordinates().y].setStyle("-fx-background-color: Maroon ");
			inputButtons[GUIApplication.game.human.getPreviousAttackCoordinates().x]
						[GUIApplication.game.human.getPreviousAttackCoordinates().y].setDisable(true);
		}
	
	}*/
	
	/*private void updateHumanGrid(){
		
		if(GUIApplication.game.CPU.getPreviousAttackResult() == State.hit)
		{
			outputButtons[GUIApplication.game.CPU.getPreviousAttackCoordinates().x]
					     [GUIApplication.game.CPU.getPreviousAttackCoordinates().y].setStyle("-fx-background-color: LimeGreen ");
		}
		else
		{
			outputButtons[GUIApplication.game.CPU.getPreviousAttackCoordinates().x]
					     [GUIApplication.game.CPU.getPreviousAttackCoordinates().y].setStyle("-fx-background-color: Maroon ");
		}
		
	}*/
	
	
	
	
	/*private void humanAttack(int x, int y)
	{
		//human attack
		HumanPlayer.GUIInput = new Coordinates(x, y);
		GUIApplication.game.processPlayerInput(GUIApplication.game.human);
		
		if(GUIApplication.game.CPU.getDefeat() == true)
		{
			GameOverScreen.winner = ("human");
			GameOverScreen.score = Integer.toString(GUIApplication.game.human.score);
			GameOverScreen gameOver = new GameOverScreen();
			Scene tempScene = gameOver.apply();
			MainMenu.window.setScene(tempScene);
		}
		
		updateComputerGrid();
		
		//human attack finished
	}*/
	
	
	/*private void computerAttack()
	{
		//computer attack
		if(GUIApplication.game.settings.threads == false)
		{
			GUIApplication.game.processPlayerInput(GUIApplication.game.CPU);
		}
		
		
		if(GUIApplication.game.human.getDefeat() == true)
		{
			GameOverScreen.winner = ("CPU");
			GameOverScreen.score = Integer.toString(GUIApplication.game.CPU.score);
			GameOverScreen gameOver = new GameOverScreen();
			Scene tempScene = gameOver.apply();
			MainMenu.window.setScene(tempScene);
		}
		
		updateHumanGrid();
		
		//computer attack finished
	}*/
	

	
	
	
	
}



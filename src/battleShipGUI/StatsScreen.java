package battleShipGUI;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import battleShipGame.GameInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

public class StatsScreen {
	
	@SuppressWarnings("unchecked")
	public Scene apply()
	{
		ObservableList <GameInfo> gamesList = FXCollections.observableArrayList();
		try {
			FileInputStream fis;
			fis = new FileInputStream("finnishedGames.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList <GameInfo> list =  (ArrayList <GameInfo>) ois.readObject();
			gamesList = FXCollections.observableArrayList(list);
			
			ois.close();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			
			e2.printStackTrace();
		}
		TableView <GameInfo> table = new TableView<GameInfo>();
	    table.setEditable(true);
	    
	    
	    TableColumn <GameInfo,Integer> gameIdCol = new TableColumn<>("GameID");
	    
	    gameIdCol.setCellValueFactory(
	    	    new PropertyValueFactory<GameInfo,Integer>("gameID")
	    	);
	    TableColumn<GameInfo,String> playerNameCol = new TableColumn<>("Player Name");
	    
	    playerNameCol.setCellValueFactory(
	    	    new PropertyValueFactory<GameInfo,String>("playerName")
	    	);
	    TableColumn <GameInfo,String> startTimeCol = new TableColumn<>("Start time");
	    
	    startTimeCol.setCellValueFactory(
	    	    new PropertyValueFactory<GameInfo,String>("startTime")
	    	);
	    
	    TableColumn<GameInfo,String> endTimeCol = new TableColumn<>("End time");
	    
	    endTimeCol.setCellValueFactory(
	    	    new PropertyValueFactory<GameInfo,String>("endTime")
	    	);
	    TableColumn<GameInfo,String> winnerCol = new TableColumn<>("Winner");
	    
	    winnerCol.setCellValueFactory(
	    	    new PropertyValueFactory<GameInfo,String>("winner")
	    	);
	    
	    table.setItems(gamesList);
	    table.getColumns().addAll( gameIdCol,playerNameCol, startTimeCol, endTimeCol,winnerCol);
	    
	    table.setOnMouseClicked(e->{
	    	if(e.getClickCount() == 2)
	    	{
		    	GUIApplication.game.info = table.getSelectionModel().getSelectedItem();
		    	ReplayScreen gameStart = new ReplayScreen();
				Scene tempScene = gameStart.apply();
				MainMenu.window.setScene(tempScene);
	    	}
	    });
	    StackPane root = new StackPane();
	    root.getChildren().add(table);
	    Scene scene = new Scene(root, 500, 250);
		return scene;
	}
}

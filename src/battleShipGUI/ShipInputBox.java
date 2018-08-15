package battleShipGUI;

import battleShipGame.InputManager;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ShipInputBox {
	
	Stage window;
	
	public void display()
	{
		
		window = new Stage();
		window.setMinWidth(200);
		window.setMinHeight(200);
		window.setTitle("add new ship");
		window.initModality(Modality.APPLICATION_MODAL);
		
		Label newShipSizeLabel = new Label("new ship size");
		
		TextField shipSize = new TextField();
		
		HBox shipInput = new HBox(10);
		shipInput.getChildren().addAll(newShipSizeLabel, shipSize);
		
		//add button
		Button addButton = new Button("add");
		addButton.setOnAction(e -> {
			if(InputManager.isType(shipSize.getText(), "int"))
			{
				GUIApplication.game.settings.GUIaddShip(Integer.parseInt(shipSize.getText()));
			}
			window.close();
		});
		
		//cancel button
		Button cancelButton = new Button("cancel");
		cancelButton.setOnAction(e -> window.close());
		
		
		//this is the final layout of this window
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.getChildren().addAll(shipInput, addButton, cancelButton);
		
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	
	}
	

}

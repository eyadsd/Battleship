package battleShipGUI;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ShipRemoveBox {
	
	Stage window;
	
	public void display()
	{
		
		window = new Stage();
		window.setMinWidth(200);
		window.setMinHeight(200);
		window.setTitle("remove a ship");
		window.initModality(Modality.APPLICATION_MODAL);
		
		ChoiceBox<Integer> shipList = new ChoiceBox<Integer>();
		for(int i = 0; i < GUIApplication.game.settings.shipSizes.size(); i++)
		{
			shipList.getItems().add(GUIApplication.game.settings.shipSizes.get(i));
		}
		
		
		HBox shipDelete = new HBox(10);
		shipDelete.getChildren().addAll(shipList);
		
		
		//remove button
		Button removeButton = new Button("remove");
		removeButton.setOnAction(e -> {
			GUIApplication.game.settings.GUIremoveShip(shipList.getValue());
			window.close();
		});
		
		//cancel button
		Button cancelButton = new Button("cancel");
		cancelButton.setOnAction(e -> window.close());
		
		
		//this is the final layout of this window
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.getChildren().addAll(shipDelete, removeButton, cancelButton);
		
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	
	}
	

}

package battleShipGUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ExitPromptBox {
	
	private boolean result = false;
	Stage window;

	public boolean display()
	{
		
		window = new Stage();
		window.setMinWidth(100);
		window.setMinHeight(50);
		window.setTitle("exit game");
		window.initModality(Modality.APPLICATION_MODAL);
		
		Label exitMessage = new Label("are you sure?");		
		
		//yes button
		Button yesButton = new Button("yes");
		yesButton.setOnAction(e -> {
			this.result = true;
			this.window.close();
		});
		
		//no button
		Button noButton = new Button("no");
		noButton.setOnAction(e -> {
			this.result = false;
			this.window.close();
		});
		
		HBox confirmButtons = new HBox(10);
		confirmButtons.getChildren().addAll(yesButton, noButton);
		
		
		//this is the final layout of this window
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.getChildren().addAll(exitMessage, confirmButtons);
		
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		return result;
	
	}

}

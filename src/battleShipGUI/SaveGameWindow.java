package battleShipGUI;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import battleShipGame.Game;

public class SaveGameWindow {
	public static Stage window;
	Game savedGames[] = new Game[10];
	
	public void display() 
	{
		
		window = new Stage();
		window.setMinWidth(100);
		window.setMinHeight(450);
		window.setTitle("save game");
		Button[] saveSlot = new Button[10];
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setAlignment(Pos.TOP_CENTER);
		for(int i = 0; i < 10 ;i++)
		{
			int slotCount = i;
			saveSlot[i] = new Button("save slot "+ (i+1));
			saveSlot[i].setOnAction(e->{
				try {
					FileInputStream fis;
					fis = new FileInputStream("savedGames.ser");
					ObjectInputStream ois = new ObjectInputStream(fis);
					savedGames=  (Game[]) ois.readObject();
					ois.close();
				} catch (Exception e2) {					
					e2.printStackTrace();
				}
				
				 try {
					
					FileOutputStream fout = new FileOutputStream("SavedGames.ser");  
					ObjectOutputStream out =new ObjectOutputStream(fout);  
					savedGames[slotCount] = GUIApplication.game;
					out.writeObject(savedGames);
					out.close();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}  
				  
			});
			layout.getChildren().addAll(saveSlot[i]);
		}
		
		Scene scene = new Scene(layout, 400, 300);
		window.setScene(scene);
		window.show();
	}
}

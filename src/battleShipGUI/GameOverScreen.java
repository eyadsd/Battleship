package battleShipGUI;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import battleShipGame.GameInfo;
import battleShipGame.PlayerScore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class GameOverScreen {
	
	public static String winner = "";
	public static String score = "";
	
	
    ArrayList <GameInfo> list =  new ArrayList <GameInfo>();
    ArrayList <PlayerScore> scorelist =  new ArrayList <PlayerScore>();

	@SuppressWarnings("unchecked")
	public Scene apply()
	{
		GUIApplication.game.info.setEndTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		GUIApplication.game.info.setWinner(winner);
		Label finalMessage = new Label(winner + " is the winner \nfinal score : " + score);	
		
		//exit button
		Button ExitButton = new Button("exit");
		ExitButton.setOnAction(e -> {
			
			MainMenu.window.close();
			
		});
		
		//play again button
		Button playAgainButton = new Button("play again");
		playAgainButton.setOnAction(e -> {	
			
			MainMenu.window.close();
			
			/*Settings.defaultSettings();*/
			
			GUIApplication.mainMenu.display();
			
		});
		
		
		//add game to finished games file
		try {
			FileInputStream fis;
			fis = new FileInputStream("finnishedGames.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = ( ArrayList <GameInfo>)  ois.readObject();
			list.add(GUIApplication.game.info);
			ois.close();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			
			e2.printStackTrace();
		}
		
		 try {
			
			FileOutputStream fout = new FileOutputStream("finnishedGames.ser");  
			ObjectOutputStream out =new ObjectOutputStream(fout);  
			out.writeObject(list);
			out.close();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		/*//add top players
		 try {
				FileInputStream fis;
				fis = new FileInputStream("scoreBoard.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				scorelist = ( ArrayList <PlayerScore>)  ois.readObject();
				for(int i = 0; i < scorelist.size(); i++)
				{
					if()
				}
				scorelist.add(new (PlayerScore()));
				ois.close();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				
				e2.printStackTrace();
			}
			
			 try {
				
				FileOutputStream fout = new FileOutputStream("finnishedGames.ser");  
				ObjectOutputStream out =new ObjectOutputStream(fout);  
				out.writeObject(list);
				out.close();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
		 
		 
		//this is the final layout of the window
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(finalMessage, playAgainButton, ExitButton);
		
		
		Scene scene = new Scene(layout, 250, 250);

		return scene;
	
	}

}

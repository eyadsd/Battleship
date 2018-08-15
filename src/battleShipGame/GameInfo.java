package battleShipGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class GameInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public int gameID;
	public String playerName;
	public String startTime;
	public String endTime ;
	public String winner;
	public Grid initialHumanGrid;
	public Grid initialCPUGrid;
	public ArrayList<RecordMove> gameRecord;
	
	public GameInfo()
	{
		gameID = (new Random().nextInt(500));
		playerName = "steve";
		startTime = "0";
		endTime = "0";
		winner = "chicken dinner";
		
		
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}

package battleShipGame;

import java.io.Serializable;

public class RecordMove implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Coordinates attackCoords;
	public State attackResult;
	public String currentPlayer;

	public RecordMove(int x, int y, State state, String player)
	{
		attackResult = state;
		attackCoords = new Coordinates(x, y);
		currentPlayer = player;
	}
}

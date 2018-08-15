package battleShipGame;

import java.io.Serializable;

public class MineReport implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	public State tileState;
	public Coordinates tileCoordinates;
	
	public MineReport(int x, int y, State state)
	{
		tileState = state;
		tileCoordinates = new Coordinates(x, y);
	}

}

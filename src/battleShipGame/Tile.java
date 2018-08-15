package battleShipGame;

import java.io.Serializable;

public class Tile implements Serializable{

	private static final long serialVersionUID = 1L;

	Ship shipReference;
	
	public State TileState ;
	
	Tile(Ship ship, State state)
	{
		this.shipReference = ship;
		this.TileState = state;
	}
	
	
}

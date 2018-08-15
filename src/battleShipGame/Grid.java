package battleShipGame;

import java.io.Serializable;

import battleShipGUI.GUIApplication;

public class Grid implements Serializable{

	private static final long serialVersionUID = 1L;

	public Tile tileArray[][];
	
	public Player playerReference;
	
	
	public Grid(Player owner)
	{
		tileArray = new Tile[GUIApplication.game.settings.gridHeight][GUIApplication.game.settings.gridWidth];
		for (int i = 0; i < GUIApplication.game.settings.gridHeight; i++){
			for(int j = 0; j < GUIApplication.game.settings.gridWidth ; j++){
				
				tileArray[i][j] = new Tile(null, State.empty);
				
			}
		}
		
		playerReference = owner;
	}
	

	private boolean ValidShipPosition(int positionX, int positionY, Ship ship)
	{
		
		if(positionX >= GUIApplication.game.settings.gridHeight || positionX < 0 || positionY >= GUIApplication.game.settings.gridWidth || positionY < 0)
		{
			return false;
		}
		
		if(ship.getDirection() == ShipDirection.vertical)
		{
			for(int i = positionX; i < positionX + ship.getSize(); i++)
			{
				if(i >= GUIApplication.game.settings.gridHeight)
				{
					return false;
				}
				if(tileArray[i][positionY].TileState != State.empty)
				{
					return false;
				}
			}
			return true;
		}
		else
		{
			for(int j = positionY; j < positionY + ship.getSize(); j++)
			{
				
				if(j >= GUIApplication.game.settings.gridWidth)
				{
					return false;
				}
				if(tileArray[positionX][j].TileState != State.empty)
				{
					return false;
				}
			}
			return true;
		}
	}
	
	
	public boolean placeShip(int positionX,int positionY, Ship ship)
	{
		if(!ValidShipPosition(positionX, positionY, ship))
		{
			return false;
		}
		
		
		if(ship.getDirection() == ShipDirection.vertical)
		{
			for(int i = positionX; i < positionX + ship.getSize(); i++)
			{
				tileArray[i][positionY].TileState = State.ship;
				tileArray[i][positionY].shipReference = ship;
			}
		}
		else
		{
			for(int j = positionY; j < positionY + ship.getSize(); j++)
			{
				tileArray[positionX][j].TileState = State.ship;
				tileArray[positionX][j].shipReference = ship;
			}
		}
		
		return true;
		
	}
	
	
	public State ProcessAttack(Coordinates xy)
	{
		
		State target = tileArray[xy.x][xy.y].TileState;
		
		switch(target)
		{
		case empty: 
			tileArray[xy.x][xy.y].TileState = State.miss;
			return State.miss;
			
		case ship:
			tileArray[xy.x][xy.y].TileState = State.hit;
			tileArray[xy.x][xy.y].shipReference.takeDamage();
			return State.hit;
			
		case hit:
			//can only happen from mines
			break;
			
		case miss:
			//can only happen from mines
			break;
			
		case mine:
			this.playerReference.playerMine.explode();
			return State.mine;
			
		default:
			//cannot happen
			break;
		
		}
		
		return target;
		
	}
	
	
	//console mode only
	public void display()
	{
		
		System.out.printf("%n%n**************************************************%n%n");
		
		for (int i = 0; i < GUIApplication.game.settings.gridHeight; i++){
			for(int j = 0; j < GUIApplication.game.settings.gridWidth ; j++){
				switch(tileArray[i][j].TileState){
				
				case empty : System.out.print('.');
				break;
				
				case ship : System.out.print('S');
				break;
				
				case hit : System.out.print('H');
					break;
					
				case miss : System.out.print('M');
					break;
					
				case mine : System.out.print('*');
					
				default:
					break;
				
				}
			}
			System.out.print("\n");
		}	
		
		System.out.printf("%n**************************************************%n%n");
		
	}
	
	

}

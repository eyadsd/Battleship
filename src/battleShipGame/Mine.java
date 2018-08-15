package battleShipGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import battleShipGUI.GUIApplication;

public class Mine implements Serializable{

	private static final long serialVersionUID = 1L;

	public boolean detonated = false;
	
	public Coordinates location;
	
	public Grid gridReference;
	
	private ArrayList<Coordinates> surroundingTiles = new ArrayList<Coordinates>();
	
	public ArrayList<MineReport> report = new ArrayList<MineReport>();
	
	private ArrayList<Coordinates> potentialLocations = new ArrayList<Coordinates>();
	
	public Mine(Coordinates xy, Grid grid)
	{
		location = xy;
		gridReference = grid;
		
		//vertical and horizontal directions
		surroundingTiles.add(new Coordinates(0, 1));
		surroundingTiles.add(new Coordinates(0, -1));
		surroundingTiles.add(new Coordinates(1, 0));
		surroundingTiles.add(new Coordinates(-1, 0));
		
		//diagonal directions
		surroundingTiles.add(new Coordinates(1, 1));
		surroundingTiles.add(new Coordinates(-1, 1));
		surroundingTiles.add(new Coordinates(1, -1));
		surroundingTiles.add(new Coordinates(-1, -1));
		
	}
	
	
	public void move()
	{
		if(detonated == false)
		{
			Random randomizer = new Random();
			int n = 4, x = 0, y = 0;
			Coordinates xy = location;
			
			for(int i = 0; i < n; i++)
			{
				xy = surroundingTiles.get(i);
				x = xy.x + location.x;
				y = xy.y + location.y;
				if(CheckValidTarget(x, y) == true)
				{
					if(this.gridReference.tileArray[x][y].TileState == State.empty)
					{
						this.potentialLocations.add(new Coordinates(x, y));
					}
				}	
			}
			
			//mine cannot move
			if(potentialLocations.size() == 0)
			{
				return;
			}
			
			
			xy = potentialLocations.get(randomizer.nextInt(potentialLocations.size()));
			
			gridReference.tileArray[location.x][location.y].TileState = State.empty;
			gridReference.tileArray[xy.x][xy.y].TileState = State.mine;
			
			if(GUIApplication.game.settings.consoleMode == true)
			{
				System.out.println("mine moved from " + location.x + " " + location.y + " to " + xy.x + " " + xy.y + "\n");
			}
			
			
			location = new Coordinates(xy.x, xy.y);
			
			potentialLocations.clear();
		}
	}
	
	
	public void explode()
	{
		int x, y;
		for(int i = 0; i < surroundingTiles.size(); i++)
		{
			x = surroundingTiles.get(i).x + location.x;
			y = surroundingTiles.get(i).y + location.y;
			
			if(CheckValidTarget(x, y) == true)
			{
				report.add(new MineReport(x, y, gridReference.ProcessAttack(new Coordinates(x, y))));
			}
		}
		report.add(new MineReport(location.x, location.y, gridReference.tileArray[location.x][location.y].TileState = State.miss));
		detonated = true;
	}
	
	
	private boolean CheckValidTarget(int x, int y)
	{
		if(x < 0 || x >= GUIApplication.game.settings.gridHeight || y < 0 || y >= GUIApplication.game.settings.gridWidth)
		{
			return false;
		}

		return true;
	}
	
	
}





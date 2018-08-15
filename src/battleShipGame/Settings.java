package battleShipGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Settings implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	public  int gridHeight;
	public  int gridWidth;
	public  List<Integer> shipSizes;
	public  Difficulty CPULevel;
	public  boolean cheat;
	public  boolean consoleMode;
	public  boolean threads;
	public  boolean timedGame;
	public  int turnTimer;
	public  boolean mines;
	public String playerName;
	
	public  void defaultSettings(){
		
		gridHeight = 10;
		gridWidth = 10;
		
		CPULevel = Difficulty.normal;
		
		shipSizes = new ArrayList<Integer>();
		shipSizes.clear();
		shipSizes.add(5);
		shipSizes.add(4);
		shipSizes.add(3);
		shipSizes.add(3);
		shipSizes.add(2);
		Collections.sort(shipSizes, Collections.reverseOrder());

		consoleMode = true;
		threads = false;
		timedGame = true;
		turnTimer = 10;
		mines = true;
		playerName = "player";
		}
	
	
	
	public  void setGridHeight(int height)
	{
		//minimum height is 5 maximum is 10 and cannot be set smaller than biggest ship or number of ships
		if (height >= 5 && height <= 10 && height >= shipSizes.get(0) && height >= shipSizes.size())
			gridHeight = height;
	}
	
	
	public  void setGridWidth(int width)
	{
		//minimum width is 5 maximum is 10 and cannot be set smaller than biggest ship or number of ships
		if (width >= 5 && width <= 10 && width >= shipSizes.get(0) && width >= shipSizes.size())
			gridWidth = width;
	}
	
	//console mode only (not used yet)
	public  void addShip()
	{
		
		System.out.println("Input new ship size");
			
		shipSizes.add(Game.scan.nextInt());
		
		//sorting the arrayList by descending order 
		Collections.sort(shipSizes, Collections.reverseOrder());
			
	}
	
	
	public  void GUIaddShip(int i)
	{
		if(i <= gridWidth && i <= gridHeight && i > 0 && shipSizes.size() < gridWidth && shipSizes.size() < gridHeight)
		{
			shipSizes.add(i);
			Collections.sort(shipSizes, Collections.reverseOrder());
		}
			
	}
	
	//console mode only (not used yet)
	public  void removeShip()
	{
		System.out.println("Input index of ship to remove");
		
		int i = Game.scan.nextInt();
		shipSizes.remove(i);
		
	}
	
	
	public  void GUIremoveShip(int size)
	{
		
		for(int i = 0; i < shipSizes.size(); i++)
		{
			//cannot remove the last ship
			if(shipSizes.get(i) == size && shipSizes.size() > 1)
				{
					shipSizes.remove(i);
					break;
				}			
		}	
	}
	
	
	public  Difficulty getCPULevel() {
		return CPULevel;
	}

	public  void GUIsetCPULevel(String level) {
		
		if(level == "easy")
		{
			CPULevel = Difficulty.easy;
		}
		else if(level == "normal")
		{
			CPULevel = Difficulty.normal;
		}
		else
		{
			CPULevel = Difficulty.hard;
		}
		
	}
	
	public  void setTurnTimer(int i)
	{
		if(i >= 5 && i < 30)
		{
			turnTimer = i;
		}
		else
		{
			turnTimer = 10;
		}
	}
	public void setName(String name)
	{
		playerName = name;
	}
	
}




package battleShipGame;

import battleShipGUI.GUIApplication;

public class HumanPlayer extends Player{
	
	private static final long serialVersionUID = 1L;
	
	public static Coordinates GUIInput;
	public static ShipDirection GUISwitch;
	
	
	public HumanPlayer()
	{
		super();
		
		playerName = "human";
		GUIInput = new Coordinates(0, 0);
		GUISwitch = ShipDirection.horizontal;
	}
	
	//console mode only
	class UserInput
	{
		//console mode only
		public void getNewCoordinates()
		{
			
			System.out.println("Input coordinates : ");
			
			int x = 0, y = 0;
			
			
			System.out.println("Input Row :");
			x = InputManager.properIntInput();
			
			System.out.println("Input Column");
			y = InputManager.properIntInput();
			
			
			nextCoordinates = new Coordinates(x, y);
		}
		
		
		//console mode only
		public void getNewShipPlacement()
		{
			getNewCoordinates();
			
			System.out.println("switch Direction? (y/n)");
			
			char switchDirection;
			switchDirection = InputManager.properCharInput();
			
			if(switchDirection == 'y')
			{
				nextDirectionSwitch = true;
			}
			
		}
		
		
		//console mode only
		public boolean checkValidCoordinates()
		{
			if(nextCoordinates.x >= GUIApplication.game.settings.gridHeight ||
				nextCoordinates.x < 0 ||
				nextCoordinates.y >= GUIApplication.game.settings.gridWidth ||
				nextCoordinates.y < 0)
				{
					return false;
				}
			return true;
		}
		
		
		//console mode only
		public boolean checkValidAttackCoordinates()
		{
			//checks if the input coordinates are inside the grid
			if(checkValidCoordinates() == false)
			{
				return false;
			}
			
			//check if the target has been hit yet
			if(draft[nextCoordinates.x][nextCoordinates.y] != State.empty)
			{
				return false;
			}
			
			return true;
		}
		
		
	}
	
	//GUI mode only
	public boolean placeShip(int index)
	{
		shipArray[index].setDirection(GUISwitch);
		if(playerGrid.placeShip(GUIInput.x,GUIInput.y,shipArray[index]))
		{
			
			return true;
		}
		return false;
			
	}
	
	
	//console mode only
	@Override
	public void placeAllShips()
	{
		
		System.out.println("Ship placement mode : \n");
		
		UserInput input = new UserInput();
		
		for(int i = 0 ; i < shipArray.length; i++)
		{
			
			System.out.printf("Placing ship : %d \nSize : %d\nCurrent direction : %s\n",
								i,
								this.shipArray[i].getSize(),
								this.shipArray[i].getDirection().toString());
			
			while(true)
			{
				//input position for the ship
				input.getNewShipPlacement();
				
				
				//switch the direction of the ship if the player demands it
				if(nextDirectionSwitch)
				{
					shipArray[i].switchDirection();
					nextDirectionSwitch = false;
				}
				
				
				//check if position of the ship is valid and place the ship if valid
				if(playerGrid.placeShip(nextCoordinates.x,nextCoordinates.y,shipArray[i]))
				{
					break;
				}
				
				System.out.println("Invalid ship position enter new position : \n");
			}
			System.out.printf("\nship %d has been placed\nLocation: %d %d\nDirection: %s\n\n",
								i,
								nextCoordinates.x,
								nextCoordinates.y,
								this.shipArray[i].getDirection().toString());
		}
		System.out.printf("%s has placed all their ships\n",playerName);
		System.out.println("\nShip placement mode has ended \n");
	}
	
	
	@Override
	public Coordinates SelectAttackTarget()
	{
		if(GUIApplication.game.settings.consoleMode == false)
		{
			return GUIInput;
		}
		
		System.out.println("Attack mode : \n");
		
		UserInput input = new UserInput();
		input.getNewCoordinates();
		
		while(!input.checkValidAttackCoordinates())
		{
			System.out.println("Invalid Attack Coordinates enter again : \n");
			input.getNewCoordinates();
		}
		
		return nextCoordinates;
	}
		
}



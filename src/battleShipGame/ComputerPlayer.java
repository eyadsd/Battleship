package battleShipGame;

import java.util.Random;
import java.util.Stack;

import battleShipGUI.GUIApplication;


public class ComputerPlayer extends Player{
	
	private static final long serialVersionUID = 1L;

	private Difficulty level;
	
	private Stack<Coordinates> nextTargets;
	
	
	public ComputerPlayer()
	{
		super();
		
		playerName = "CPU";
		
		level = GUIApplication.game.settings.getCPULevel();
		
		nextTargets = new Stack<Coordinates>();
		nextTargets.clear();
	}
	
	
	
	public class AI
	{
		public void getNewAttackCoordinates()
		{
			
			Random randomizer = new Random();
			
			switch (level)
			{
			case easy:
				//the easy computer attacks tiles at random
				nextCoordinates = new Coordinates(randomizer.nextInt(GUIApplication.game.settings.gridHeight), randomizer.nextInt(GUIApplication.game.settings.gridWidth));
				break;
				
				
			case normal:
				//the normal computer attacks at random (hunt mode) and if it hits attacks all adjacent tiles (target mode)
				//can be improved by adding parity filter (not implemented yet)
				
				//if the attack stack is empty and the previous attack missed choose random target
				if(getPreviousAttackResult() == State.miss && nextTargets.isEmpty() == true)
				{
					nextCoordinates = new Coordinates(randomizer.nextInt(GUIApplication.game.settings.gridHeight), randomizer.nextInt(GUIApplication.game.settings.gridWidth));
					return;
				}
				
				//if the attack stack is not empty and the previous attack missed attack the first target in the stack
				else if(getPreviousAttackResult() == State.miss && nextTargets.isEmpty() == false)
				{
					nextCoordinates = nextTargets.pop();
				}
				
				//if the previous attack hit do these tasks
				else
				{
					//add all 4 adjacent tiles to the attack stack
					int x, y;
					
					x = getPreviousAttackCoordinates().x;
					y = getPreviousAttackCoordinates().y;
					
					if(CheckValidAttackCoordinates(x - 1, y))
					{	
						//check up
						nextTargets.push(new Coordinates(x - 1, y));
					}
					if(CheckValidAttackCoordinates(x + 1, y))
					{
						//check down
						nextTargets.push(new Coordinates(x + 1, y));
					}
					if(CheckValidAttackCoordinates(x, y + 1))
					{
						//check right
						nextTargets.push(new Coordinates(x, y + 1));
					}
					if(CheckValidAttackCoordinates(x, y - 1))
					{
						//check left
						nextTargets.push(new Coordinates(x, y - 1));
					}
					
					//if none of the adjacent tiles are valid targets and the stack is empty then choose random target
					if(nextTargets.isEmpty() == true)
					{
						nextCoordinates = new Coordinates(randomizer.nextInt(GUIApplication.game.settings.gridHeight), randomizer.nextInt(GUIApplication.game.settings.gridWidth));
						return;
					}
					
					//if there are moves left in the attack stack choose the first target in the stack
					nextCoordinates = nextTargets.pop();
				}
				
				
				break;
				
			case hard:
				//the hard computer uses a probability algorithm to select targets(not implemented yet)
				//this algorithm requires the game to be played with full rules(report which ships have been sunk so far)
				
				nextCoordinates = new Coordinates(randomizer.nextInt(GUIApplication.game.settings.gridHeight), randomizer.nextInt(GUIApplication.game.settings.gridWidth));
				
				
				//add hard difficulty logic here
				break;
				
			default:
				//cannot happen
				break;
				
			
			}

		}
		
		private void getNewShipCoordinates()
		{
			Random randomizer = new Random();
			
			nextCoordinates = new Coordinates(randomizer.nextInt(GUIApplication.game.settings.gridHeight), randomizer.nextInt(GUIApplication.game.settings.gridWidth));
		}
		
		
		public void getNewShipPlacement()
		{
			getNewShipCoordinates();
			
			Random randomizer = new Random();
			
			nextDirectionSwitch = randomizer.nextBoolean();
			
		}
		
		
		public boolean CheckValidAttackCoordinates(int x, int y)
		{
			if(x < 0 || x >= GUIApplication.game.settings.gridHeight || y < 0 || y >= GUIApplication.game.settings.gridWidth)
			{
				return false;
			}
			
			
			if(draft[x][y] != State.empty)
			{
				return false;
			}
			
			return true;
		}
		
	}
	
	//has no use for the computer player
	@Override
	public boolean placeShip(int index)
	{
		return false;
	}
	
	
	@Override
	public void placeAllShips()
	{
		
		AI selector = new AI();
		
		
		for(int i = 0 ; i < shipArray.length; i++)
		{
			while(true)
			{
				//Select a random position for the ships
				selector.getNewShipPlacement();
				
				//select a random direction for the ships
				if(nextDirectionSwitch)
				{
					shipArray[i].switchDirection();
				}
				
				//check if the ship is placed in a valid position 
				if(playerGrid.placeShip(nextCoordinates.x, nextCoordinates.y, shipArray[i]))
				{					
					break;
				}
				//if control reaches here then ship input was invalid
				
			}
		}
		if(GUIApplication.game.settings.consoleMode == true)
		{
			System.out.printf("%s has placed all their ships\n",playerName);
		}
	}
	
	
	@Override
	public Coordinates SelectAttackTarget()
	{
		
		AI selector = new AI();
		selector.getNewAttackCoordinates();
		
		while(!selector.CheckValidAttackCoordinates(nextCoordinates.x, nextCoordinates.y))
		{
			selector.getNewAttackCoordinates();
		}
		currentAttack = nextCoordinates;
		return nextCoordinates;
	}
	
	
}



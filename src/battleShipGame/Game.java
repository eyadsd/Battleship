package battleShipGame;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Scanner;

import battleShipGUI.GUIApplication;



public class Game implements Serializable{

	private static final long serialVersionUID = 1L;
	public  Settings settings  = new Settings();
	public Player CPU;
	public Player human;
	public GameInfo info;
	private boolean gameOver;
	public Player winningPlayer;
	
	public static Scanner scan;
	
	
	
	public Game()
	{
		gameOver = false;
		info = new GameInfo();
		info.setStartTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime())); 
		info.gameRecord = new ArrayList<RecordMove>();
	}
	
	
	//take input from one player and pass it to the other player to accept then return the result to the attacker
	public void processPlayerInput(Player currentPlayer)
	{
		Player Attacker, Defender;
		if(currentPlayer.equals(human))
		{
			Attacker = human;
			Defender = CPU;
		}
		else
		{
			Attacker = CPU;
			Defender = human;
		}
		
		Coordinates xy = Attacker.SelectAttackTarget();
		State result = Defender.AcceptAttack(xy);
		if(result == State.mine)
		{
			Attacker.score += 300;
			Attacker.reportMine(xy, Defender.playerMine.report);
			recordMine(xy, Defender.playerMine.report, Attacker.playerName);
		}
		else
		{
			Attacker.report(xy, result);
			recordMove(xy, result, Attacker.playerName);
		}
		
		//end the game if the defender loses after the attack
		if(Defender.getDefeat() == true)
		{
			winningPlayer = Attacker;
			gameOver = true;
		}
	}
	
	//console mode only
	public void runDefault()
	{
		System.out.println("Starting BattleShip game\n");
		
		GUIApplication.game.settings.defaultSettings();
		System.out.println("Default settings have been set\n");
		
		run();
	}
	
	//can be called from GUI to play with modified settings
	public void run()
	{
		GUIApplication.game.settings.consoleMode = true;
		scan = new Scanner(System.in);
		
		initialize();
		
		gameLoop();
		
		endGame();
		
	}
	
	//console mode only
	private void initialize()
	{
		
		
		System.out.println("Initializing Players\n");
		
		initPlayers();
		
		//CPU is the default winner
		winningPlayer = CPU;
		
		System.out.println("All Players have been initialized\n");
		
		if(GUIApplication.game.settings.mines == true)
		{
			System.out.println("Initializing Mines\n");
			
			human.placeMine();
			CPU.placeMine();
			
			System.out.println("Player Mines have been Initialized\n");
		}
		
	}
	
	
	//console mode only
	private void gameLoop()
	{
		System.out.println("Game Started\n");
		
		while(gameOver != true)
		{
			processInput();
			
			outputGame();
			
		}
	}
	
	
	//console mode only
	private void endGame()
	{
		
		System.out.println("Game Over\n");
		System.out.println(winningPlayer.playerName + " is the winner!\n");
		
		//closes System.in with it and it cannot be opened again
		//only call this at the very end of the program
		scan.close();
				
		//the game ends here
		
	}
	
	
	//console mode only
	private void initPlayers()
	{
		CPU = new ComputerPlayer();
		CPU.playerName = ("CPU");
		
		
		human = new HumanPlayer();
		human.playerName = ("human");
		
		System.out.println("Initializing " + CPU.playerName);
		
		CPU.placeAllShips();
		
		System.out.println(CPU.playerName + " has been initialized\n");
		
		System.out.println("Initializing " + human.playerName);
		
		human.placeAllShips();
		
		System.out.println(human.playerName + " human has been initialized\n");
	}
	
	
	//console mode only
	public void processInput()
	{
		processPlayerInput(human);
		
		//skip computers turn if the game is over
		if(gameOver == true)
		{
			return;
		}
		
		processPlayerInput(CPU);
	}
	
	
	//console mode only
	private void outputGame()
	{
		
		human.displayGrid();
		human.displayDraft();
		
		CPU.playerMine.move();
		human.playerMine.move();
		
		System.out.println(human.getMessage());
		System.out.println(CPU.getMessage());
		
	}
	
	
	public void recordMine(Coordinates xy, ArrayList<MineReport> enemyMineReport, String name)
	{
		RecordMove move = new RecordMove(xy.x, xy.y, State.miss, name);
		info.gameRecord.add(move);
		
		int x = 0, y = 0;
		for(int i = 0; i < enemyMineReport.size(); i++)
		{
			x = enemyMineReport.get(i).tileCoordinates.x;
			y = enemyMineReport.get(i).tileCoordinates.y;
			
			move = new RecordMove(x, y, enemyMineReport.get(i).tileState, name);
			info.gameRecord.add(move);
		}
		
	}
	
	
	public void recordMove(Coordinates xy, State result, String name)
	{
		RecordMove move = new RecordMove(xy.x, xy.y, result, name);
		
		info.gameRecord.add(move);
	}
	
}


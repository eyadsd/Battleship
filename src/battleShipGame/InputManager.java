package battleShipGame;


public class InputManager {
	
	//validates user input and prompts correct integer input
	public static int properIntInput()
	{
		String userInput = "";
		boolean error = false;
		
		do
		{
			userInput = Game.scan.nextLine();
			if(!isType(userInput, "int"))
			{
				error = true;
				System.out.println("Invalid integer input. enter new value :");
			}
			else
			{
				error = false;
			}
		}
		while(error == true);
		
		return Integer.parseInt(userInput);
	}
	
	
	//returns the first character of the input string
	public static char properCharInput()
	{
		String userInput = "";
		userInput = Game.scan.nextLine();

		return userInput.charAt(0);
	}
	
	
	//checks for valid type of the input string
	public static boolean isType(String testString, String type)
	{
		try{
			switch(type)
			{
			case "int" : 
				Integer.parseInt(testString);
				break;
						
			case "double" :
				Double.parseDouble(testString);
				break;
			
			default :
				return false;
			}
			return true;
			
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	
}
